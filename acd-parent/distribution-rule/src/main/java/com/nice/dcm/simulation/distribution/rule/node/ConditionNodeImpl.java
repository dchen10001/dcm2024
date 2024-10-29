package com.nice.dcm.simulation.distribution.rule.node;

import com.nice.dcm.simulation.distribution.rule.Condition;
import com.nice.dcm.simulation.distribution.rule.Node;
import com.nice.dcm.simulation.distribution.rule.NodeType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class ConditionNodeImpl implements Node {

	private final Condition condition;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.CONDITION;
	}

}
