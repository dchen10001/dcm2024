package com.nice.dcm.simulation.distribution.service;

public class RuleParserServiceProviderImpl implements RuleParserServiceProvider {

	@Override
	public RuleParserService createRuleParserService() {
		return new RuleParserServiceImpl();
	}
}
