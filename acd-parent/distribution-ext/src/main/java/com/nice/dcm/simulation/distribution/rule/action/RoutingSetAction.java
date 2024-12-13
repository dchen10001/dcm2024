package com.nice.dcm.simulation.distribution.rule.action;

import java.util.List;

public interface RoutingSetAction {
	/**
	 * Get the groupAction for the given waitingSeconds
	 * 
	 * @param waitingSeconds
	 * @return
	 */
	List<RoutingGroupAction> getActions(long waitingSeconds);
}
