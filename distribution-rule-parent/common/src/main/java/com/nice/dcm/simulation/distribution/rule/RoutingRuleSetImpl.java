package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Routing rule set implementation.
 * 
 * @see RoutingRuleSet
 * 
 * @author David Chen
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingRuleSetImpl implements RoutingRuleSet {
	private final RoutingGroupRule groupRule;
	private final List<RoutingGroupRule> groupRules;

	public RoutingRuleSetImpl(RoutingGroupRule groupRule) {
		this(groupRule, List.of());
	}
	
	public RoutingRuleSetImpl(@NonNull RoutingGroupRule groupRule, List<RoutingGroupRule> groupRules) {
		super();		
		this.groupRule = groupRule;
		this.groupRules = groupRules == null ? List.of() : groupRules;
		
		for (RoutingGroupRule rule : this.groupRules) {
			if (rule.getWaitAfterSeconds() <= 0) {
				throw new IllegalArgumentException("waitAfterSeconds must be greater than zero in list of group rules.");
			}
		}
	}
}
