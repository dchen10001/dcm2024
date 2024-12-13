package com.nice.dcm.simulation.distribution.action;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Queue to action implementation.
 * 
 * @see QueueToAction
 * 
 * @author David Chen
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class QueueToActionImpl implements QueueToAction {
	private final Set<SkillQueueSelector> selectors;
	private final int priority;
	private final boolean leastBusy;
	
	public QueueToActionImpl(@NonNull List<SkillQueueSelector> skillSelectors, int priority, boolean leastBusy) {
		if (skillSelectors.isEmpty()) {
			throw new IllegalArgumentException("skillSelectors is null or empty");
		}
		
		if (priority < 0) {
			throw new IllegalArgumentException("priority is less than 0");
		}
		
		this.selectors = skillSelectors.stream().distinct().collect(Collectors.toSet());
		this.priority = priority;
		this.leastBusy = leastBusy;
	}
}
