package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for SQL_OPERATOR node operator: SQL style operator
 * supported operators: IN, NOT IN, 
 * future support(maybe) BETWEEN, NOT BETWEEN
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class SqlOperatorNodeImpl implements Node {
	private final SqlOperator operator;
	
    public SqlOperatorNodeImpl(String operator) {
        this.operator = SqlOperator.fromString(operator);
    }
    
	@Override
	public NodeType getNodeType() {
		return NodeType.SQL_OPERATOR;
	}
}
