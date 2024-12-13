package com.nice.dcm.simulation.distribution.node;

import com.nice.dcm.simulation.distribution.rule.ActionType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for ACTIONRULE node action: action to be performed
 * only QUEUE_TO supported at the moment
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class ActionNodeImpl implements Node {
	private final ActionType action;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ACTIONRULE;
	}
}
