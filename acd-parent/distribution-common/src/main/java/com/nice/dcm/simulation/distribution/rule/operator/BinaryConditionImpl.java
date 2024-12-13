package com.nice.dcm.simulation.distribution.rule.operator;

import com.nice.dcm.simulation.distribution.rule.Condition;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@ToString
@EqualsAndHashCode
public class BinaryConditionImpl implements Condition {
    private final BinaryOperator operator;
    private final int right;
    
    public BinaryConditionImpl(BinaryOperator operator, int level) {
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
        
        if (o instanceof BinaryConditionImpl other) {
            if (operator != other.operator) {
                return operator.compareTo(other.operator);
            }
            return Integer.compare(right, other.right);
        } else {
            return 1;
        }
    }
}
