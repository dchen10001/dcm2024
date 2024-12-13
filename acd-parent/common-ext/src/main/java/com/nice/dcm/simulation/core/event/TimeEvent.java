package com.nice.dcm.simulation.core.event;

import com.nice.dcm.simulation.core.IdEntity;

public interface TimeEvent<E extends IdEntity> extends IdEntity {
	
	/**
	 * return event occur time, which is seconds from the beginning.
	 * @return
	 */
	long getEventSeconds();
	
	EventType getType();
	
	E getEventEntity();
}
