package com.nice.dcm.simulation.distribution.service;

import com.nice.dcm.simulation.distribution.action.CTDistributionRules;
import com.nice.dcm.simulation.distribution.action.CTRuleSetActions;

/**
 * interface of rule parser service.
 * 
 * The implementation of this service is responsible for parsing the rule script, and return the action.
 * @author David Chen
 */
public interface RuleParserService {
	CTRuleSetActions parserRules(CTDistributionRules ctDistributionRules);
	
	/**
	 * Parse the rule script and return the action.
	 * 
	 * @param contactTypeOid
	 * @param ruleScript
	 * @return
	 */
	//QueueToGroupSetAction parserRule(String contactTypeOid, String ruleScript);
	
	/**
	 * Get the action for the given contact type oid.
	 * 
	 * @param contactTypeOid
	 * @return
	 */
	//QueueToGroupSetAction getQueueToGroupSetAction(String contactTypeOid);
	
	/**
	 * Get the skill queue selectors.
	 * 
	 * @return
	 */
	//List<SkillQueueSelector> getSkillQueueSelectors();
}
