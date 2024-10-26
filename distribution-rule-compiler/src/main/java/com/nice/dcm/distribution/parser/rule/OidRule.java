package com.nice.dcm.distribution.parser.rule;

import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.NodeType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class OidRule implements Node {
	private final String oid;

	@Override
	public NodeType getNodeType() {
		return NodeType.OID;
	}
}
