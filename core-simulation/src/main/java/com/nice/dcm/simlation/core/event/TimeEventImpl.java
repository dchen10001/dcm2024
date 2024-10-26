package com.nice.dcm.simlation.core.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TimeEventImpl implements TimeEvent, Comparable<TimeEvent> {
	private final long id;
	private final EventType eventType;
	private long timestamp;
	
	public TimeEventImpl(long id, long timestamp, EventType eventType) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.eventType = eventType;
	}
	
	public TimeEventImpl(long timestamp, EventType eventType) {
		super();
		this.id = TimeEvent.generateId();
		this.timestamp = timestamp;
		this.eventType = eventType;
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
		return eventType;
	}

	@Override
	public int compareTo(TimeEvent o) {
		long ret = this.timestamp - o.getTimestamp();
		if (ret == 0) {
			return 0;
		}
		return ret > 0 ? 1 : -1;
	}
}
