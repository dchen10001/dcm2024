package com.nice.dcm.simulation.core.event;

import com.nice.dcm.simulation.core.IdEntity;
import com.nice.dcm.simulation.core.SequenceGenerator;

public class TimeEventImpl<E extends IdEntity> implements TimeEvent<E> {

	private final long id;
	private final EventType eventType;
	private final long eventSeconds;
	private final E eventEntity;
	
	public TimeEventImpl(long eventSeconds, EventType eventType, E eventEntity) {
		super();
		this.id = SequenceGenerator.generateId(TimeEvent.class);
		this.eventSeconds = eventSeconds;
		this.eventType = eventType;
		this.eventEntity = eventEntity;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public long getEventSeconds() {
		return eventSeconds;
	}

	@Override
	public EventType getType() {
		return eventType;
	}
	
	@Override
	public E getEventEntity() {
		return this.eventEntity;
	}
}
