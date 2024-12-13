package com.nice.dcm.simulation.acd.service;

public interface ACDRuleProvider {
	/**
	 * Parser rule.
	 * @param channelId
	 * @param contactTypeOid
	 * @param script
	 * @return
	 */
	int parserRule(int channelId, String contactTypeOid, String script);

	/**
	 * Find available agent and assign contact to agent. Return agent Oid
	 * @param channelId
	 * @param contactTypeOid
	 * @param contactId
	 * @param arrivalTime
	 * @param waitTime
	 * @return
	 */
	String assignContactToAgent(int channelId, String contactTypeOid, long contactId, int arrivalTime, int waitTime);
}
