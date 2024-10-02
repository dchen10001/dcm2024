package com.nice.dcm.distribution.parser.rule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class SkillLevelCondition implements Comparable<SkillLevelCondition> {
    private final String skillOid;
    private final Condition condition;
    
    public SkillLevelCondition(String skillOid) {
        this(skillOid, null);
    }

    @Override
    public int compareTo(SkillLevelCondition o) {
        if (o == null) {
            return 1;
        }
        
        int ret = this.skillOid.compareTo(o.skillOid);
        if (ret != 0) {
            return ret;
        }
        
        if (condition == null) {
            return o.condition == null ? 0 : -1;
        }
        
        if (o.condition == null) {
            return 1;
        }
        return condition.compareTo(o.condition);
    }
}
