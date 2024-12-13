package com.nice.dcm.simulation.core.queue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simulation.core.entity.Employee;
import com.nice.dcm.simulation.core.event.EventType;
import com.nice.dcm.simulation.core.event.service.EventHandler;

public class EmployeeEventHandlerImpl implements EventHandler<Employee> {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeEventHandlerImpl.class);
	
	@Override
	public void onEvent(Employee element, EventType type, long eventSeconds) {
		logger.debug("EmployeeEventHandlerImpl.onEvent() called with: element = " + element + ", type = " + type + ", eventSeconds = " + eventSeconds);
		
		switch (type) {
		case AGENT_LOGIN:
		case AGENT_LOGOFF:
		case AGENT_ACTIVITY_CHANGED:
		case AGENT_COMPLETED_CONTACT:
            //TODO: Implement this method
			break;
		default:
			String msg = "Unknown event category: " + type;
			logger.error(msg);
			throw new IllegalArgumentException(msg);			
		}
	}
}
