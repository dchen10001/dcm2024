package com.nice.dcm.simulation.distribution.node;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.RoutingGroupRuleImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleImpl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class RoutingRuleGroupNodeImpl implements Node {
	private final RoutingGroupRuleImpl routingGroupRule;
	
	public RoutingRuleGroupNodeImpl(List<RoutingRuleImpl> rules) {
		this(0, rules);
	}
	
	public RoutingRuleGroupNodeImpl(long waitAfterSeconds, List<RoutingRuleImpl> rules) {
		super();
		this.routingGroupRule = new RoutingGroupRuleImpl(waitAfterSeconds, rules);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULEGROUP;
	}
}
