package com.nice.dcm.simulation.distribution.node;

import com.nice.dcm.simulation.distribution.rule.RoutingRuleSetImpl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRuleSetNodeImpl implements Node {

	private final RoutingRuleSetImpl ruleSet;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULESET;
	}

}
