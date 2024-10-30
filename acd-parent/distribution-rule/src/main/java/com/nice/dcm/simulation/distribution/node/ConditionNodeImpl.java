package com.nice.dcm.simulation.distribution.node;

import com.nice.dcm.simulation.distribution.rule.Condition;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for CONDITION node condition: condition to be evaluated
 * Condition: condition to be evaluated, it is either a binary condition or 
 * a sql condition.
 * @see Condition
 *
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class ConditionNodeImpl implements Node {

	private final Condition condition;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.CONDITION;
	}
}
