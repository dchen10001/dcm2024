package com.nice.dcm.simulation.distribution.action;

import java.util.Set;

/**
 * interface of action for queue to action.
 * @author David Chen 
 */
public interface QueueToAction {
	Set<SkillQueueSelector> getSelectors();
	
	int getPriority();
	
	boolean isLeastBusy();
}
