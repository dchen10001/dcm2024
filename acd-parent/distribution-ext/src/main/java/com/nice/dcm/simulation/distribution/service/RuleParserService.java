package com.nice.dcm.simulation.distribution.service;

import com.nice.dcm.simulation.distribution.rule.action.RoutingSetAction;
import com.nice.dcm.simulation.distribution.storage.ChannelSelectorStorage;

public interface RuleParserService {
	RoutingSetAction parserRule(int channelId, String contactTypeOid, String rule);
	
	ChannelSelectorStorage getChannelStorage(int channelId);
}
