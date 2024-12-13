package com.nice.dcm.distribution.rule.parser.node;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class SkillSetSelectorNodeImpl implements Node {
	
	private final SkillSetSelector skillSetSelector;
	
	public SkillSetSelectorNodeImpl(List<SkillSelector> skillSelectors) {
		this.skillSetSelector = new SkillSetSelectorImpl(skillSelectors);
	}
	
	public SkillSetSelectorNodeImpl(SkillSelector skillSelector) {
		this.skillSetSelector = new SkillSetSelectorImpl(skillSelector);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILL_SET_SELECTOR;
	}
}
