package com.nice.dcm.simulation.distribution.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingGroupRuleImpl {
	private long waitAfterSeconds;
    private final  List<RoutingRuleImpl> rules;
    
	public RoutingGroupRuleImpl(List<RoutingRuleImpl> rules) {
		this(0, rules);
	}
	
	public RoutingGroupRuleImpl(long waitAfterSeconds, List<RoutingRuleImpl> rules) {
		super();
		this.rules = sortRulesByPriority(rules) ;
		setWaitAfterSeconds(waitAfterSeconds);
	}
	
	public void setWaitAfterSeconds(long waitAfterSeconds) {
		assert waitAfterSeconds >= 0 : "The waitAfterSeconds must be greater than or equal to 0";
    	this.waitAfterSeconds = waitAfterSeconds;
    }
	
	private List<RoutingRuleImpl> sortRulesByPriority(List<RoutingRuleImpl> rules) {
		List<RoutingRuleImpl> newRules = new ArrayList<>(rules);
		Collections.sort(newRules, (o1, o2) -> o1.getPriority() - o2.getPriority());
		return newRules;
	}
}
