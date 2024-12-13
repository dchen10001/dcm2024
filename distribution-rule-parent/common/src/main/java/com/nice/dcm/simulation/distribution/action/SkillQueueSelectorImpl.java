package com.nice.dcm.simulation.distribution.action;

import java.util.List;

import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SkillQueueSelectorImpl implements SkillQueueSelector {
	private final int id;
	private final SkillSetSelector selector;
	
	public SkillQueueSelectorImpl(SkillSetSelector selector) {
		this.id = SkillQueueSelector.generateId();
		this.selector = selector;
	}
	
	@Override
	public List<SkillSelector> getSkillSelectors() {
		return selector.getSkillSelectors();
	}
}
