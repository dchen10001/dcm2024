package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.RoutingRule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for ROUTINGRULE node routingRule: RoutingRule instance
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRuleNodeImpl implements Node {
	private final RoutingRule routingRule;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULE;
	}
}
