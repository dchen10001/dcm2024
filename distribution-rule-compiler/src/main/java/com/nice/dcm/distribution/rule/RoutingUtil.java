package com.nice.dcm.distribution.rule;

import java.util.Set;

import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;

public class RoutingUtil {
	public static boolean applyRuleSet(RoutingRuleSet routingRuleSet, 
			Set<String> skillOidSet, long waitingTime) {
		return routingRuleSet.apply(skillOidSet, waitingTime);
	}
}
