package com.nice.dcm.simulation.distribution.node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class AndSkillsNodeImpl implements Node {
	private final Set<AndSkillLevelConditions> skills;

	public AndSkillsNodeImpl(Collection<AndSkillLevelConditions> skills) {
		this.skills = new HashSet<>(skills);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLANDRULE;
	}
}
