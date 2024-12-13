package com.nice.dcm.simulation.acd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RuleParserServiceFactoryTest {
	@Test
	void testGetProviderName() {
		String providerName = RuleParserServiceFactory.getProviderName();		
		Assertions.assertEquals(RuleParserServiceFactory.DEFAULT_PROVIDER, providerName);
		
		System.setProperty(RuleParserServiceFactory.DEFAULT_PROVIDER_KEY, "dummy");
		
		providerName = RuleParserServiceFactory.getProviderName();
		Assertions.assertEquals("dummy", providerName);
	}
}
