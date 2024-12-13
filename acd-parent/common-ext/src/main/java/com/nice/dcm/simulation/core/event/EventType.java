package com.nice.dcm.simulation.core.event;

public enum EventType {
	CONTACT_ARRIVAL(EventCategory.CONTACT_EVENT), CONTACT_ABANDONED(EventCategory.CONTACT_EVENT), 
	CONTACT_RULE_CHANGED(EventCategory.CONTACT_EVENT), 
	AGENT_LOGIN(EventCategory.AGENT_EVENT), AGENT_LOGOFF(EventCategory.AGENT_EVENT), 
	AGENT_ACTIVITY_CHANGED(EventCategory.AGENT_EVENT), AGENT_COMPLETED_CONTACT(EventCategory.AGENT_EVENT);
	
	private EventCategory eventCategory;
	
	private EventType(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
	
	public EventCategory getEventCategory() {
        return eventCategory;
    }
}
