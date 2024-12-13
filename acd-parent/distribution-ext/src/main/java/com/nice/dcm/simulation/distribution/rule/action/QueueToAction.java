package com.nice.dcm.simulation.distribution.rule.action;

import java.util.Set;

import com.nice.dcm.simulation.distribution.rule.SkillQueueSelector;

public interface QueueToAction {
	Set<SkillQueueSelector> getSelectors();
	int getPriority();
	boolean isLeastBusy();
}
