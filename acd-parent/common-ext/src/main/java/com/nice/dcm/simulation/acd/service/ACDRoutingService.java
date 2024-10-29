package com.nice.dcm.simulation.acd.service;

import java.util.Collection;

import com.nice.dcm.simulation.distribution.rule.SkillQueueSelector;

public interface ACDRoutingService {
	/**
	 * return the selector with the least waiting time from the collections
	 * @param selectors
	 * @return
	 */
	SkillQueueSelector getLeastBusyQueue(Collection<SkillQueueSelector> selectors);
	
	/**
	 * from the collection of skill queue selectors, find one of available agent oid
	 * @param skills
	 * @return agent oid if found, null if not found
	 */
	String findAvailableAgent(Collection<SkillQueueSelector> skillQueueSelectors);
	
	/**
	 * from the collection of skill queue selectors, find one of available agent oid
	 * @param skills
	 * @return agent oid if found, null if not found
	 */
	String findAvailableAgentByIds(Collection<Integer> skillQueueSelectorIds);
	
	/**
	 * from skill queue selectors, find one of available agent oid
	 * @param skills
	 * @return agent oid if found, null if not found
	 */	
	String findAvailableAgent(SkillQueueSelector skillQueueSelector);

	/**
	 * from skill queue selectors, find one of available agent oid
	 * @param skills
	 * @return agent oid if found, null if not found
	 */	
	String findAvailableAgentById(int skillQueueSelectorId);

}
