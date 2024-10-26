package com.nice.dcm.distribution.parser.engine.result;

import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.NodeType;

import lombok.Getter;

@Getter
public class AgentOidNode implements Node {
	private final String agentOid;
	private final int priority;
	
	public AgentOidNode() {
		this(null, Integer.MAX_VALUE);
	}
	
	public AgentOidNode(String agentOid, int priority) {
		this.agentOid = agentOid;
		this.priority = priority;
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.AGENTOID;
	}
	
	public boolean hasAgentOid() {
		return agentOid != null && !agentOid.isBlank();
	}
}
