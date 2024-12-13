package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRuleGroupNodeImpl implements Node {
	
	private final RoutingGroupRule routingGroupRule;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULEGROUP;
	}

}
