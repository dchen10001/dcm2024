package com.nice.dcm.distribution.rule.client;

import java.nio.file.ProviderNotFoundException;
import java.util.ServiceLoader;

import com.nice.dcm.simulation.distribution.service.RuleParserService;
import com.nice.dcm.simulation.distribution.service.RuleParserServiceProvider;

public class RuleParserServiceFactory {
	public static final String DEFAULT_PROVIDER = "com.nice.dcm.simulation.distribution.service.RuleParserServiceProviderImpl";

	public static final String DEFAULT_PROVIDER_KEY = "RuleParserServiceProvider";

	private RuleParserServiceFactory() {

	}

	/**
	 * Get RuleParserService implementation class name from env first, then system
	 * property or return the default value
	 * 
	 * @param skills
	 * @return RuleParserService implementation class name
	 */

	public static RuleParserService getRuleParserService() {
		RuleParserServiceProvider provider = getRuleParserServiceProvider();
		return provider.createRuleParserService();
	}

	/**
	 * Get RuleParserServiceProvider implementation class name from env first, then
	 * system property or return the default value
	 * 
	 * @return RuleParserService implementation class name
	 */

	protected static RuleParserServiceProvider getRuleParserServiceProvider() {
		String providerName = getProviderName();
		return getRuleParserServiceProvider(providerName);
	}

	/**
	 * Get RuleParserServiceProider with ServiceLoader by providerName
	 * 
	 * @param providerName
	 * @return
	 */
	protected static RuleParserServiceProvider getRuleParserServiceProvider(String providerName) {
		ServiceLoader<RuleParserServiceProvider> loader = ServiceLoader.load(RuleParserServiceProvider.class);
		for (RuleParserServiceProvider provider : loader) {
			if (providerName.equals(provider.getClass().getName())) {
				return provider;
			}
		}
		throw new ProviderNotFoundException("RuleParserServiceProvider " + providerName + " not found");
	}

	/**
	 * Get RuleParserService implementation class name from env first, then system
	 * property or return the default value
	 * 
	 * @return RuleParserService implementation class name
	 */

	protected static String getProviderName() {
		String providerName = System.getenv(DEFAULT_PROVIDER_KEY);
		if (providerName == null || providerName.isBlank()) {
			providerName = System.getProperty(DEFAULT_PROVIDER_KEY, DEFAULT_PROVIDER);
		}
		return providerName;
	}
}
