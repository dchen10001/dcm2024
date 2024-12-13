package com.nice.dcm.simulation.distribution.rule.operator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class AndSkillLevelConditionsImpl implements AndSkillLevelConditions {
	protected final Collection<SkillLevelCondition> skillLevelConditions;
	
	public AndSkillLevelConditionsImpl(Collection<SkillLevelCondition> skillLevelConditions) {
		List<SkillLevelCondition> skillLevelConditionsList = new ArrayList<>(skillLevelConditions);
		
		if (skillLevelConditionsList.isEmpty()) {
			throw new IllegalArgumentException("The skill level conditions must not be empty");
		}
		
		if (skillLevelConditionsList.size() > 1) {
			Collections.sort(skillLevelConditionsList);
		}
		
		this.skillLevelConditions = skillLevelConditionsList;
	}
	
	public AndSkillLevelConditionsImpl(SkillLevelCondition skillLevelCondition) {
		this.skillLevelConditions = List.of(skillLevelCondition);
	}
}
