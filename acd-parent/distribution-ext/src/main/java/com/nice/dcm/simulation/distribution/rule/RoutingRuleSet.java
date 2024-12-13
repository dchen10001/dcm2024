package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

public interface RoutingRuleSet {
	public RoutingGroupRule getGroupRule();
	public List<RoutingGroupRule> getGroupRules();
}
