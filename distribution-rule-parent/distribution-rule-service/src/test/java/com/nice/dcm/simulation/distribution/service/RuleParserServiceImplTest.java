package com.nice.dcm.simulation.distribution.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.action.CTDistributionRules;
import com.nice.dcm.simulation.distribution.action.CTDistributionRulesImpl;
import com.nice.dcm.simulation.distribution.action.CTRuleSetActions;
import com.nice.dcm.simulation.distribution.action.QueueToAction;
import com.nice.dcm.simulation.distribution.action.QueueToGroupAction;
import com.nice.dcm.simulation.distribution.action.QueueToGroupSetAction;
import com.nice.dcm.simulation.distribution.action.QueueToGroupSetActionImpl;
import com.nice.dcm.simulation.distribution.action.SkillQueueSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;

class RuleParserServiceImplTest {
	@Test
	void testCreateRuleParserService() {
		RuleParserService ruleParserService = new RuleParserServiceImpl();
		
		Set<String> skills = Set.of("1111a", "2222a", "3333a", "4444a");
		String rule1 = """ 
						queue to @S:1111a and @S:2222a and (@S:3333a, @S:4444a) with priority 1
					""";
		
		String rule2 = """
					queue to @S:1111a level = 2 with priority 2
					wait 100 queue to @S:2222a with priority 1
					wait 150 queue to @S:3333a with priority 1
				""";
		
		String rule3 = """
				queue to @S: 1111a and @S: 5555a with priority 1
				wait 200 
	            queue to @S: 1111a level = 2 with priority 2
	            queue to @S: 1111a level < 2 with priority 2
	            queue to least busy of @S: 1111a and @S: 3333a with priority 1
	            queue to @S: 1111a with priority 1
	            queue to least busy of @S: 3333a with priority 1
	            queue to @S: 1111a and @S: 4444a with priority 1				
			""";
		
		String rule4 = """
						set etc = 10
						queue to @S: 1111a with priority 1		
					"""	;
		
		Map<String, String> contactTypes = Map.of("01", rule1, "02", rule2, "03", rule3, "04", rule4);

		CTDistributionRules ctDistributionRules = new CTDistributionRulesImpl(skills, contactTypes);
		
		final CTRuleSetActions ruleSetActions = ruleParserService.parserRules(ctDistributionRules);
		List<String> ctOids = ruleSetActions.getContactTypes();
		assertEquals(3, ctOids.size());
		
		List.of("01", "02", "03").forEach(oid -> {
			assertTrue(ctOids.contains(oid), "oid: " + oid);
			QueueToGroupSetAction queueToGroupSetAction = ruleSetActions.getQueueToGroupSetAction(oid);
			assertNotNull(queueToGroupSetAction);
			if(oid.equals("01")) {
				assertQueueToGroupSetAction01((QueueToGroupSetActionImpl)queueToGroupSetAction);
			}
		});
		
		List<String> errorOids = ruleSetActions.getErrorContactTypes();
		assertEquals(1, errorOids.size());
		assertEquals("04", errorOids.get(0));
		assertEquals("line 1: 1 token recognition error at: 's'", ruleSetActions.getCTError("04"));
		
		List<String> noSkillOids = ruleSetActions.getInvalidSkillsContactTypes();
		assertEquals(1, noSkillOids.size(), "noSkillOids: " + noSkillOids);
		assertEquals("03", noSkillOids.get(0));
		assertEquals(Set.of("5555a"), ruleSetActions.getInvalidSkills("03"));
		
		List<SkillQueueSelector> selectors = ruleSetActions.getSkillQueueSelectors();
		assertEquals(8, selectors.size());
		
		//		@S:1111a
		//		@S:1111a level = 2
		//		@S:1111a level < 2
		//		@S:2222a
		//		@S:3333a
		//		@S:4444a
		//		@S:5555a
		// 		(@S:3333a, @S:4444a)
		Set<Integer> ids = new HashSet<>();
		
		for(SkillQueueSelector selector : selectors) {
			List<SkillSelector> skillSelectors = selector.getSkillSelectors();
			assertTrue(ids.add(selector.getId()), "duplicated id" + selector.getId());
			
			if(selector.getId() != 2) {
				assertEquals(1, skillSelectors.size());
			}
			else {
				assertEquals(2, skillSelectors.size());
			}
		}
	}
	
	void assertQueueToGroupSetAction01(QueueToGroupSetActionImpl queueToGroupSetAction) {
		assertEquals(0, queueToGroupSetAction.getGroupActions().size());
		QueueToGroupAction groupAction = queueToGroupSetAction.getDefaultGroupAction();
		assertEquals(0, groupAction.getWaitAfterSeconds());
		assertEquals(1, groupAction.getActions().size());
		QueueToAction ruleAction = groupAction.getActions().get(0);
		
		assertEquals(1, ruleAction.getPriority());
		assertEquals(3, ruleAction.getSelectors().size());
		assertFalse(ruleAction.isLeastBusy());
	}
	
	@Test
	void testNullParm() {
		RuleParserService ruleParserService = new RuleParserServiceImpl();
		Exception exception = assertThrows(NullPointerException.class, () -> {
			ruleParserService.parserRules(null);
		});
		assertEquals("ctDistributionRules is marked non-null but is null", exception.getMessage());
	}
	
	
}
