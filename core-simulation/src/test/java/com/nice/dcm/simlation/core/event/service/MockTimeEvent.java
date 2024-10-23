package com.nice.dcm.simlation.core.event.service;

import com.nice.dcm.simlation.core.event.EventType;
import com.nice.dcm.simlation.core.event.TimeEvent;

public class MockTimeEvent implements TimeEvent {
	private static long IDNUM = 0;
	
	private final long id;
	private final long timestamp;
	
	public MockTimeEvent(long timestamp) {
		this.id = IDNUM++;
		this.timestamp = timestamp;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public EventType getType() {
		return EventType.CONTACT_ARRIVAL;
	}

	@Override
	public String toString() {
		return "MockTimeEvent [id=" + id + ", timestamp=" + timestamp + "]";
	}
}
