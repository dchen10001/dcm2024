package com.nice.dcm.simulation.distribution.rule;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.nice.dcm.simulation.distribution.rule.action.RoutingGroupAction;

public interface RuleExecutor {
	final AtomicInteger idGenerator = new AtomicInteger(0);
	/**
	 * get the routing group action for the given channel, contactTypeOid, arrivalTime and waitTime
	 * 
	 * @param channleId
	 * @param contactTypeOid
	 * @param arrivalTime in seconds - the time the contact arrived (not used in the current implementation)
	 * @param waitTime in seconds - the time the contact has been waiting
	 * @return
	 */
	List<RoutingGroupAction> evaluate(int channleId, String contactTypeOid, int arrivalTime, int waitTime);
	
	String getContactTypeOid();
	
	int getChannelId();
	
	int getRuleId();
	
	static int generateId() {
		return idGenerator.getAndIncrement();
	}
	
	static void reSetId() {
		idGenerator.set(0);
	}	
}
