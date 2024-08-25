package com.nice.dcm.distribution.parser.rule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class SkillRule implements Node {
	private final String skillOid;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLRULE;
	}

}
