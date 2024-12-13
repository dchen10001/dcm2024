package com.nice.dcm.simulation.core.queue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simulation.core.entity.Contact;
import com.nice.dcm.simulation.core.event.EventType;
import com.nice.dcm.simulation.core.event.service.EventHandler;

public class ContactEventHandlerImpl implements EventHandler<Contact> {
	private static final Logger logger = LoggerFactory.getLogger(ContactEventHandlerImpl.class);
	
	@Override
	public void onEvent(Contact element, EventType type, long eventSeconds) {
		logger.debug("ContactEventHandlerImpl.onEvent() called with: element = " + element + ", type = " + type + ", eventSeconds = " + eventSeconds);
		
		switch (type) {
		case CONTACT_ARRIVAL:
		case CONTACT_ABANDONED:
		case CONTACT_RULE_CHANGED:
            //TODO: Implement this method
			break;
		default:
			String msg = "Unknown event category: " + type;
			logger.error(msg);
			throw new IllegalArgumentException(msg);			
		}
	}
}
