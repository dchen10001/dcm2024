package com.nice.dcm.distribution.parser.rule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class OrderRule implements Node {
	private final int priority;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ORDER;
	}

}
