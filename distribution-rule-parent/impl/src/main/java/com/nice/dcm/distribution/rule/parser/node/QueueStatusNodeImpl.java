package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.QueueStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for QUEUE_STATUS node queue_status: LEAST_BUSY
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
