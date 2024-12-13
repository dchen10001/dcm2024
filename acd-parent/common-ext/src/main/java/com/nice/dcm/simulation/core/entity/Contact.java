package com.nice.dcm.simulation.core.entity;

import com.nice.dcm.simulation.core.IdEntity;

public interface Contact extends IdEntity {
	/**
	 *
	 * @return channel id of the contact
	 */
	int getChannelId();
	
	/**
	 * 
	 * @return contact type oid of the contact
	 */
	String getContactTypeOid();
	
	/**
	 * 
	 * @return the arriving time in seconds of the contact
	 */
	long getArrivingTime();
	
	/**
	 * 
	 * @return the average abandoned time of the contact.
	 */
	long getAbandonedTime();
}
