package com.nice.dcm.distribution.parser.rule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class AgentStatusRule implements Node {
	public enum AgentStatus {
		LEAST_BUSY;		
	}
	
	private final AgentStatus agentStatus;
	
	
	@Override
	public NodeType getNodeType() {
		return NodeType.AGENT_STATUS;
	}

}
