package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class QueueToActionImplTest {
	@Test
	void testConstructor() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"), new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition), new SkillSelectorImpl("skillOid2", condition));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
	
		SkillQueueSelector skillQueueSelector = new SkillQueueSelectorImpl(skillSelector);	
		
		QueueToActionImpl queueToAction = new QueueToActionImpl(List.of(skillQueueSelector), 0, false);
		Set<SkillQueueSelector> selectors = queueToAction.getSelectors();
		assertEquals(1, selectors.size());
		assertEquals(0, queueToAction.getPriority());
		assertEquals(false, queueToAction.isLeastBusy());
		
		Exception e = assertThrows(NullPointerException.class, () -> new QueueToActionImpl(null, 0, false));
		assertEquals("skillSelectors is marked non-null but is null", e.getMessage());
		
		List<SkillQueueSelector> skillQueueSelectors = List.of();
		e = assertThrows(IllegalArgumentException.class, () -> new QueueToActionImpl(skillQueueSelectors, 1, false));
		assertEquals("skillSelectors is null or empty", e.getMessage());
		
		List<SkillQueueSelector> skillQueueSelectors1 = List.of(skillQueueSelector);
		e = assertThrows(IllegalArgumentException.class, () -> new QueueToActionImpl(skillQueueSelectors1, -1, false));
		assertEquals("priority is less than 0", e.getMessage());
	}
}
