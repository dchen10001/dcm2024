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
public class ActionRule implements Node {
	public enum ActionType {
		QUEUE_TO
	}
	
	private final ActionType action;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ACTIONRULE;
	}

}
