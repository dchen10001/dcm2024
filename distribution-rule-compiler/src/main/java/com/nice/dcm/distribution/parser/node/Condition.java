package com.nice.dcm.distribution.parser.node;

public interface Condition extends Comparable<Condition> {
    public boolean evaluate(int left);
}
