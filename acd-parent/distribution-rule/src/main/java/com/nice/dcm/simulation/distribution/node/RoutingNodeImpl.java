package com.nice.dcm.simulation.distribution.node;

import java.util.Set;

import com.nice.dcm.simulation.distribution.node.ActionNodeImpl.ActionType;
import com.nice.dcm.simulation.distribution.node.QueueStatusNodeImpl.QueueStatus;
import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleImpl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingNodeImpl implements Node {
	private final RoutingRuleImpl routingRule;
	
	public RoutingNodeImpl(ActionType action, QueueStatus queueStatus, Set<AndSkillLevelConditions> skills,
			int priority) {
		super();
        this.routingRule = new RoutingRuleImpl(action, queueStatus, skills, priority);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULE;
	}
}
