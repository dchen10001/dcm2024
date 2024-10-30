package com.nice.dcm.simulation.distribution.rule;

import java.util.HashSet;
import java.util.Set;

import com.nice.dcm.simulation.distribution.node.ActionNodeImpl.ActionType;
import com.nice.dcm.simulation.distribution.node.QueueStatusNodeImpl.QueueStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingRuleImpl {
	private final ActionType action;
	private final QueueStatus queueStatus;
	private final Set<AndSkillLevelConditions> skills;
	private final int priority;
	
	public RoutingRuleImpl(ActionType action, QueueStatus queueStatus, Set<AndSkillLevelConditions> skills,
			int priority) {
		super();
		this.action = action;
		this.queueStatus = queueStatus;
		this.skills = new HashSet<>(skills);
		this.priority = priority;
	}
	
	public RoutingRuleImpl(Set<AndSkillLevelConditions> skills, int priority) {
		this(ActionType.QUEUE_TO, null, skills, priority);
	}
	
	public RoutingRuleImpl(QueueStatus queueStatus, Set<AndSkillLevelConditions> skills, int priority) {
		this(ActionType.QUEUE_TO, queueStatus, skills, priority);
	}
	
	public RoutingRuleImpl merge(RoutingRuleImpl rule) {
		if (this.priority != rule.priority) {
			throw new IllegalArgumentException("The priority of the rules must be the same");
		}
		skills.addAll(rule.skills);
		return this;
	}
}
