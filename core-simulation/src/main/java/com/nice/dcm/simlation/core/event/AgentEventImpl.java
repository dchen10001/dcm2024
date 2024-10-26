package com.nice.dcm.simlation.core.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentEventImpl extends TimeEventImpl implements AgentEvent {
	private String agentOid;
	
	public AgentEventImpl(long timestamp, String agentOid, EventType eventType) {
        super(timestamp, eventType);	
		this.agentOid = agentOid;
	}

	@Override
	public String getAgentOid() {
		return agentOid;
	}
}
