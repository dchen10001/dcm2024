package com.nice.dcm.distribution.parser.rule;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BinaryCondition implements Condition {
    private final BinaryOperator operator;
    private final int right;
    
    public BinaryCondition(BinaryOperator operator, int level) {
        this.operator = operator;
        this.right = level;
    }
    
    @Override
    public boolean evaluate(int left) {
        return operator.evaluate(left, right);
    }

    @Override
    public int compareTo(Condition o) {
        if (o == null) {
            return 1;
        }
        
        if (o instanceof BinaryCondition other) {
            if (operator != other.operator) {
                return operator.compareTo(other.operator);
            }
            return Integer.compare(right, other.right);
        } else {
            return 1;
        }
    }
}
