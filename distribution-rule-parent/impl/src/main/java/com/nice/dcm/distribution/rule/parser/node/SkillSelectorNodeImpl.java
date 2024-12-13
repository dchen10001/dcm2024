package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class SkillSelectorNodeImpl implements Node {
	private final SkillSelector skillSelector;
	
	public SkillSelectorNodeImpl(String skillOid) {
		this.skillSelector = new SkillSelectorImpl(skillOid);
	}
	
	public SkillSelectorNodeImpl(String skillOid, SkillLevelCondition condition) {
		this.skillSelector = new SkillSelectorImpl(skillOid, condition);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILL_SELECTOR;
	}

}
