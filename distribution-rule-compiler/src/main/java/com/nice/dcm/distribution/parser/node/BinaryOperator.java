package com.nice.dcm.distribution.parser.node;

public enum BinaryOperator {
    LESS_THAN("<"), LESS_THAN_OR_EQUAL("<="), EQUAL("="), NOT_EQUAL("<>"), GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">=");

    private String operator;

    public String getOperator() {
        return operator;
    }

    BinaryOperator(String operator) {
        this.operator = operator;
    }

    public boolean evaluate(int left, int right) {
        switch (this) {
        case LESS_THAN:
            return left < right;
        case LESS_THAN_OR_EQUAL:
            return left <= right;
        case EQUAL:
            return left == right;
        case NOT_EQUAL:
            return left != right;
        case GREATER_THAN:
            return left > right;
        case GREATER_THAN_OR_EQUAL:
            return left >= right;
        default:
            throw new IllegalArgumentException("Unknown operator: " + this);
        }
    }
    
    public static BinaryOperator fromString(String operator) {
        switch (operator) {
        case "<":
            return LESS_THAN;
        case "<=":
            return LESS_THAN_OR_EQUAL;
        case "=":
            return EQUAL;
        case "<>":
            return NOT_EQUAL;
        case ">":
            return GREATER_THAN;
        case ">=":
            return GREATER_THAN_OR_EQUAL;
        default:
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
