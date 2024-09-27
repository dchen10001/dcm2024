package com.nice.dcm.distribution.parser.rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class AndSkillsRule implements Node {
	
	private final Set<ComparableOidSet> skills;
	
    public AndSkillsRule(Collection<ComparableOidSet> skills) {
        this.skills = new HashSet<>(skills);
    }
    
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLANDRULE;
	}
}
