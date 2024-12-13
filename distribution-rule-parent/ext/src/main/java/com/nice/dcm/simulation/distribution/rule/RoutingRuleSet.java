package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

/**
 * Routing rule set interface - the top level of distribution rule, contains at least one routing group rule.
 * And a List of routing group rules with wait time.
 * 
 * During execution, apply the group rule first if the contact is arriving. 
 * 
 * If the contact is already in the queue, use the waiting time to determine the routing rule.
 * 
 * @author David Chen
 */
public interface RoutingRuleSet {
	public RoutingGroupRule getGroupRule();
	public List<RoutingGroupRule> getGroupRules();
}
