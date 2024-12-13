package com.nice.dcm.simulation.distribution.service;

import java.util.Set;

public interface RuleParserServiceProvider {
	public RuleParserService createRuleParserService(Set<String> skills);
}
