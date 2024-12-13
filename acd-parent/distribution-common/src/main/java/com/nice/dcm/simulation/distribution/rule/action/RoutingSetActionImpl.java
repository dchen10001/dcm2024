package com.nice.dcm.simulation.distribution.rule.action;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingSetActionImpl implements RoutingSetAction {
	private final RoutingGroupAction defaultGroupAction;
	private final List<RoutingGroupAction> groupActions;
	
	@Override
	public List<RoutingGroupAction> getActions(long waitingSeconds) {
		List<RoutingGroupAction> result = new ArrayList<>();
		result.add(defaultGroupAction);
		long totalWaitingSeconds = 0;
		for (RoutingGroupAction action : groupActions) {
			totalWaitingSeconds += action.getWaitAfterSeconds();
			if (totalWaitingSeconds <= waitingSeconds) {
				result.add(action);
			} else {
				break;
			}
		}
		return result;
	}
}
