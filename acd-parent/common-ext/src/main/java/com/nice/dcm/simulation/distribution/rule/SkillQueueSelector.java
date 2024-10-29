package com.nice.dcm.simulation.distribution.rule;

import java.util.concurrent.atomic.AtomicLong;

/**
 * interface of ACD skill queue.
 */
public interface SkillQueueSelector extends AndSkillLevelConditions {
	final long INVALID_TIMESTAMP = -1;
	final AtomicLong idGenerator = new AtomicLong(0);
	
	/*
	 * return a unique id of skill queue.
	 */
	int getId();
	
	/*
	 * return a channel id of the skill queue
	 */
	int getChannelId();
	
	/*
	 * if it is true, contact waiting time must be recorded.
	 */
	boolean isRequiredWaitingTime();
	
	static long generateId() {
		return idGenerator.getAndIncrement();
	}
	
	static void reSetId() {
		idGenerator.set(0);
	}	
}
