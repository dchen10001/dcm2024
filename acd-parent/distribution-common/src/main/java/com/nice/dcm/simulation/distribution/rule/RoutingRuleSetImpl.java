package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingRuleSetImpl implements RoutingRuleSet {
	private final RoutingGroupRule groupRule;
	private final List<RoutingGroupRule> groupRules;
	
	public RoutingRuleSetImpl(RoutingGroupRule groupRule) {
		this(groupRule, List.of());
	}
	
	public RoutingRuleSetImpl(RoutingGroupRule groupRule, List<RoutingGroupRule> groupRules) {
		super();		
		this.groupRule = groupRule;
		this.groupRules = groupRules;
	}
}
