package com.nice.dcm.distribution.rule;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class BaseSkillRule implements SkillRule, Comparable<BaseSkillRule> {
    private final int priority;
    private final long waitAfterSeconds;
    
    protected BaseSkillRule(int priority, long waitAfterSeconds) {
        super();
        this.priority = priority > 0 ? priority : 1;
        this.waitAfterSeconds = waitAfterSeconds > 0 ? waitAfterSeconds : 0l;
    }

    @Override
    public int compareTo(BaseSkillRule o) {
        return priority - o.priority;
    }

    @Override
    public boolean apply(Set<String> skillOidSet, long waitingTime) {
        if(skillOidSet == null || skillOidSet.isEmpty()) {
            return false;
        }
        
        //waiting longer enough
        return waitingTime >= waitAfterSeconds;
    }
}
