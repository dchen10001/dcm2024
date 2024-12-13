package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class QueueToGroupActionImplTest {
	@Test
	void testConstructor() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
	
		SkillQueueSelector skillQueueSelector = new SkillQueueSelectorImpl(skillSelector);	
		
		QueueToActionImpl queueToAction = new QueueToActionImpl(List.of(skillQueueSelector), 0, false);

		QueueToGroupAction queueToGroupAction = new QueueToGroupActionImpl(List.of(queueToAction));
		assertEquals(1, queueToGroupAction.getActions().size());
		assertEquals(0, queueToGroupAction.getWaitAfterSeconds());
		
		queueToGroupAction = new QueueToGroupActionImpl(List.of(queueToAction), 100);
		assertEquals(1, queueToGroupAction.getActions().size());
		assertEquals(100, queueToGroupAction.getWaitAfterSeconds());
		
		List<QueueToAction> actions = List.of();
		Exception e = assertThrows(IllegalArgumentException.class, () -> new QueueToGroupActionImpl(actions, 100));
		assertEquals("actions is null or empty", e.getMessage());
		
		e = assertThrows(NullPointerException.class, () -> new QueueToGroupActionImpl(null, 100));
		assertEquals("actions is marked non-null but is null", e.getMessage());
		
		List<QueueToAction> actions1 = List.of(queueToAction);
		e = assertThrows(IllegalArgumentException.class, () -> new QueueToGroupActionImpl(actions1, -1));
		assertEquals("waitAfterSeconds is less than 0", e.getMessage());
		
	}
}
