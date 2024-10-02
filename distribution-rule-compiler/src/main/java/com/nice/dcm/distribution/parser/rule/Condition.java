package com.nice.dcm.distribution.parser.rule;

public interface Condition extends Comparable<Condition> {
    public boolean evaluate(int left);
}
