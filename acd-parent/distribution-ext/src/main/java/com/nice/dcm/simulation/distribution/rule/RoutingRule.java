package com.nice.dcm.simulation.distribution.rule;

import java.util.Set;

public interface RoutingRule {
	public ActionType getAction();

	public QueueStatus getQueueStatus();

	public Set<AndSkillLevelConditions> getSkills();

	public int getPriority();

	public RoutingRule merge(RoutingRule rule);

	public boolean isLeastBusyOf();
}
