package com.nice.dcm.simulation.distribution.action;

import java.util.List;

/**
 * This interface represents a group of routing rules. 
 * It contains a list of routing rules and a wait time after which the next group of routing rules should be executed
 * @author David Chen 
 */
public interface QueueToGroupAction {
	long getWaitAfterSeconds();
    List<QueueToAction> getActions();
}
