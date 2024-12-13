package com.nice.dcm.simulation.distribution.rule;

import java.util.List;
import java.util.Map;

/**
 * This interface represents a routing rule.
 * 
 * @see RuleAction
 * @see QueueStatus
 * @see SkillSetSelector
 * @see RoutingGroupRule
 * 
 * @author David Chen
 */
public interface RoutingRule {
	
	RuleAction getAction();
	
	/**
	 * 
	 * @return the queue status or null if the rule is not based on queue status
	 */
	QueueStatus getQueueStatus();

	/**
	 * 
	 * @return the skill set selectors
	 */
	List<SkillSetSelector> getSelectors();

	/**
	 * 
	 * @return the priority of the rule
	 */
	int getPriority();

	/**
	 * 
	 * @return true if the rule is based on "Least Busy Of" queue status
	 */
	boolean isLeastBusyOf();
	
	default boolean evaluate(Map<String, Integer> skillToLevels) {
		if (skillToLevels == null || skillToLevels.isEmpty()) {
            return false;
        }
        
        for(SkillSetSelector skillSetSelector : getSelectors()) {
            if (skillSetSelector.evaluate(skillToLevels)) {
                return true;
            }
        }
        return false;
	}
}
