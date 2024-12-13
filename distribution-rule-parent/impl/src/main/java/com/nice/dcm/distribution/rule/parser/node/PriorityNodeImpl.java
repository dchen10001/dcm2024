package com.nice.dcm.distribution.rule.parser.node;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for PRIORITY node priority: priority of the node
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class PriorityNodeImpl implements Node {
	private final int priority;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.PRIORITY;
	}
}
