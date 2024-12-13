package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.action.RoutingGroupAction;
import com.nice.dcm.simulation.distribution.rule.action.RoutingSetAction;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class RuleExecutorImpl implements RuleExecutor {
	private final int ruleId;

	private final int channelId;

	@NonNull
	private final String contactTypeOid;
	
	@NonNull
	private final RoutingSetAction routingSetAction;

	public RuleExecutorImpl(int channelId, @NonNull String contactTypeOid, @NonNull RoutingSetAction routingSetAction) {
		super();
		this.ruleId = RuleExecutor.generateId();
		this.channelId = channelId;
		this.contactTypeOid = contactTypeOid;
		this.routingSetAction = routingSetAction;
	}
	
	@Override
	public List<RoutingGroupAction> evaluate(int channelId, String contactTypeOid, int arrivalTime, int waitTime) {
		if (this.channelId != channelId || !this.contactTypeOid.equals(contactTypeOid)) {
			new IllegalArgumentException("Invalid channel or contact type");
		}
		return routingSetAction.getActions(waitTime);
	}

	@Override
	public int getRuleId() {
		return ruleId;
	}
}