package com.nice.dcm.simulation.distribution.service;

import java.util.Set;

/**
 * Interface for RuleParserServiceProvider, which is used to create RuleParserService.
 * 
 * It will be used by Java ServiceLoader framework to load the implementation of RuleParserService.
 * 
 * @see RuleParserService
 * 
 * @author David Chen
 */
public interface RuleParserServiceProvider {
	/**
	 * Create RuleParserService with the given skills.
	 * skills is used to valid the skill in the rule.
	 * 
	 * @param skills the skills for the RuleParserService
	 * @return the RuleParserService
	 */
	public RuleParserService createRuleParserService();
}
