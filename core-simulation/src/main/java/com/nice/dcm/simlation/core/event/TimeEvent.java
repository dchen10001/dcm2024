package com.nice.dcm.simlation.core.event;

public interface TimeEvent {
	long getId();
	long getTimestamp();
	EventType getType();
}
