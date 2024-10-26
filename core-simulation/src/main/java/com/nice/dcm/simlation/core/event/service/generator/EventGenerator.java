package com.nice.dcm.simlation.core.event.service.generator;

import java.util.List;

import com.nice.dcm.simlation.core.event.TimeEvent;

public interface EventGenerator {
	//get the earliest event time in this generator
	long getEventTime();
	
	//get the list of events that occur at the given time
	List<TimeEvent> getEvents(long currentTime);
}
