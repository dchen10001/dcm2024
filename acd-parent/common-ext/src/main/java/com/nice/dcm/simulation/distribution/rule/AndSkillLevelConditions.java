package com.nice.dcm.simulation.distribution.rule;

import java.util.Collection;
import java.util.Map;

/**
 * a list skill level conditions with AND relationship
 */
public interface AndSkillLevelConditions {
	Collection<SkillLevelCondition> getSkillLevelConditions();
	
	default boolean evaluate(Map<String, Integer> skillToLevels) {
		for(SkillLevelCondition skillLevelCondition : getSkillLevelConditions()) {
			String skillOid = skillLevelCondition.getSkillOid();
			if(!skillToLevels.containsKey(skillOid)) {
				return false;
			}
			
			if(!skillLevelCondition.evaluate(skillOid, skillToLevels.get(skillOid))) {
				return false;
			}
		}
		return true;
	}
}
