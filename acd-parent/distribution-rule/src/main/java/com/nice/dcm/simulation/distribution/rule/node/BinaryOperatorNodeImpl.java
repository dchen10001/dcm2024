package com.nice.dcm.simulation.distribution.rule.node;

import com.nice.dcm.simulation.distribution.operator.BinaryOperator;
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
