package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for BINARY_OPERATOR node
 * operator: operator to be used in the rule to compare the skill level
 * supported operators: <, <=, >, >=, ==, !=
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class BinaryOperatorNodeImpl implements Node {
	private final BinaryOperator operator;
	
	public BinaryOperatorNodeImpl(String operator) {
        this.operator = BinaryOperator.fromString(operator);
    }
	
	@Override
	public NodeType getNodeType() {
		return NodeType.BINARY_OPERATOR;
	}
}
