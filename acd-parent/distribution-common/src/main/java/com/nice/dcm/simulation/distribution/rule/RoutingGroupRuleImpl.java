package com.nice.dcm.simulation.distribution.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingGroupRuleImpl implements RoutingGroupRule {
	private final long waitAfterSeconds;
    private final List<RoutingRule> rules;
    
	public RoutingGroupRuleImpl(List<RoutingRule> rules) {
		this(0, rules);
	}
	
	public RoutingGroupRuleImpl(long waitAfterSeconds, List<RoutingRule> rules) {
		super();
		this.rules = sortRulesByPriority(rules) ;
		this.waitAfterSeconds = waitAfterSeconds;
	}

	private List<RoutingRule> sortRulesByPriority(List<RoutingRule> rules) {
		List<RoutingRule> newRules = new ArrayList<>(rules);
		Collections.sort(newRules, (o1, o2) -> o1.getPriority() - o2.getPriority());
		return newRules;
	}
}
