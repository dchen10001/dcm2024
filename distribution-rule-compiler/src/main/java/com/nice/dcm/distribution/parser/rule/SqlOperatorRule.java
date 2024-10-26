package com.nice.dcm.distribution.parser.rule;

import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.NodeType;
import com.nice.dcm.distribution.parser.node.SqlOperator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class SqlOperatorRule implements Node {
    private final SqlOperator operator;
    
    public SqlOperatorRule(String operator) {
        this.operator = SqlOperator.fromString(operator);
    }
    
    @Override
    public NodeType getNodeType() {
        return NodeType.SQL_OPERATOR;
    }
}
