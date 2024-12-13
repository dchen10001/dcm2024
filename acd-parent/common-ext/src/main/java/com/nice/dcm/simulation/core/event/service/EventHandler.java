package com.nice.dcm.simulation.core.event.service;

import com.nice.dcm.simulation.core.IdEntity;
import com.nice.dcm.simulation.core.event.EventType;

public interface EventHandler<E extends IdEntity> {
	void onEvent(E element, EventType type, long eventSeconds);
}
