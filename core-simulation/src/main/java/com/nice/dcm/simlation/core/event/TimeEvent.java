package com.nice.dcm.simlation.core.event;

import java.util.concurrent.atomic.AtomicLong;

public interface TimeEvent {
	final long INVALID_TIMESTAMP = -1;
	final AtomicLong idGenerator = new AtomicLong(0);
	
	long getId();
	long getTimestamp();
	EventType getType();
	
	static long generateId() {
		return idGenerator.getAndIncrement();
	}
	
	static void reSetId() {
		idGenerator.set(0);
	}
}
