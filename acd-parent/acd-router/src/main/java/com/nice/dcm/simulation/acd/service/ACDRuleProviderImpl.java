package com.nice.dcm.simulation.acd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simulation.distribution.rule.RuleExecutor;
import com.nice.dcm.simulation.distribution.rule.RuleExecutorImpl;
import com.nice.dcm.simulation.distribution.rule.action.RoutingGroupAction;
import com.nice.dcm.simulation.distribution.rule.action.RoutingSetAction;
import com.nice.dcm.simulation.distribution.service.RuleParserService;

import lombok.NonNull;

public class ACDRuleProviderImpl implements ACDRuleProvider {
	private static final Logger logger = LoggerFactory.getLogger(ACDRuleProviderImpl.class);

	private RuleParserService parser;

	private ACDRoutingService service;

	private final Map<Integer, ChannelStorage> channelStorages = new HashMap<>();

	public ACDRuleProviderImpl() {

	}

	public ACDRuleProviderImpl(RuleParserService parser, ACDRoutingService service) {
		setService(service);
		setParser(parser);
	}

	public void setParser(@NonNull RuleParserService parser) {
		this.parser = parser;
	}

	public void setService(@NonNull ACDRoutingService service) {
		this.service = service;
	}

	/**
	 * parser distribution rule and store RuleExecutorImpl
	 */
	@Override
	public int parserRule(int channelId, String contactTypeOid, String script) {
		RoutingSetAction action = parser.parserRule(channelId, contactTypeOid, script);
		RuleExecutorImpl ruleExecutor = new RuleExecutorImpl(channelId, contactTypeOid, action);
		logger.debug("Add rule set. ChannelId: {}, ContactTypeOid: {}, RuleSet: {}", channelId, contactTypeOid,
				ruleExecutor);
		ChannelStorage channelStorage = channelStorages.computeIfAbsent(channelId, k -> new ChannelStorage(channelId));
		channelStorage.addRuleSet(contactTypeOid, ruleExecutor);
		return ruleExecutor.getRuleId();
	}

	@Override
	public String assignContactToAgent(int channelId, String contactTypeOid, 
			long contactId, int arrivalTime,
			int waitTime) {
		logger.debug("Assign contact to agent. ChannelId: {}, ContactTypeOid: {}, ContactId: {}, ArrivalTime: {}, WaitTime: {}", channelId, contactTypeOid, contactId, arrivalTime, waitTime);
		String agentOid = findAvailableAgent(channelId, contactTypeOid, arrivalTime, waitTime);
		if (agentOid != null) {
			logger.debug("Agent {} is found. Assign contact {} to the agent.", agentOid, contactId);			
            service.assignContactToAgent(channelId, contactTypeOid, contactId, agentOid);
            return agentOid;
		} else {
			logger.debug("Agent is not found. Contact {} is waiting.", contactId);
			return null;
		}
	}

	protected String findAvailableAgent(int channelId, String contactTypeOid, int arrivalTime, int waitTime) {
		ChannelStorage channelStorage = channelStorages.get(channelId);
		if (channelStorage == null) {
			logger.warn("The channel {} is not exist.", channelId);
			return null;
		}
		
		RuleExecutor ruleExecutor = channelStorage.getRuleSet(contactTypeOid);
		if (ruleExecutor == null) {
			logger.warn("The rule set of contact type {} is not exist.", contactTypeOid);
			return null;
		}
		
		List<RoutingGroupAction> groupActions = ruleExecutor.evaluate(channelId, contactTypeOid, arrivalTime, waitTime);
		logger.debug("Find available agent. ChannelId: {}, ContactTypeOid: {}, ArrivalTime: {}, WaitTime: {}, GroupActions: {}", channelId, contactTypeOid, arrivalTime, waitTime, groupActions);
		return service.findAvailableAgent(channelId, arrivalTime, groupActions);
	}
	
	class ChannelStorage {
		private final int channelId;
		private final Map<String, RuleExecutor> ruleSetMap = new HashMap<>();

		public ChannelStorage(int channelId) {
			this.channelId = channelId;
		}

		public void addRuleSet(String contactTypeOid, RuleExecutor ruleExecutor) {
			ruleSetMap.put(contactTypeOid, ruleExecutor);
		}

		public RuleExecutor getRuleSet(String contactTypeOid) {
			return ruleSetMap.get(contactTypeOid);
		}

		public int getChannelId() {
			return channelId;
		}
	}
}
