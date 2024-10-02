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

import com.nice.dcm.distribution.parser.DistributionRulesParser.Agent_statusContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.AndSkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.OrderContext;
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
import com.nice.dcm.distribution.parser.rule.AgentStatusRule;
import com.nice.dcm.distribution.parser.rule.AgentStatusRule.AgentStatus;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.BinaryCondition;
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
import com.nice.dcm.distribution.parser.rule.SqlOperatorRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

public class SkillRuleVisitor implements DistributionRulesVisitor<Node> {

    @Override
    public Node visit(ParseTree tree) {
        return null;
    }

    @Override
    public Node visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Node visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Node visitErrorNode(ErrorNode node) {
        return null;
    }

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

    @Override
    public RoutingRuleGroup visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
        WaitRule waitRule = visitWaitRule(ctx.waitRule());
        RoutingRuleGroup rule = visitRoutingRuleGroup(ctx.routingRuleGroup());
        rule.setWaitAfterSeconds(waitRule.getWaitFor());
        return rule;
    }

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

    @Override
    public RoutingRule visitRoutingRule(RoutingRuleContext ctx) {
        ActionRule action = visitRuleAction(ctx.ruleAction());
        AndSkillsRule skill = visitAndSkills(ctx.andSkills());
        OrderRule order = visitOrder(ctx.order());
        Set<ComparableOidSet> skills = skill.getSkills();
        AgentStatus agentStatus = null;
        if (ctx.agent_status() != null) {
            AgentStatusRule agentStatusNode = visitAgent_status(ctx.agent_status());
            agentStatus = agentStatusNode.getAgentStatus();
        }
        return new RoutingRule(action, skills, order.getPriority(), agentStatus);
    }

    @Override
    public AgentStatusRule visitAgent_status(Agent_statusContext ctx) {
        if (ctx.LEAST_BUSY() != null) {
            return new AgentStatusRule(AgentStatus.LEAST_BUSY);
        }
        return null;
    }

    @Override
    public ActionRule visitRuleAction(RuleActionContext ctx) {
        if (ctx.QUEUE_TO() != null) {
            return new ActionRule(ActionType.QUEUE_TO);
        }
        return null;
    }

    @Override
    public SkillRule visitSkill(SkillContext ctx) {
        OidRule oidRule = visitEntity_identifier(ctx.entity_identifier());
        
        LevelConditionContext levelCtx = ctx.levelCondition();
        if (levelCtx == null) {
            return new SkillRule(oidRule.getOid());
        }
        ConditionRule conditionRule = visitLevelCondition(levelCtx);
        return new SkillRule(oidRule.getOid(), conditionRule.getCondition());
    }

    @Override
    public OidRule visitEntity_identifier(Entity_identifierContext ctx) {
        TerminalNode oidNode = ctx.NUMBER();
        if (oidNode == null) {
            oidNode = ctx.UUID_OR_HEXA();
        }
        return new OidRule(oidNode.getText());
    }

    @Override
    public WaitRule visitWaitRule(WaitRuleContext ctx) {
        return new WaitRule(toNumber(ctx.NUMBER()));
    }

    @Override
    public OrderRule visitOrder(OrderContext ctx) {
        return new OrderRule(toNumber(ctx.NUMBER()));
    }

    private int toNumber(TerminalNode node) {
        return Integer.parseInt(node.getText());
    }

    @Override
    public AndSkillsRule visitAndSkills(AndSkillsContext ctx) {
        List<SkillOrSetContext> skills = ctx.skillOrSet();
        List<ComparableOidSet> skillSet = skills.stream().map(this::getOidSet).toList();
        return new AndSkillsRule(skillSet);
    }

    private ComparableOidSet getOidSet(SkillOrSetContext ctx) {
        SkillSetRule node = visitSkillOrSet(ctx);
        return node.getSkillSetKey();
    }

    @Override
    public SkillSetRule visitSkillSet(SkillSetContext ctx) {
        List<SkillContext> skills = ctx.skill();
        List<SkillLevelCondition> skillLevelConditions = skills.stream().map(this::getSkillLevelCondition).toList();
        return new SkillSetRule(skillLevelConditions);
    }

    private SkillLevelCondition getSkillLevelCondition(SkillContext ctx) {
        SkillRule node = visitSkill(ctx);
        return node.getSkillLevelCondition();
    }

    @Override
    public SkillSetRule visitSkillOrSet(SkillOrSetContext ctx) {
        SkillSetContext skillSet = ctx.skillSet();
        if (skillSet != null) {
            return visitSkillSet(skillSet);
        } else {
            SkillLevelCondition skillLevelCondition = getSkillLevelCondition(ctx.skill());
            return new SkillSetRule(skillLevelCondition);
        }
    }

    @Override
    public ConditionRule visitLevelCondition(LevelConditionContext ctx) {
        BinaryOperatorContext binaryOperator = ctx.binaryOperator();
        SqlOperatorContext sqlOperator = ctx.sqlOperator();
        List<TerminalNode> numbers = ctx.NUMBER();

        Condition condition = null;
        if (binaryOperator != null) {
            BinaryOperatorRule binaryOperatorRule = visitBinaryOperator(binaryOperator);
            int level = this.toNumber(numbers.get(0));
            condition = new BinaryCondition(binaryOperatorRule.getOperator(), level);
            
        } else if (sqlOperator != null) {
            SqlOperatorRule sqlOperatorRule = visitSqlOperator(sqlOperator);
            int lowerLevel = this.toNumber(numbers.get(0));
            int upperLevel = this.toNumber(numbers.get(1));
            condition = new SqlCondition(sqlOperatorRule.getOperator(), lowerLevel, upperLevel);
        }
        return new ConditionRule(condition);
    }

    @Override
    public BinaryOperatorRule visitBinaryOperator(BinaryOperatorContext ctx) {
        String operator = ctx.getText();
        try {
            return new BinaryOperatorRule(operator);
        } catch (IllegalArgumentException e) {
            Token token = ctx.getStart();
            throw new ParseCancellationException("lvisitRoutingRuleSetine " + token.getLine() + ":" + token.getCharPositionInLine()
                    + ". Invalid operator: " + operator);
        }        
    }

    @Override
    public SqlOperatorRule visitSqlOperator(SqlOperatorContext ctx) {
        String operator = ctx.getText();
        try {
            return new SqlOperatorRule(operator);
        } catch (IllegalArgumentException e) {
            Token token = ctx.getStart();
            throw new ParseCancellationException("line " + 
                    token.getLine() + ":" + token.getCharPositionInLine() 
                    + ". Invalid operator: " + operator);
        }
    }
}
