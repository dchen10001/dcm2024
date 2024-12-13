package com.nice.dcm.distribution.rule.parser.node;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class SkillOrSelectorNodeImpl implements Node {
	List<SkillSetSelector> selectors;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILL_OR_SELECTOR;
	}

}
