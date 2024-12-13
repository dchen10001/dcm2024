package com.nice.dcm.simulation.distribution.action;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Queue to group action implementation.
 * 
 * @see QueueToGroupAction
 * 
 * @author David Chen
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class QueueToGroupActionImpl implements QueueToGroupAction {
	private long waitAfterSeconds;
    private final  List<QueueToAction> actions;
    
    public QueueToGroupActionImpl(List<QueueToAction> actions) {
    	this(actions, 0);
    }
    
	public QueueToGroupActionImpl(@NonNull List<QueueToAction> actions, long waitAfterSeconds) {
		if (actions.isEmpty()) {
			throw new IllegalArgumentException("actions is null or empty");
		}
		
		if (waitAfterSeconds < 0) {
			throw new IllegalArgumentException("waitAfterSeconds is less than 0");
		}
		this.actions = actions;
		this.waitAfterSeconds = waitAfterSeconds;
	}
}
