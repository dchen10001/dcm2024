package com.nice.dcm.simulation.distribution.node;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.operator.AndSkillLevelConditionsImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class SkillSetNodeImpl implements Node {
	private final AndSkillLevelConditions andSkillLevelConditions;

	public SkillSetNodeImpl(List<SkillLevelCondition> skillLevelConditions) {
        this.andSkillLevelConditions = new AndSkillLevelConditionsImpl(skillLevelConditions);
	}
	
	public SkillSetNodeImpl(SkillLevelCondition skillLevelCondition) {
		this.andSkillLevelConditions = new AndSkillLevelConditionsImpl(skillLevelCondition);
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLSETRULE;
	}
}
