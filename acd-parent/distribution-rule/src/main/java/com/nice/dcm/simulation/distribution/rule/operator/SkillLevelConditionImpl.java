package com.nice.dcm.simulation.distribution.rule.operator;

import com.nice.dcm.simulation.distribution.rule.Condition;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class SkillLevelConditionImpl implements SkillLevelCondition {
    private final String skillOid;
    private final Condition condition;

	public SkillLevelConditionImpl(String skillOid) {
        this(skillOid, null);
    }

	@Override
	public int compareTo(SkillLevelCondition o) {
        if (o == null) {
            return 1;
        }
        
        if (o instanceof SkillLevelConditionImpl other) {
            if (skillOid != other.skillOid) {
                return skillOid.compareTo(other.skillOid);
            }
            return condition.compareTo(other.condition);
        } else {
            return 1;
        }
	}
}
