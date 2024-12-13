package com.nice.dcm.simulation.distribution.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Routing rule implementation.
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingRuleImpl implements RoutingRule {
	private final RuleAction action;
	private final QueueStatus queueStatus;
	private final List<SkillSetSelector> selectors;
	private final int priority;
	
	public RoutingRuleImpl(RuleAction action, QueueStatus queueStatus, 
			Collection<SkillSetSelector> selectors,
			int priority) {
		super();
		if (selectors == null || selectors.isEmpty()) {
			throw new IllegalArgumentException("selectors is null or empty");
		}
		
		if (priority < 0) {
			throw new IllegalArgumentException("priority is negative");
		}
		
		this.action = action == null ? RuleAction.QUEUE_TO : action;
		this.queueStatus = selectors.size() == 1 ? null : queueStatus;
		this.selectors = new ArrayList<>(selectors);
		this.priority = priority;
	}
	
	public RoutingRuleImpl(QueueStatus queueStatus, Collection<SkillSetSelector> selectors, int priority) {
		this(RuleAction.QUEUE_TO, queueStatus, selectors, priority);
	}
	
	public RoutingRuleImpl(Collection<SkillSetSelector> selectors, int priority) {
		this(null, selectors, priority);
	}

	@Override
	public boolean isLeastBusyOf() {
		return this.queueStatus != null;
	}
}
