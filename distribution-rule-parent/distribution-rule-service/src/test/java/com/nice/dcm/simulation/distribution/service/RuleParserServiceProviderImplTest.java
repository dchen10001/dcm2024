package com.nice.dcm.simulation.distribution.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RuleParserServiceProviderImplTest {
	@Test
	void testCreateRuleParserService() {
		RuleParserServiceProviderImpl ruleParserServiceProviderImpl = new RuleParserServiceProviderImpl();
		RuleParserService ruleParserService = ruleParserServiceProviderImpl.createRuleParserService();
		assertNotNull(ruleParserService);
		assertEquals(RuleParserServiceImpl.class.getName(), ruleParserService.getClass().getName());
	}
}
