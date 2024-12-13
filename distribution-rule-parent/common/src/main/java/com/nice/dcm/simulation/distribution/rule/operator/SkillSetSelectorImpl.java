package com.nice.dcm.simulation.distribution.rule.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SkillSetSelectorImpl implements SkillSetSelector {
	private final List<SkillSelector> skillSelectors;
	
	public SkillSetSelectorImpl(@NonNull List<SkillSelector> skillSelectors) {
		if (skillSelectors.isEmpty()) {
            throw new IllegalArgumentException("skillSelectors is empty");
		}
		
		Set<SkillSelector> selectorSet = skillSelectors.stream().collect(Collectors.toSet());
		if (selectorSet.size() < skillSelectors.size()) {
			skillSelectors = new ArrayList<>(selectorSet);
		}
		this.skillSelectors = skillSelectors;
	}
	
	public SkillSetSelectorImpl(@NonNull SkillSelector skillSelector) {
		super();
		this.skillSelectors = List.of(skillSelector);
	}
}
