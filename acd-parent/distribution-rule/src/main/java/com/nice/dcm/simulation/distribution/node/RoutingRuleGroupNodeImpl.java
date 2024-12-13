package com.nice.dcm.simulation.distribution.node;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRuleImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class RoutingRuleGroupNodeImpl implements Node {
	private final RoutingGroupRule routingGroupRule;
	
	public RoutingRuleGroupNodeImpl(List<RoutingRule> rules) {
		this(0, rules);
	}
	
	public RoutingRuleGroupNodeImpl(long waitAfterSeconds, List<RoutingRule> rules) {
		super();
		this.routingGroupRule = new RoutingGroupRuleImpl(waitAfterSeconds, rules);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULEGROUP;
	}
}
