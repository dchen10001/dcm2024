package com.nice.dcm.simulation.distribution.action;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class QueueToGroupSetActionImpl implements QueueToGroupSetAction {
	private final QueueToGroupAction defaultGroupAction;
	private final List<QueueToGroupAction> groupActions;
	
	public QueueToGroupSetActionImpl(QueueToGroupAction defaultGroupAction) {
		this(defaultGroupAction, List.of());
	}
	
	public QueueToGroupSetActionImpl(@NonNull QueueToGroupAction defaultGroupAction, List<QueueToGroupAction> groupActions) {
		if (defaultGroupAction.getWaitAfterSeconds() != 0) {
			throw new IllegalArgumentException("waitAfterSeconds of default group action should be 0.");
		}
		
		this.defaultGroupAction = defaultGroupAction;
        if (groupActions == null || groupActions.isEmpty()) {
        	this.groupActions = List.of();
        } else {
        	this.groupActions = groupActions;
        }
	}
	
	@Override
	public List<QueueToGroupAction> getActions(long waitingSeconds) {
		List<QueueToGroupAction> result = new ArrayList<>();
		result.add(defaultGroupAction);
		long totalWaitingSeconds = 0;
		for (QueueToGroupAction action : groupActions) {
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
