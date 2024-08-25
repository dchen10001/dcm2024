package com.nice.dcm.distribution.parser.rule;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class AndSkillsRule implements Node {
	
	private final Set<SkillRule> skills;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLANDRULE;
	}

}
