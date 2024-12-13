package com.nice.dcm.simulation.core.queue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simulation.core.IdEntity;
import com.nice.dcm.simulation.core.entity.Contact;
import com.nice.dcm.simulation.core.entity.Employee;
import com.nice.dcm.simulation.core.event.EventType;
import com.nice.dcm.simulation.core.event.TimeEvent;

public class TimeEventListenerImpl implements EventListener<TimeEvent<? extends IdEntity>> {
	private static final Logger logger = LoggerFactory.getLogger(TimeEventListenerImpl.class);
	
	private ContactEventHandlerImpl contactEventHandler = new ContactEventHandlerImpl();
	private EmployeeEventHandlerImpl employeeEventHandler = new EmployeeEventHandlerImpl();
	
	@Override
	public void onEvent(TimeEvent<? extends IdEntity> timeEvent) {
		IdEntity element = timeEvent.getEventEntity();
		EventType type = timeEvent.getType();
		switch (type.getEventCategory()) {
		case CONTACT_EVENT:
			contactEventHandler.onEvent((Contact)element, type, timeEvent.getEventSeconds());
			break;
		case AGENT_EVENT:
			employeeEventHandler.onEvent((Employee)element, type, timeEvent.getEventSeconds());
			break;
		default:
			String msg = "Unknown event category: " + type.getEventCategory();
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}
}
