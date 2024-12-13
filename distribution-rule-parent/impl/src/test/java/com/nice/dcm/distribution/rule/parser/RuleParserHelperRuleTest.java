package com.nice.dcm.distribution.rule.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSet;

class RuleParserHelperRuleTest {
	@Test
	void testVisitRoutingRuleSet() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl(Set.of("1111a"));
		RuleParserHelper ruleParserHelper = new RuleParserHelper();
		RoutingRuleSet routingRuleSet = null;
		
		String scripts[] = {
				"queue to @S: 1111a with priority 1",
				"""
				queue to @S: 1111a level = 2 with priority 2
				wait 100 queue to @S: 2222a with priority 1
				wait 150 queue to @S: 3333a with priority 1
				""", 				
				"""
				queue to @S: 1111a with priority 1
				wait 200 
	            queue to @S: 1111a level = 2 with priority 2
	            queue to @S: 1111a level < 2 with priority 2
	            queue to least busy of @S: 1111a and @S: 3333a with priority 1
	            queue to @S: 1111a with priority 1
	            queue to least busy of @S: 3333a with priority 1
	            queue to @S: 1111a and @S: 4444a with priority 1
				"""};
		
		int caseNo = 0;
		routingRuleSet = ruleParserHelper.visitRoutingRuleSet(scripts[caseNo++], dcmRuleVisitor);
		RoutingGroupRule groupRule = routingRuleSet.getGroupRule();
		assertNotNull(groupRule);
		assertEquals(0, groupRule.getWaitAfterSeconds());
		assertEquals(0, routingRuleSet.getGroupRules().size());
		
		Set<String> noExistSkills = dcmRuleVisitor.getNoExistSkills();
		assertEquals(0, noExistSkills.size());
		
		routingRuleSet = ruleParserHelper.visitRoutingRuleSet(scripts[caseNo++], dcmRuleVisitor);
		assertNotNull(groupRule);
		assertEquals(0, groupRule.getWaitAfterSeconds());
		assertEquals(2, routingRuleSet.getGroupRules().size());
		assertEquals(100, routingRuleSet.getGroupRules().get(0).getWaitAfterSeconds());
		assertEquals(150, routingRuleSet.getGroupRules().get(1).getWaitAfterSeconds());
		noExistSkills = dcmRuleVisitor.getNoExistSkills();
		assertEquals(2, noExistSkills.size());
		assertTrue(noExistSkills.contains("2222a"));
		assertTrue(noExistSkills.contains("3333a"));
		
		routingRuleSet = ruleParserHelper.visitRoutingRuleSet(scripts[caseNo], dcmRuleVisitor);
		assertEquals(0, groupRule.getWaitAfterSeconds());
		assertNotNull(groupRule);
		assertEquals(1, routingRuleSet.getGroupRules().size());
		assertEquals(200, routingRuleSet.getGroupRules().get(0).getWaitAfterSeconds());
		noExistSkills = dcmRuleVisitor.getNoExistSkills();
		assertEquals(3, noExistSkills.size());
		assertTrue(noExistSkills.contains("2222a"));
		assertTrue(noExistSkills.contains("3333a"));
		assertTrue(noExistSkills.contains("4444a"));
		assertFalse(noExistSkills.contains("1111a"));
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			ruleParserHelper.visitRoutingRuleSet("queue to @S: 1111a with priority 1 \n end", dcmRuleVisitor);
		});
		assertEquals("line 2: 2 token recognition error at: 'nd'", exception.getMessage());
	}
}
