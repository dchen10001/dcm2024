package com.nice.dcm.distribution.parser;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.Agent_statusContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.AndSkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.OrderContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillOrSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.ActionRule.ActionType;
import com.nice.dcm.distribution.parser.rule.AgentStatusNode;
import com.nice.dcm.distribution.parser.rule.AgentStatusNode.AgentStatus;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.ComparableOidSet;
import com.nice.dcm.distribution.parser.rule.Node;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.RoutingRule;
import com.nice.dcm.distribution.parser.rule.RoutingRuleGroup;
import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.SkillSetRule;
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
        Set<RoutingRuleGroup> ruleGroups = new TreeSet<>();
        // add first rule group
        ruleGroups.add(visitRoutingRuleGroup(ctx.routingRuleGroup()));

        for (RoutingWaitingRuleGroupContext r : ctx.routingWaitingRuleGroup()) {
            ruleGroups.add(visitRoutingWaitingRuleGroup(r));
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
        List<RoutingRule> rules = ctx.routingRule().stream().map(this::visitRoutingRule).toList();
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
            AgentStatusNode agentStatusNode = visitAgent_status(ctx.agent_status());
            agentStatus = agentStatusNode.getAgentStatus();
        }
        return new RoutingRule(action, skills, order.getPriority(), agentStatus);
    }

    @Override
    public AgentStatusNode visitAgent_status(Agent_statusContext ctx) {
        if (ctx.LEAST_BUSY() != null) {
            return new AgentStatusNode(AgentStatus.LEAST_BUSY);
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
        return new SkillRule(oidRule.getOid());
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
        List<String> skillOids = skills.stream().map(this::getOid).toList();
        return new SkillSetRule(skillOids);
    }

    private String getOid(SkillContext ctx) {
        SkillRule node = visitSkill(ctx);
        return node.getSkillOid();
    }

    @Override
    public SkillSetRule visitSkillOrSet(SkillOrSetContext ctx) {
        SkillSetContext skillSet = ctx.skillSet();
        if (skillSet != null) {
            return visitSkillSet(skillSet);
        } else {
            String skillOid = getOid(ctx.skill());
            return new SkillSetRule(skillOid);
        }
    }
}
