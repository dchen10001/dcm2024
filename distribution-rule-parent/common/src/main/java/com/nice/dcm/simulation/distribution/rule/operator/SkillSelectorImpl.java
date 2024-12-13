package com.nice.dcm.simulation.distribution.rule.operator;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Implementation of {@link SkillSelector}.
 * 
 * 
 * @see SkillSelector
 * 
 * @author David Chen
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class SkillSelectorImpl implements SkillSelector {
	@NonNull
    private final String skillOid;
    private final SkillLevelCondition condition;
    
	public SkillSelectorImpl(String skillOid) {
		this(skillOid, null);
	}

	@Override
	public int compareTo(SkillSelector o) {
        if (o == null) {
            return 1;
        }
        
        if (o instanceof SkillSelectorImpl other) {
        	int result = compareSkillOid(other);
            if (result == 0) {
                return compareCondition(other.condition);
            } else {
                return result;
            }
        } else {
            return 1;
        }
	}
	
	private int compareSkillOid(SkillSelectorImpl o) {
		return skillOid.compareTo(o.skillOid);
	}
	
	private int compareCondition(SkillLevelCondition o) {
		if (condition == null) {
			return o == null ? 0 : -1;
		} else {
			return condition.compareTo(o);
		}
	}
}
