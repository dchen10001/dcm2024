package com.nice.dcm.simulation.distribution.rule.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillQueueSelector;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper=true)
public class SkillQueueSelectorImpl extends AndSkillLevelConditionsImpl 
	implements SkillQueueSelector {

	private int id;
	
	private int channelId;
	
	private SkillQueueSelectorImpl(int channelId, int id, Collection<SkillLevelCondition> skillLevelConditions) {
		super(skillLevelConditions);
		this.channelId = channelId;
		this.id = id;
	}

	private static Map<Integer, Map< Collection<SkillLevelCondition>, SkillQueueSelector>> channelSelectorMap = new HashMap<>();
	
	public static SkillQueueSelector getSkillQueueSelector(int channelId, AndSkillLevelConditions andSkillLevelConditions) {
		Map<Collection<SkillLevelCondition>, SkillQueueSelector> selectorMap = channelSelectorMap.computeIfAbsent(channelId, k -> new HashMap<>());
		Collection<SkillLevelCondition> skillLevelConditions = andSkillLevelConditions.getSkillLevelConditions();
		
		return selectorMap.computeIfAbsent(skillLevelConditions, k -> createSkillQueueSelector(channelId, skillLevelConditions));
	}
	
	private static SkillQueueSelector createSkillQueueSelector(int channelId, Collection<SkillLevelCondition> skillLevelConditions) {
		int id = SkillQueueSelector.generateId();
		return new SkillQueueSelectorImpl(channelId, id, skillLevelConditions);
	}
}
