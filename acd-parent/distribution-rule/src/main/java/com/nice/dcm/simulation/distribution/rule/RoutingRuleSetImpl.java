package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingRuleSetImpl {
	private final RoutingGroupRuleImpl groupRule;
	private final List<RoutingGroupRuleImpl> groupRules;
	
	public RoutingRuleSetImpl(RoutingGroupRuleImpl groupRule) {
		this(groupRule, List.of());
	}
	
	public RoutingRuleSetImpl(RoutingGroupRuleImpl groupRule, List<RoutingGroupRuleImpl> groupRules) {
		super();
		assert groupRule != null && groupRules != null : "The group rule and group rules must not be null";
		assert groupRule.getWaitAfterSeconds() == 0 : "The group rule must have 0 waitAfter";
		
		this.groupRule = groupRule;
		this.groupRules = groupRules;
	}
}
