package com.nice.dcm.simulation.distribution.rule;

/**
 * SkillSelector interface defines the skill selector for routing rule.
 * 
 * SkillSelector is used to define the skill and condition for routing rule.
 * 
 * The routing rule will be applied to the skill if the skill and condition are
 * matched.
 * 
 *  @author David Chen
 *  
 */
public interface SkillSelector extends Comparable<SkillSelector> {
	String getSkillOid();
	
	SkillLevelCondition getCondition();
	
	/**
	 * evaluate if the skill oid is matched the selector's oid,
	 * and the skill level is satisfied the condition if the condition is not null.
	 * 
	 * @param skillOid
	 * @param level
	 * @return true if the skill level is satisfied the condition
	 */
	default boolean evaluate(String skillOid, int level) {
		if (!this.getSkillOid().equals(skillOid)) {
			return false;
		}
		
		SkillLevelCondition condition = getCondition();
		if (condition == null) {
			return true;
		}
		return condition.evaluate(level);
	}	
}
