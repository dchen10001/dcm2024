package com.nice.dcm.simulation.distribution.rule.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nice.dcm.simulation.distribution.rule.SkillQueueSelector;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class QueueToActionImpl implements QueueToAction {
	private final Set<SkillQueueSelector> selectors;
	private final int priority;
	private final boolean leastBusy;

	public QueueToActionImpl(List<SkillQueueSelector> selectors, int priority, boolean leastBusy) {
		this.selectors = new HashSet<>(selectors);
		this.priority = priority;
		this.leastBusy = leastBusy;
	}
}
