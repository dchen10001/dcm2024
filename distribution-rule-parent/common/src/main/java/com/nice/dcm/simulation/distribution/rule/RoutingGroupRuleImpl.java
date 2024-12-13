package com.nice.dcm.simulation.distribution.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Routing group rule implementation.
 * 
 * rules are sorted by priority
 * 
 * @see RoutingGroupRule
 * 
 * @author David Chen
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingGroupRuleImpl implements RoutingGroupRule {
	private final long waitAfterSeconds;
    private final List<RoutingRule> rules;
    
    public RoutingGroupRuleImpl(RoutingRule rule) {
    	this(0, rule);
    }
    
	public RoutingGroupRuleImpl(long waitAfterSeconds, @NonNull RoutingRule rule) {
		super();

		if (waitAfterSeconds < 0) {
			throw new IllegalArgumentException("waitAfterSeconds must be greater than or equal to zero");
		}
		
		this.rules = List.of(rule) ;
		this.waitAfterSeconds = waitAfterSeconds;
	}
    
	public RoutingGroupRuleImpl(List<RoutingRule> rules) {
		this(0, rules);
	}
	
	public RoutingGroupRuleImpl(long waitAfterSeconds, @NonNull List<RoutingRule> rules) {
		super();
		if (waitAfterSeconds < 0) {
			throw new IllegalArgumentException("waitAfterSeconds must be greater than or equal to zero");
		}
		
		if (rules.isEmpty()) {
			throw new IllegalArgumentException("rules is empty");
		}
		rules = optimizeRules(rules);
		this.rules = sortRulesByPriority(rules) ;
		this.waitAfterSeconds = waitAfterSeconds;
	}

	private List<RoutingRule> sortRulesByPriority(List<RoutingRule> rules) {
		List<RoutingRule> newRules = new ArrayList<>(rules);
		Collections.sort(newRules, Comparator.comparing(RoutingRule::getPriority));
		return newRules;
	}
	
	private List<RoutingRule> optimizeRules(List<RoutingRule> rules) {
		Map<Integer, List<RoutingRule>> rulesByProiority = rules.stream().collect(Collectors.groupingBy(RoutingRule::getPriority));
		rules = new ArrayList<>();
 		for (Map.Entry<Integer, List<RoutingRule>> entry : rulesByProiority.entrySet()) {
			if (entry.getValue().size() > 1) {
				List<RoutingRule> result = mergeRules(entry.getValue());
				rules.addAll(result);
			} else {
				rules.add(entry.getValue().get(0));
			}
		}
		return rules;
    }
	
	private List<RoutingRule> mergeRules(List<RoutingRule> rules) {
		List<RoutingRule> result = new ArrayList<>();
		Set<SkillSetSelector> selectors = new HashSet<>();
		RoutingRule defaultRule = null;
		for (RoutingRule rule : rules) {
			if (rule.isLeastBusyOf()) {
				result.add(rule);
			} else {
				defaultRule = rule;
				selectors.addAll(rule.getSelectors());
			}
		}
		
		if (defaultRule != null) {
            result.add(new RoutingRuleImpl(defaultRule.getAction(), defaultRule.getQueueStatus(), new ArrayList<>(selectors), defaultRule.getPriority()));
		}
		return result;
	}	
}
