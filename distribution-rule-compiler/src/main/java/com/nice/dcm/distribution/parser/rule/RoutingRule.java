package com.nice.dcm.distribution.parser.rule;

import java.util.Set;

import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.NodeType;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule.QueueStatus;

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
	private final QueueStatus queueStatus;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULE;
	}

	@Override
	public int compareTo(RoutingRule o) {
		return priority - o.priority;
	}
}
