package com.nice.dcm.distribution.parser.rule;

public enum SqlOperator {
    IN("in"), NOT_IN("not in");
    
    private String operator;

    SqlOperator(String operator) {
        this.operator = operator;
    }
    
    public String getOperator() {
        return operator;
    }
    
    public boolean evaluate(int left, int upperBound, int lowerBound) {
        switch (this) {
        case IN:
            return left >= lowerBound && left <= upperBound;
        case NOT_IN:
            return left < lowerBound || left > upperBound;
        default:
            throw new IllegalArgumentException("Unknown operator: " + this);
        }
    }

    public static SqlOperator fromString(String operator) {
        switch (operator) {
        case "in":
            return IN;
        case "not in":
            return NOT_IN;
        default:
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
