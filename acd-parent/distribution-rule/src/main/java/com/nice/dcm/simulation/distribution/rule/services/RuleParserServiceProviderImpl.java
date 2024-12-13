package com.nice.dcm.simulation.distribution.rule.services;

import java.util.Set;

import com.nice.dcm.simulation.distribution.service.RuleParserService;
import com.nice.dcm.simulation.distribution.service.RuleParserServiceProvider;

public class RuleParserServiceProviderImpl implements RuleParserServiceProvider {
	@Override
	public RuleParserService createRuleParserService(Set<String> skills) {
		return new RuleParserServiceImpl(skills);
	}
}
