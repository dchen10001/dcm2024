package com.nice.dcm.simulation.acd.service;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.action.RoutingGroupAction;

public interface ACDRoutingService {

	/**
	 * Find available agent.
	 *
	 * @param channleId    the channel id
	 * @param arrivalTime  the arrival time
	 * @param groupActions the group actions
	 * @return the string
	 */
	String findAvailableAgent(int channelId, int arrivalTime, List<RoutingGroupAction> groupActions);
	
	/**
	 * Assign contact to agent.
	 *
	 * @param channelId      the channel id
	 * @param contactTypeOid the contact type oid
	 * @param contactId      the contact id
	 * @param agentId        the agent id
	 */
	void assignContactToAgent(int channelId, String contactTypeOid, long contactId, String agentId);
}
