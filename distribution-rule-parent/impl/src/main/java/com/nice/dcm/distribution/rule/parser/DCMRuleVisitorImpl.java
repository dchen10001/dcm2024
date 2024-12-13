package com.nice.dcm.distribution.rule.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.distribution.rule.parser.node.EntityIdentifierNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.QueueStatusNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.RoutingRuleGroupNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.RoutingRuleNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.RoutingRuleSetNodeImpl;
import com.nice.dcm.simulation.distribution.rule.QueueStatus;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRuleImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSet;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSetImpl;
import com.nice.dcm.simulation.distribution.rule.RuleAction;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

public class DCMRuleVisitorImpl extends AbstractRuleVistorImpl {

	private final Set<String> skills;
	
	private final Set<String> noExistSkills = new HashSet<>();
	
	public DCMRuleVisitorImpl() {
		this(null);
	}
	
	public DCMRuleVisitorImpl(Set<String> skills) {
		this.skills = skills == null ? Set.of() : skills;
	}
	
	/**
	 * Visit a routing rule set node
	 * 
	 * @param ctx
	 * @return
	 */
	@Override
	public RoutingRuleSetNodeImpl visitRoutingRuleSet(RoutingRuleSetContext ctx) {
		RoutingGroupRule groupRule = visitRoutingRuleGroup(ctx.routingRuleGroup()).getRoutingGroupRule();

		List<RoutingWaitingRuleGroupContext> waitGroupRules = ctx.routingWaitingRuleGroup();
		RoutingRuleSet routingRuleSet = null;
		if (waitGroupRules.isEmpty()) {
			routingRuleSet =  new RoutingRuleSetImpl(groupRule);
		} else {
			List<RoutingGroupRule> groupRules = waitGroupRules.stream()
					.map(c -> visitRoutingWaitingRuleGroup(c).getRoutingGroupRule()).toList();
			routingRuleSet = new RoutingRuleSetImpl(groupRule, groupRules);
		}
		return new RoutingRuleSetNodeImpl(routingRuleSet);
	}

	/**
	 * Visit a routing waiting rule group node
	 * 
	 * @param ctx
	 * @return
	 */
	@Override
	public RoutingRuleGroupNodeImpl visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
		long waitAfterSeconds = visitWaitRule(ctx.waitRule()).getWaitFor();
		RoutingGroupRule rule = toRoutingGroupRule(ctx.routingRuleGroup(), waitAfterSeconds);
		return new RoutingRuleGroupNodeImpl(rule);
	}

	/**
	 * Visit a routing rule group node
	 * 
	 * @param ctx
	 * @return
	 */
	@Override
	public RoutingRuleGroupNodeImpl visitRoutingRuleGroup(RoutingRuleGroupContext ctx) {
		RoutingGroupRule rule = toRoutingGroupRule(ctx, 0l);
		return new RoutingRuleGroupNodeImpl(rule);
	}
	
	protected RoutingGroupRule toRoutingGroupRule(RoutingRuleGroupContext ctx, long waitAfterSeconds) {
		List<RoutingRule> rules = ctx.routingRule().stream().map(this::toRoutingRule).toList();
		return new RoutingGroupRuleImpl(waitAfterSeconds, rules);
	}

	/**
	 * Visit a routing rule node
	 * 
	 * @param ctx
	 * @return
	 */
	@Override
	public RoutingRuleNodeImpl visitRoutingRule(RoutingRuleContext ctx) {
        RoutingRule rule = toRoutingRule(ctx);
		return new RoutingRuleNodeImpl(rule);
	}
	
	protected RoutingRule toRoutingRule(RoutingRuleContext ctx) {
		RuleAction action = visitRuleAction(ctx.ruleAction()).getAction();
		List<SkillSetSelector> selectors = visitOrSkills(ctx.orSkills()).getSelectors();
		int priority = visitPriority(ctx.priority()).getPriority();
		
		QueueStatus queueStatus = null;
		if (selectors.size() > 1) {
			QueueStatusNodeImpl queueNode = visitQueue_status(ctx.queue_status());
			queueStatus = queueNode != null ? queueNode.getQueueStatus() : null;
		}
		return new RoutingRuleImpl(action, queueStatus, selectors, priority);
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
