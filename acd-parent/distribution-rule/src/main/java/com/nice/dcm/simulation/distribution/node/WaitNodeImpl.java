package com.nice.dcm.simulation.distribution.node;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for WAITING node
 * waitFor: seconds to wait to process new rules
 */
@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class WaitNodeImpl implements Node {
	private final int waitFor;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.WAITING;
	}
}
