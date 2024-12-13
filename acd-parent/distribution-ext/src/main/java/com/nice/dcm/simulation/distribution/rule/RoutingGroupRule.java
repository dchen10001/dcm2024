package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

public interface RoutingGroupRule {
	public long getWaitAfterSeconds();

	public List<RoutingRule> getRules();
}
