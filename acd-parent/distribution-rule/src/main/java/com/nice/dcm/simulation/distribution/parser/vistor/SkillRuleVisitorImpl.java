package com.nice.dcm.simulation.distribution.parser.vistor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.simulation.distribution.node.EntityIdentifierNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingRuleGroupNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingRuleSetNodeImpl;
import com.nice.dcm.simulation.distribution.rule.ActionType;
import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.QueueStatus;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSetImpl;

import lombok.NonNull;

public class SkillRuleVisitorImpl extends AbstractRuleVistorImpl {
	private static final Logger logger = LoggerFactory.getLogger(SkillRuleVisitorImpl.class);
	
	private final Set<String> skills;
	
	private final Set<String> noExistSkills = new HashSet<>();
	
	public SkillRuleVisitorImpl() {
		this(Set.of());
	}
	
	public SkillRuleVisitorImpl(@NonNull Set<String> skills) {
		this.skills = skills;
	}
	
	@Override
	public RoutingRuleSetNodeImpl visitRoutingRuleSet(RoutingRuleSetContext ctx) {
		RoutingRuleGroupNodeImpl groupRule = visitRoutingRuleGroup(ctx.routingRuleGroup());
		if(ctx.routingWaitingRuleGroup().isEmpty()) {
			RoutingRuleSetImpl ruleSet = new RoutingRuleSetImpl(groupRule.getRoutingGroupRule());
			return new RoutingRuleSetNodeImpl(ruleSet);
		}
		List<RoutingGroupRule> groupRules = ctx.routingWaitingRuleGroup().stream().map(c -> visitRoutingWaitingRuleGroup(c).getRoutingGroupRule()).toList();
		RoutingRuleSetImpl ruleSet = new RoutingRuleSetImpl(groupRule.getRoutingGroupRule(), groupRules);
		return new RoutingRuleSetNodeImpl(ruleSet);
	}

	@Override
	public RoutingRuleGroupNodeImpl visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
		int waitAfterSeconds = visitWaitRule(ctx.waitRule()).getWaitFor();
		RoutingGroupRule rule = visitRoutingRuleGroup(ctx.routingRuleGroup(), waitAfterSeconds).getRoutingGroupRule();
		return new RoutingRuleGroupNodeImpl(rule);
	}

	public RoutingRuleGroupNodeImpl visitRoutingRuleGroup(RoutingRuleGroupContext ctx, int waitAfterSeconds) {
		List<RoutingRule> rules = getRules(ctx);
		return new RoutingRuleGroupNodeImpl(waitAfterSeconds, rules);
	}

	@Override
	public RoutingRuleGroupNodeImpl visitRoutingRuleGroup(RoutingRuleGroupContext ctx) {
		List<RoutingRule> rules = getRules(ctx);
		return new RoutingRuleGroupNodeImpl(rules);
	}

	private List<RoutingRule> getRules(RoutingRuleGroupContext ctx) {
		List<RoutingNodeImpl> ruleNodes = ctx.routingRule().stream().map(this::visitRoutingRule).toList();
		return ruleNodes.stream().map(RoutingNodeImpl::getRoutingRule).toList();
		
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
	
    /**
     * Visit an entity identifier node
     * 
     * @param ctx
     * @return
     */
    @Override
    public EntityIdentifierNodeImpl visitEntity_identifier(Entity_identifierContext ctx) {
        EntityIdentifierNodeImpl idNode = super.visitEntity_identifier(ctx);
        String oid = idNode.getEntityIdentifier();
		if (!skills.contains(oid)) {
			logger.warn("Skill {} does not exist", oid);
        	noExistSkills.add(oid);
        }
        return idNode;
    }
    
	public Set<String> getNoExistSkills() {
		return noExistSkills;
	}
}
