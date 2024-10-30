package com.nice.dcm.simulation.distribution.rule;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * interface of ACD skill queue.
 */
public interface SkillQueueSelector extends AndSkillLevelConditions {
	final long INVALID_TIMESTAMP = -1;
	final AtomicInteger idGenerator = new AtomicInteger(0);
	
	/*
	 * return a unique id of skill queue.
	 */
	int getId();
	
	/*
	 * return a channel id of the skill queue
	 */
	int getChannelId();
	
	static int generateId() {
		return idGenerator.getAndIncrement();
	}
	
	static void reSetId() {
		idGenerator.set(0);
	}	
}
