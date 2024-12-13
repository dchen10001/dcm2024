package com.nice.dcm.simulation.distribution.action;

import java.util.List;
import java.util.Set;

public interface CTRuleSetActions {
	/**
	 * get the contact type oids that have valid distribution rule
	 * 
	 * @return list of contact types
	 */
	List<String> getContactTypes();
	
	/**
	 * get the contact type oids that have invalid distribution rule
	 * 
	 * @return list of contact types
	 */
	List<String> getErrorContactTypes();
	
	/**
	 * get the contact type oids that the distribution rule has invalid skills
	 * @return
	 */
	List<String> getInvalidSkillsContactTypes();

	/**
	 * get the action for the contact type
	 * 
	 * @param contactTypeOid
	 * @return the action, or null if the contact type oid is not found
	 */
	QueueToGroupSetAction getQueueToGroupSetAction(String contactTypeOid);
	
	/**
	 * get the invalid skills for the contact type
	 * 
	 * @param contactTypeOid
	 * @return the invalid skills, or null if the contact type oid is not found
	 */
	Set<String> getInvalidSkills(String contactTypeOid);
	
	/**
	 * get the error message for the contact type
	 * 
	 * @param contactTypeOid
	 * @return the error message, or null if the contact type oid is not found
	 */
	String getCTError(String contactTypeOid);
	
	/**
	 * get unique skill queue selectors from all the actions
	 * 
	 * @return the skill queue selectors
	 */
	List<SkillQueueSelector> getSkillQueueSelectors();
}
