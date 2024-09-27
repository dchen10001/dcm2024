package com.nice.dcm.distribution.parser.rule;

import java.util.Set;

import com.nice.dcm.distribution.parser.rule.AgentStatusNode.AgentStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRule implements Node, Comparable<RoutingRule> {

	private final ActionRule action;
	
	private final Set<ComparableOidSet> skills;
	
	private final int priority;
	private final AgentStatus agentStatus;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULE;
	}

	@Override
	public int compareTo(RoutingRule o) {
		return priority - o.priority;
	}
}
