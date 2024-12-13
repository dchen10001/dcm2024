package com.nice.dcm.simulation.acd.service;

import java.nio.file.ProviderNotFoundException;
import java.util.ServiceLoader;
import java.util.Set;

import com.nice.dcm.simulation.distribution.service.RuleParserService;
import com.nice.dcm.simulation.distribution.service.RuleParserServiceProvider;

import lombok.NonNull;

/**
 * A Factory class to RuleParserService implementation class  
 */
public class RuleParserServiceFactory {
	public static final String DEFAULT_PROVIDER = "com.nice.dcm.simulation.distribution.rule.services.RuleParserServiceProviderImpl";
	
	public static final String DEFAULT_PROVIDER_KEY= "RuleParserServiceProvider";
	
	private RuleParserServiceFactory() {
		
	}
	
	public static RuleParserService getRuleParserService(@NonNull Set<String> skills) {
		RuleParserServiceProvider provider = getRuleParserServiceProvider();
		return provider.createRuleParserService(skills);
	}
	
	protected static RuleParserServiceProvider getRuleParserServiceProvider() {
		String providerName = getProviderName();
		return getRuleParserServiceProvider(providerName);
	}
	
	protected static RuleParserServiceProvider getRuleParserServiceProvider(String providerName) {
		ServiceLoader<RuleParserServiceProvider> loader = ServiceLoader.load(RuleParserServiceProvider.class);
		for(RuleParserServiceProvider provider : loader) {
			if (providerName.equals(provider.getClass().getName())) {
                return provider;
            }
		}
		throw new ProviderNotFoundException("RuleParserServiceProvider " + providerName + " not found");
	}
	
	protected static String getProviderName() {
		String providerName = System.getenv(DEFAULT_PROVIDER_KEY);
		if(providerName == null || providerName.isBlank()) {
			providerName = System.getProperty(DEFAULT_PROVIDER_KEY, DEFAULT_PROVIDER);
		}
		return providerName;
	}	
}
