package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class CTRuleSetActionsImplTest {
	@Test
	void testGetInvalidSkills() {
		Map<String, QueueToGroupSetAction> queueToGroupSetActions = Map.of();
		List<SkillQueueSelector> skillQueueSelectors = List.of();
		Map<String, Set<String>> ctInvalidSkills = Map.of();
		Map<String, String> ctErrorMap = Map.of();
		
		CTRuleSetActions cTRuleSetActions = new CTRuleSetActionsImpl(queueToGroupSetActions,
				skillQueueSelectors, ctInvalidSkills, ctErrorMap);
		
		assertEquals(0, cTRuleSetActions.getContactTypes().size());
		assertEquals(0, cTRuleSetActions.getErrorContactTypes().size());
		assertEquals(0, cTRuleSetActions.getInvalidSkillsContactTypes().size());
		assertEquals(0, cTRuleSetActions.getSkillQueueSelectors().size());
		
		assertNull(cTRuleSetActions.getQueueToGroupSetAction("oid1"));
		assertNull(cTRuleSetActions.getInvalidSkills("oid1"));
		assertNull(cTRuleSetActions.getCTError("oid1"));
		
		queueToGroupSetActions = Map.of();
		skillQueueSelectors = List.of();
		ctInvalidSkills = null;
		ctErrorMap = null;
		
		cTRuleSetActions = new CTRuleSetActionsImpl(queueToGroupSetActions,
				skillQueueSelectors, ctInvalidSkills, ctErrorMap);
		
		assertEquals(0, cTRuleSetActions.getContactTypes().size());
		assertEquals(0, cTRuleSetActions.getErrorContactTypes().size());
		assertEquals(0, cTRuleSetActions.getInvalidSkillsContactTypes().size());
		assertEquals(0, cTRuleSetActions.getSkillQueueSelectors().size());
		
		assertNull(cTRuleSetActions.getQueueToGroupSetAction("oid1"));
		assertNull(cTRuleSetActions.getInvalidSkills("oid1"));
		assertNull(cTRuleSetActions.getCTError("oid1"));
		
		queueToGroupSetActions = Map.of(
				"oid1", new QueueToGroupSetActionImpl(createDefault()),
				"oid3", new QueueToGroupSetActionImpl(createDefault()),
				"oid2", new QueueToGroupSetActionImpl(createDefault())
				);
		skillQueueSelectors = List.of(createSkillQueueSelector(), createSkillQueueSelector());
		ctInvalidSkills = Map.of("oid1", Set.of("invalidSkill1", "invalidSkill2"),
				"oid2", Set.of("invalidSkill3", "invalidSkill4"));
		ctErrorMap = Map.of("oid4", "error message 1", "oid5", "error message 2");
		
		cTRuleSetActions = new CTRuleSetActionsImpl(queueToGroupSetActions,
				skillQueueSelectors, ctInvalidSkills, ctErrorMap);
		
		assertEquals(3, cTRuleSetActions.getContactTypes().size());
		for (String oid : queueToGroupSetActions.keySet()) {
			assertTrue(cTRuleSetActions.getContactTypes().contains(oid));
		}
		
		assertEquals(2, cTRuleSetActions.getErrorContactTypes().size());
		assertTrue(cTRuleSetActions.getErrorContactTypes().contains("oid4"));
		assertTrue(cTRuleSetActions.getErrorContactTypes().contains("oid5"));
		
		assertEquals(2, cTRuleSetActions.getInvalidSkillsContactTypes().size());
		assertEquals(2, cTRuleSetActions.getSkillQueueSelectors().size());
		
		for(String oid : cTRuleSetActions.getContactTypes()) {
			assertNotNull(cTRuleSetActions.getQueueToGroupSetAction(oid));
		}
		
		for(String oid : cTRuleSetActions.getInvalidSkillsContactTypes()) {
			assertNotNull(cTRuleSetActions.getInvalidSkills(oid));
		}
		
		for(String oid : cTRuleSetActions.getErrorContactTypes()) {
			assertNotNull(cTRuleSetActions.getCTError(oid));
		}
	}
	
	@Test
	void testException() {
		Map<String, QueueToGroupSetAction> queueToGroupSetActions = Map.of();
		List<SkillQueueSelector> skillQueueSelectors = List.of();
		Map<String, Set<String>> ctInvalidSkills = Map.of();
		Map<String, String> ctErrorMap = Map.of();
		
		Exception exception = assertThrows(NullPointerException.class, () -> {
			new CTRuleSetActionsImpl(null, skillQueueSelectors, ctInvalidSkills, ctErrorMap);
		});
		assertEquals("queueToGroupSetActions is marked non-null but is null", exception.getMessage());
		
		exception = assertThrows(NullPointerException.class, () -> {
			new CTRuleSetActionsImpl(queueToGroupSetActions, null, ctInvalidSkills, ctErrorMap);
		});
		assertEquals("skillQueueSelectors is marked non-null but is null", exception.getMessage());
	}
	
	private QueueToGroupAction createDefault() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		SkillSetSelector skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
		SkillQueueSelector skillQueueSelector = new SkillQueueSelectorImpl(skillSelector);	
		QueueToAction queueToAction = new QueueToActionImpl(List.of(skillQueueSelector), 0, false);
		return  new QueueToGroupActionImpl(List.of(queueToAction), 0);
	}
	
	private SkillQueueSelector createSkillQueueSelector() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
		return new SkillQueueSelectorImpl(skillSelector);
	}
}
