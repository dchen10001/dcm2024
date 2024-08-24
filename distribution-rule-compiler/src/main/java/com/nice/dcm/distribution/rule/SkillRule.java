package com.nice.dcm.distribution.rule;

import java.util.Set;

public interface SkillRule {
    boolean apply(Set<String> skillOidSet, long waitingTime);
    int getPriority();
    long getWaitAfterSeconds();
}
