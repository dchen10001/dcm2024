package com.nice.dcm.simulation.distribution.rule.vistor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.simulation.distribution.node.ActionNodeImpl.ActionType;
import com.nice.dcm.simulation.distribution.node.QueueStatusNodeImpl.QueueStatus;
import com.nice.dcm.simulation.distribution.node.RoutingNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingRuleGroupNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingRuleSetNodeImpl;
import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRuleImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSetImpl;

public class SkillRuleVisitorImpl extends AbstractRuleVistorImpl {

	
	@Override
	public RoutingRuleSetNodeImpl visitRoutingRuleSet(RoutingRuleSetContext ctx) {
		RoutingRuleGroupNodeImpl groupRule = visitRoutingRuleGroup(ctx.routingRuleGroup());
		if(ctx.routingWaitingRuleGroup().isEmpty()) {
			RoutingRuleSetImpl ruleSet = new RoutingRuleSetImpl(groupRule.getRoutingGroupRule());
			return new RoutingRuleSetNodeImpl(ruleSet);
		}
		List<RoutingGroupRuleImpl> groupRules = ctx.routingWaitingRuleGroup().stream().map(c -> visitRoutingWaitingRuleGroup(c).getRoutingGroupRule()).toList();
		RoutingRuleSetImpl ruleSet = new RoutingRuleSetImpl(groupRule.getRoutingGroupRule(), groupRules);
		return new RoutingRuleSetNodeImpl(ruleSet);
	}

	@Override
	public RoutingRuleGroupNodeImpl visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
		RoutingGroupRuleImpl rule = visitRoutingRuleGroup(ctx.routingRuleGroup()).getRoutingGroupRule();
		int waitAfterSeconds = visitWaitRule(ctx.waitRule()).getWaitFor();
		rule.setWaitAfterSeconds(waitAfterSeconds);
		return new RoutingRuleGroupNodeImpl(rule);
	}

	@Override
	public RoutingRuleGroupNodeImpl visitRoutingRuleGroup(RoutingRuleGroupContext ctx) {
		List<RoutingNodeImpl> ruleNodes = ctx.routingRule().stream().map(this::visitRoutingRule).toList();
		List<RoutingRuleImpl> rules = ruleNodes.stream().map(RoutingNodeImpl::getRoutingRule).toList();
		return new RoutingRuleGroupNodeImpl(rules);
	}

	@Override
	public RoutingNodeImpl visitRoutingRule(RoutingRuleContext ctx) {
		ActionType action = visitRuleAction(ctx.ruleAction()).getAction();
		Set<AndSkillLevelConditions> skills = visitAndSkills(ctx.andSkills()).getSkills();
		int priority = Integer.parseInt(ctx.priority().getText());
		QueueStatus queueStatus = null;
		if (ctx.queue_status() != null) {
			queueStatus = visitQueue_status(ctx.queue_status()).getQueueStatus();
		}
		return new RoutingNodeImpl(action, queueStatus, skills, priority);
	}
}
