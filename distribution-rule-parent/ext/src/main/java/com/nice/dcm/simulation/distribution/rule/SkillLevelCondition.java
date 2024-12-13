package com.nice.dcm.simulation.distribution.rule;

/**
 * interface of condition for skill level condition.
 * 
 *  @author David Chen
 *  
 */
public interface SkillLevelCondition extends Comparable<SkillLevelCondition> {
	/**
	 * evaluate if the skill level is satisfied the condition
	 * @param level
	 * @return true if the skill level is satisfied the condition
	 */
	boolean evaluate(int level);
}
