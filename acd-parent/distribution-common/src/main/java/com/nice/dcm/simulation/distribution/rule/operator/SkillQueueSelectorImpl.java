package com.nice.dcm.simulation.distribution.rule.operator;

import java.util.Collection;

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

	private final int id;
	
	private final int channelId;
	
	private final boolean requiredWaitingTime;
	
	private SkillQueueSelectorImpl(int channelId, int id, Collection<SkillLevelCondition> skillLevelConditions, boolean requiredWaitingTime) {
		super(skillLevelConditions);
		this.channelId = channelId;
		this.id = id;
		this.requiredWaitingTime = requiredWaitingTime;
	}

	public static SkillQueueSelector createSkillQueueSelector(int channelId, Collection<SkillLevelCondition> skillLevelConditions, boolean requiredWaitingTime) {
		int id = SkillQueueSelector.generateId();
		return new SkillQueueSelectorImpl(channelId, id, skillLevelConditions, requiredWaitingTime);
	}
}
