package com.nice.dcm.simulation.core.entity;

import com.nice.dcm.simulation.core.IdEntityImpl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContactImpl extends IdEntityImpl implements Contact {
	private final int channelId;
	private final String contactTypeOid;
	private final long arrivingTime;
	private final long abandonedTime;
	
	public ContactImpl(int channelId, String contactTypeOid, long arrivingTime, long abandonedTime) {
		super();
		this.channelId = channelId;
		this.contactTypeOid = contactTypeOid;
		this.arrivingTime = arrivingTime;
		this.abandonedTime = abandonedTime;
	}
}
