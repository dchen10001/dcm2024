package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.RoutingRuleSet;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRuleSetNodeImpl implements Node {
	private final RoutingRuleSet routingRuleSet;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULESET;
	}
}
