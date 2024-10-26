package com.nice.dcm.distribution.parser.rule;

import com.nice.dcm.distribution.parser.node.BinaryOperator;
import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.NodeType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class BinaryOperatorRule implements Node {
    private final BinaryOperator operator;
    
    public BinaryOperatorRule(String operator) {
        this.operator = BinaryOperator.fromString(operator);
    }
    
    @Override
    public NodeType getNodeType() {
        return NodeType.BINARY_OPERATOR;
    }

}
