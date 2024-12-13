package com.nice.dcm.simulation.distribution.storage;

import java.util.Collection;
import java.util.List;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillQueueSelector;

public interface ChannelSelectorStorage {
	public int getChannelId();
	
	public List<SkillQueueSelector> getSelectors();
	
	public SkillQueueSelector findSelector(Collection<SkillLevelCondition> conditions);
	
	public SkillQueueSelector findSelector(int id);
}
