package com.nice.dcm.distribution.parser.rule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class AgentStatusNode implements Node {
	public enum AgentStatus {
		LEAST_BUSY, HIGHER_RANKING;		
	}
	
	private final AgentStatus agentStatus;
	
	
	@Override
	public NodeType getNodeType() {
		return NodeType.AGENT_STATUS;
	}

}
