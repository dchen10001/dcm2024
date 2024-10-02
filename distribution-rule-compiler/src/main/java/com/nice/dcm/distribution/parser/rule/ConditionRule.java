package com.nice.dcm.distribution.parser.rule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class ConditionRule implements Node {
    private final Condition condition;

    @Override
    public NodeType getNodeType() {
        return NodeType.CONDITION;
    }


}
