package com.nice.dcm.simulation.distribution.rule;

public interface SkillLevelCondition {
	String getSkillOid();
	Condition getCondition();
	
	default boolean evaluate(String skillOid, int level) {
		if (!this.getSkillOid().equals(skillOid)) {
			return false;
		}
		
		Condition condition = getCondition();
		if (condition == null) {
			return true;
		}
		return condition.evaluate(level);
	}
}
