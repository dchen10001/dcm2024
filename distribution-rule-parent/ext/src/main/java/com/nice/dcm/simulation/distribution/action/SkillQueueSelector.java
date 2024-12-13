package com.nice.dcm.simulation.distribution.action;

import java.util.concurrent.atomic.AtomicInteger;

import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

/**
 * interface of ACD skill queue, extends from SkillSetSelector.
 * 
 * @author David Chen 
 */
public interface SkillQueueSelector extends SkillSetSelector {

	final AtomicInteger idGenerator = new AtomicInteger(0);
	
	/*
	 * return a unique id of skill queue.
	 */
	int getId();
	
	static int generateId() {
		return idGenerator.getAndIncrement();
	}
	
	static void resetId() {
		idGenerator.set(0);
	}		
}
