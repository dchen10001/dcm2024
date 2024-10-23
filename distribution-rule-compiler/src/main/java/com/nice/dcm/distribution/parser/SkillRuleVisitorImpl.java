package com.nice.dcm.distribution.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.AndSkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.OrderContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Queue_statusContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillOrSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.ActionRule.ActionType;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule.QueueStatus;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.BinaryCondition;
import com.nice.dcm.distribution.parser.rule.BinaryOperator;
import com.nice.dcm.distribution.parser.rule.BinaryOperatorRule;
import com.nice.dcm.distribution.parser.rule.ComparableOidSet;
import com.nice.dcm.distribution.parser.rule.Condition;
import com.nice.dcm.distribution.parser.rule.ConditionRule;
import com.nice.dcm.distribution.parser.rule.Node;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.RoutingRule;
import com.nice.dcm.distribution.parser.rule.RoutingRuleGroup;
import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;
import com.nice.dcm.distribution.parser.rule.SkillLevelCondition;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.SkillSetRule;
import com.nice.dcm.distribution.parser.rule.SqlCondition;
import com.nice.dcm.distribution.parser.rule.SqlOperator;
import com.nice.dcm.distribution.parser.rule.SqlOperatorRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

public class SkillRuleVisitorImpl extends AbstractSkillRuleVisitorImpl {

    public SkillRuleVisitorImpl() {
        super();
    }
    
    /**
     * Get a routing rule set, which is the top level rule set
     * 
     * @param token
     * @return
     */
    @Override
    public RoutingRuleSet visitRoutingRuleSet(RoutingRuleSetContext ctx) {
        List<RoutingRuleGroup> ruleGroups = new ArrayList<>();
        // add first rule group
        ruleGroups.add(visitRoutingRuleGroup(ctx.routingRuleGroup()));

        for (RoutingWaitingRuleGroupContext r : ctx.routingWaitingRuleGroup()) {
            ruleGroups.add(visitRoutingWaitingRuleGroup(r));
        }
        
        //TODO: sort by waitAfter, same waitAfter should merge
        if(ruleGroups.size() > 1) {
            ruleGroups.sort((r1, r2) -> Long.compare(r1.getWaitAfterSeconds(), r2.getWaitAfterSeconds()));
        }
        return new RoutingRuleSet(ruleGroups);
    }

    /**
     * Get a routing group with waiting time.
     * 
     * @param token
     * @return
     */
    @Override
    public RoutingRuleGroup visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
        WaitRule waitRule = visitWaitRule(ctx.waitRule());
        RoutingRuleGroup rule = visitRoutingRuleGroup(ctx.routingRuleGroup());
        rule.setWaitAfterSeconds(waitRule.getWaitFor());
        return rule;
    }

    /**
     * Get a routing group without waiting time.
     * 
     * @param token
     * @return
     */
    @Override
    public RoutingRuleGroup visitRoutingRuleGroup(RoutingRuleGroupContext ctx) {
        List<RoutingRule> rules = ctx.routingRule().stream().map(this::visitRoutingRule).collect(Collectors.toList());
        if (rules.size() > 1) {
            rules.sort((r1, r2) -> Integer.compare(r1.getPriority(), r2.getPriority()));
            //TODO: merge rules with same priority if the relationship is clear
//            rules = rules
//                    .stream().collect(Collectors.groupingBy(RoutingRule::getPriority)).values().stream().map(
//                            list -> list.stream()
//                                    .reduce((r1, r2) -> new RoutingRule(r1.getAction(), r1.getSkills(),
//                                            r1.getPriority(), r1.getAgentStatus())))
//                    .map(r -> r.get()).collect(Collectors.toList());
        }
        return new RoutingRuleGroup(rules);
    }

    /**
     * Get a routing rule
     * 
     * @param token
     * @return
     */
    @Override
    public RoutingRule visitRoutingRule(RoutingRuleContext ctx) {
        ActionRule action = visitRuleAction(ctx.ruleAction());
        AndSkillsRule skill = visitAndSkills(ctx.andSkills());
        OrderRule order = visitOrder(ctx.order());
        Set<ComparableOidSet> skills = skill.getSkills();
        QueueStatus queueStatus = null;
        if (ctx.queue_status() != null) {
            QueueStatusRule agentStatusNode = visitQueue_status(ctx.queue_status());
            queueStatus = agentStatusNode.getQueueStatus();
        }
        return new RoutingRule(action, skills, order.getPriority(), queueStatus);
    }

    /**
     * Get a AND skill set rule. The rule language is AND but the actual
     * implementation is OR.(This need to be confirmed)
     * 
     * @param token
     * @return
     */
    @Override
    public AndSkillsRule visitAndSkills(AndSkillsContext ctx) {
        List<SkillOrSetContext> skills = ctx.skillOrSet();
        List<ComparableOidSet> skillSet = skills.stream().map(this::getOidSet).toList();
        return new AndSkillsRule(skillSet);
    }

    protected ComparableOidSet getOidSet(SkillOrSetContext ctx) {
        SkillSetRule node = visitSkillOrSet(ctx);
        return node.getSkillSetKey();
    }    
}
