package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.RuleAction;

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
	private final RuleAction action;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ACTIONRULE;
	}
}
