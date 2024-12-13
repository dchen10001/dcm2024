package com.nice.dcm.simulation.distribution.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.BinarySkillLevelConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class RoutingRuleImplTest {
	@Test
	void testConstruction() {
		RuleAction action = RuleAction.QUEUE_TO;
		QueueStatus queueStatus = QueueStatus.LEAST_BUSY;
		
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
		
		List<SkillSetSelector> selectors = List.of(skillSelector);
		int priority = 1;

		RoutingRuleImpl routingRuleImpl = new RoutingRuleImpl(action, queueStatus, selectors, priority);
		
		assertEquals(action, routingRuleImpl.getAction());
		assertNull(routingRuleImpl.getQueueStatus());
		assertFalse(routingRuleImpl.isLeastBusyOf());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		
		routingRuleImpl = new RoutingRuleImpl(null, queueStatus, selectors, priority);
		
		assertEquals(action, routingRuleImpl.getAction());
		assertNull(routingRuleImpl.getQueueStatus());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		assertFalse(routingRuleImpl.isLeastBusyOf());
		
		routingRuleImpl = new RoutingRuleImpl(queueStatus, selectors, priority);
		assertEquals(action, routingRuleImpl.getAction());
		assertNull(routingRuleImpl.getQueueStatus());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		assertFalse(routingRuleImpl.isLeastBusyOf());
		
		routingRuleImpl = new RoutingRuleImpl(selectors, priority);
		assertEquals(action, routingRuleImpl.getAction());
		assertNull(routingRuleImpl.getQueueStatus());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		assertFalse(routingRuleImpl.isLeastBusyOf());
	}

	@Test
	void testConstructionList() {
		RuleAction action = RuleAction.QUEUE_TO;
		QueueStatus queueStatus = QueueStatus.LEAST_BUSY;

		SkillSetSelectorImpl skillSelector1 = new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2))));

		SkillSetSelectorImpl skillSelector2 = new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", new BinarySkillLevelConditionImpl(BinaryOperator.EQUAL, 3))));

		List<SkillSetSelector> selectors = List.of(skillSelector1, skillSelector2);
		int priority = 1;

		RoutingRuleImpl routingRuleImpl = new RoutingRuleImpl(action, queueStatus, selectors, priority);
		
		assertEquals(action, routingRuleImpl.getAction());
		assertEquals(queueStatus, routingRuleImpl.getQueueStatus());
		assertTrue(routingRuleImpl.isLeastBusyOf());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		
		routingRuleImpl = new RoutingRuleImpl(null, queueStatus, selectors, priority);
		
		assertEquals(action, routingRuleImpl.getAction());
		assertEquals(queueStatus, routingRuleImpl.getQueueStatus());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		assertTrue(routingRuleImpl.isLeastBusyOf());
		
		routingRuleImpl = new RoutingRuleImpl(queueStatus, selectors, priority);
		assertEquals(action, routingRuleImpl.getAction());
		assertEquals(queueStatus, routingRuleImpl.getQueueStatus());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		assertTrue(routingRuleImpl.isLeastBusyOf());
		
		routingRuleImpl = new RoutingRuleImpl(selectors, priority);
		assertEquals(action, routingRuleImpl.getAction());
		assertNull(routingRuleImpl.getQueueStatus());
		assertEquals(selectors, routingRuleImpl.getSelectors());
		assertEquals(priority, routingRuleImpl.getPriority());
		assertFalse(routingRuleImpl.isLeastBusyOf());
	}
	
	@Test
	void testConstructionException() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
		
		List<SkillSetSelector> selectors = List.of(skillSelector);		
		int priority = 1;
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingRuleImpl(null, priority);
		});
		
		assertEquals("selectors is null or empty", exception.getMessage());
		
		final List<SkillSetSelector> list = List.of();
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingRuleImpl(list, priority);
		});
		
		assertEquals("selectors is null or empty", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingRuleImpl(selectors, -1);
		});
		
		assertEquals("priority is negative", exception.getMessage());
	}
}
