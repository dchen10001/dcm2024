package com.nice.dcm.simulation.distribution.rule.action;

import java.util.List;

public interface RoutingGroupAction {
	long getWaitAfterSeconds();
    List<QueueToAction> getActions();
}
