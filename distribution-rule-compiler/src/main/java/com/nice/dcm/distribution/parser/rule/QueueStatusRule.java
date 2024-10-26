package com.nice.dcm.distribution.parser.rule;

import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.NodeType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class QueueStatusRule implements Node {
	public enum QueueStatus {
		LEAST_BUSY;		
	}
	
	private final QueueStatus queueStatus;
	
	
	@Override
	public NodeType getNodeType() {
		return NodeType.QUEUE_STATUS;
	}

}
