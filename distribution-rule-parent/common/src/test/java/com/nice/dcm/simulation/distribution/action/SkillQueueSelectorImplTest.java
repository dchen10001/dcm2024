package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class SkillQueueSelectorImplTest {
	@Test
	void testGetSkillSelectors() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
	
		SkillQueueSelector skillQueueSelector = new SkillQueueSelectorImpl(skillSelector);
		assertEquals(0, skillQueueSelector.getId());
		assertEquals(2, skillQueueSelector.getSkillSelectors().size());
		assertEquals("skillOid1", skillQueueSelector.getSkillSelectors().get(0).getSkillOid());
	
		skillQueueSelector = new SkillQueueSelectorImpl(skillSelector);
		assertEquals(1, skillQueueSelector.getId());
		
		SkillQueueSelector.resetId();
		skillQueueSelector = new SkillQueueSelectorImpl(skillSelector);
		assertEquals(0, skillQueueSelector.getId());
	}
}
