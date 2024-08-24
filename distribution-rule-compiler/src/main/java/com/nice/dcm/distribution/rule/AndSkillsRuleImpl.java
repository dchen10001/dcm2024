package com.nice.dcm.distribution.rule;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper=true)
public class AndSkillsRuleImpl extends BaseSkillRule {
    private final Set<String> skillOids;
    
    public AndSkillsRuleImpl(@NonNull Set<String> skillOids, int priority) {
        this(skillOids, priority, 0l);
    }
    
    public AndSkillsRuleImpl(@NonNull Set<String> skillOids, int priority, long waitAfterSeconds) {
        super(priority, waitAfterSeconds);
        this.skillOids = skillOids;
    }

    public Set<String> getSkillOids() {
        return skillOids;
    }

    @Override
    public boolean apply(Set<String> skillOidSet, long waitingTime) {
        if(super.apply(skillOidSet, waitingTime)) {
            return skillOidSet.containsAll(skillOids);
        }
        return false;
    }
}
