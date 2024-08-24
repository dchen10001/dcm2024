package com.nice.dcm.distribution.rule;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper=true)
public class SkillRuleImpl extends BaseSkillRule {
    private final String skillOid;
    
    public SkillRuleImpl(@NonNull String skillOid, int priority) {
        this(skillOid, priority, 0l);
    }
    
    public SkillRuleImpl(@NonNull String skillOid, int priority, long waitAfterSeconds) {
        super(priority, waitAfterSeconds);
        this.skillOid = skillOid;
    }

    public String getSkillOid() {
        return skillOid;
    }

    @Override
    public boolean apply(Set<String> skillOidSet, long waitingTime) {
        if(super.apply(skillOidSet, waitingTime)) {
            return skillOidSet.contains(skillOid);
        }
        return false;
    }
}
