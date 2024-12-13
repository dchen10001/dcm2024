package com.nice.dcm.simulation.core.event.service;

import java.util.List;

import com.nice.dcm.simulation.core.IdEntity;
import com.nice.dcm.simulation.core.event.TimeEvent;

public interface EventGenerator<E extends IdEntity> {
	long getEarliestEventTime();
	
	List<TimeEvent<E>> getTimeEvents(long currentTime);
}
