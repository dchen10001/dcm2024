package com.nice.dcm.simulation.core.event;

import java.util.concurrent.atomic.AtomicLong;

public interface TimeEvent {
	final long INVALID_TIMESTAMP = -1;
	final AtomicLong idGenerator = new AtomicLong(0);
	
	/**
	 * unique Id
	 * @return
	 */
	long getId();
	
	/**
	 * return event occur time, which is seconds from the beginning.
	 * @return
	 */
	long getEventSeconds();
	
	
	static long generateId() {
		return idGenerator.getAndIncrement();
	}
	
	static void reSetId() {
		idGenerator.set(0);
	}
}
