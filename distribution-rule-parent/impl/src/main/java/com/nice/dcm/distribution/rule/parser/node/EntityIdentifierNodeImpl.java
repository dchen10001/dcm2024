package com.nice.dcm.distribution.rule.parser.node;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for ENTITY_IDENTIFIER node
 * entityIdentifier: entity identifier UUID style
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class EntityIdentifierNodeImpl implements Node {
	private final String entityIdentifier;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ENTITY_IDENTIFIER;
	}
}
