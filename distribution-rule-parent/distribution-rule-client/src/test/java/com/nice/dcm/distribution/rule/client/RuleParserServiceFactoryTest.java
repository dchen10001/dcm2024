package com.nice.dcm.distribution.rule.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.ProviderNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.nice.dcm.simulation.distribution.service.RuleParserService;
import com.nice.dcm.simulation.distribution.service.RuleParserServiceProvider;

import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
class RuleParserServiceFactoryTest {
	@SystemStub
	private EnvironmentVariables environmentVariables;

	@BeforeEach
	void setUp() {
		System.clearProperty(RuleParserServiceFactory.DEFAULT_PROVIDER_KEY);
	}
	
	@Test
	void testGetProviderNameEnv() {
		System.setProperty(RuleParserServiceFactory.DEFAULT_PROVIDER_KEY, "systemValue");
		environmentVariables.set(RuleParserServiceFactory.DEFAULT_PROVIDER_KEY,  "envValue");

		String providcerName = RuleParserServiceFactory.getProviderName();
		assertEquals("envValue", providcerName);
	}
	
	@Test
	void testGetProviderNameSystem() {
		System.setProperty(RuleParserServiceFactory.DEFAULT_PROVIDER_KEY, "systemValue");
		
		String providcerName = RuleParserServiceFactory.getProviderName();
		assertEquals("systemValue", providcerName);
	}
	
	@Test
	void testGetProviderNameDefault() {
		String providcerName = RuleParserServiceFactory.getProviderName();
		assertEquals("com.nice.dcm.simulation.distribution.service.RuleParserServiceProviderImpl", providcerName);
	}
	
	@Test
	void testGetRuleParserServiceProvider() {
		RuleParserServiceProvider provider = RuleParserServiceFactory.getRuleParserServiceProvider("com.nice.dcm.simulation.distribution.service.RuleParserServiceProviderImpl");
		
		assertEquals("com.nice.dcm.simulation.distribution.service.RuleParserServiceProviderImpl", provider.getClass().getName());

		provider = RuleParserServiceFactory.getRuleParserServiceProvider();
		assertEquals("com.nice.dcm.simulation.distribution.service.RuleParserServiceProviderImpl", provider.getClass().getName());
		
		Exception thrown = assertThrows(ProviderNotFoundException.class,
				() -> RuleParserServiceFactory.getRuleParserServiceProvider("unknown"), "unknow provider");

		assertTrue(thrown.getMessage().contains("RuleParserServiceProvider unknown not found"));		
	}
	
	@Test
	void testGetRuleParserService() {
		RuleParserService service = RuleParserServiceFactory.getRuleParserService();
		assertEquals("com.nice.dcm.simulation.distribution.service.RuleParserServiceImpl", service.getClass().getName());
	}
}
