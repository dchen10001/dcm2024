package com.nice.dcm.simulation.distribution.action;

import java.util.List;

/**
 * interface of action for queue to group set action.
 * @author David Chen 
 */
public interface QueueToGroupSetAction {
	/**
	 * Get the queue to group Action for the given waitingSeconds
	 * 
	 * @param waitingSeconds
	 * @return
	 */
	List<QueueToGroupAction> getActions(long waitingSeconds);
}
