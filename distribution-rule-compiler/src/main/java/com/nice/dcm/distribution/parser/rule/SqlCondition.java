package com.nice.dcm.distribution.parser.rule;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SqlCondition implements Condition {
    private final SqlOperator sqlOperator;
    private final int lowerBound;
    private final int upperBound;

    public SqlCondition(SqlOperator sqlOperator, int lowerBound, int upperBound) {
        this.sqlOperator = sqlOperator;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean evaluate(int left) {
        return sqlOperator.evaluate(left, upperBound, lowerBound);
        
    }

    @Override
    public int compareTo(Condition o) {
        if (o == null) {
            return 1;
        }
        
        if (o instanceof SqlCondition other) {
            if (sqlOperator != other.sqlOperator) {
                return sqlOperator.compareTo(other.sqlOperator);
            }
            if (lowerBound != other.lowerBound) {
                return Integer.compare(lowerBound, other.lowerBound);
            }
            return Integer.compare(upperBound, other.upperBound);
        } else {
            return -1;
        }
    }
}
