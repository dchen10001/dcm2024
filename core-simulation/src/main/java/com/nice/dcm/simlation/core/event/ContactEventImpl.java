package com.nice.dcm.simlation.core.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContactEventImpl extends TimeEventImpl implements ContactEvent {
	private String ctOid;
	
	public ContactEventImpl(long timestamp, String ctOid, EventType eventType) {
		super(timestamp, eventType);
		this.ctOid = ctOid;
	}

	@Override
	public String getContactTypeOid() {
		return ctOid;
	}

}
