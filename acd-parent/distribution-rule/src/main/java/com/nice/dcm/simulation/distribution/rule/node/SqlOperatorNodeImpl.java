package com.nice.dcm.simulation.distribution.rule.node;

import com.nice.dcm.simulation.distribution.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.Node;
import com.nice.dcm.simulation.distribution.rule.NodeType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
