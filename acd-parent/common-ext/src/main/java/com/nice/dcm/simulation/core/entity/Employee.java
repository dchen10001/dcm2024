package com.nice.dcm.simulation.core.entity;

import java.util.List;
import java.util.Set;

import com.nice.dcm.simulation.core.IdEntity;

public interface Employee extends IdEntity {
	/**
	 * 
	 * @return oid of the employee;
	 */
	String getOid();
	
	/**
	 * 
	 * @return the current skill level of the employee
	 */
	Set<SkillLevel> getCurrentSkills(long currentTime);
	
	/**
	 * 
	 * @return the list of contacts that the employee is handling
	 */
	List<Contact> getHandlingContacts();
}
