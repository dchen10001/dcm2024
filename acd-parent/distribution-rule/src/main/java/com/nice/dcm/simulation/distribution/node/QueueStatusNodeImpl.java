package com.nice.dcm.simulation.distribution.node;

import com.nice.dcm.simulation.distribution.rule.QueueStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for QUEUE_STATUS node action: action to be performed only
 * LEAST_BUSY supported at the moment
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class QueueStatusNodeImpl implements Node {
	private final QueueStatus queueStatus;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.QUEUE_STATUS;
	}
}
