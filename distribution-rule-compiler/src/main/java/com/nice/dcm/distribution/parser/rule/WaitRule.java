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
public class WaitRule implements Node {
	private final int waitFor;

	@Override
	public NodeType getNodeType() {
		return NodeType.WAITING;
	}

	public int getWaitFor() {
		return waitFor;
	}
}
