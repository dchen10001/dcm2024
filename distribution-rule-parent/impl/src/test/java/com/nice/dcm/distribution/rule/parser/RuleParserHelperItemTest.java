package com.nice.dcm.distribution.rule.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;
import com.nice.dcm.simulation.distribution.rule.RuleAction;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

class RuleParserHelperItemTest {
	@Test
	void testVisitWaitFor() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		String script = "wait 100";
		long waitFor = ruleParserHelper.visitWaitFor(script, dcmRuleVisitor);
		assertEquals(100, waitFor);
		
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitWaitFor("wait for 100", dcmRuleVisitor);
		});
		assertEquals("line 1: 6 token recognition error at: 'o'", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitWaitFor("wait -100", dcmRuleVisitor);
		});
		assertEquals("line 1: 5 token recognition error at: '-'", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitWaitFor("wait 1.09", dcmRuleVisitor);
		});
		assertEquals("line 1: 6 token recognition error at: '.0'", exception.getMessage());
	}
	
	@Test
	void testVisitPriority() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		String script = "with priority 100";
		int priority = ruleParserHelper.visitPriority(script, dcmRuleVisitor);
		assertEquals(100, priority);

		script = "with  \n priority  100";
		priority = ruleParserHelper.visitPriority(script, dcmRuleVisitor);
		assertEquals(100, priority);
		
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitPriority("with priority 100.0", dcmRuleVisitor);
		});
		assertEquals("line 1: 17 token recognition error at: '.0'", exception.getMessage());

		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitPriority("with priority -100", dcmRuleVisitor);
		});
		assertEquals("line 1: 14 token recognition error at: '-'", exception.getMessage());
	}
	
	@Test
	void testVisitOid() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		String script = UUID.randomUUID().toString();
		String oid = ruleParserHelper.visitOid(script, dcmRuleVisitor);
		assertEquals(script, oid);

		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitOid("1111s", dcmRuleVisitor);
		});
		assertEquals("line 1: 4 token recognition error at: 's'", exception.getMessage());
	}
	
	@Test
	void testVisitSqlOperator() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
        TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
        String script = "in 1..3";
        
        SqlOperator sqlOperator = ruleParserHelper.visitSqlOperator(script, dcmRuleVisitor);
        
        assertEquals(SqlOperator.IN, sqlOperator);
        
        script = "not in 1..3";
        assertEquals(SqlOperator.NOT_IN, ruleParserHelper.visitSqlOperator(script, dcmRuleVisitor));
        
        Exception exception = assertThrows(ParseCancellationException.class, () -> {
            ruleParserHelper.visitSqlOperator("is not equal", dcmRuleVisitor);
        });
        assertEquals("line 1: 0 token recognition error at: 'is'", exception.getMessage());
	}
	
	@Test
	void testVisitBinaryOperator() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
        TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
        
		for (BinaryOperator operator : BinaryOperator.values()) {
			if (operator == BinaryOperator.NOT_USED) {
				continue;
			}
			String script = operator.getOperator();
			assertEquals(operator, ruleParserHelper.visitBinaryOperator(script, dcmRuleVisitor), "Failed for operator: " + script);
		}
		
        Exception exception = assertThrows(ParseCancellationException.class, () -> {
            ruleParserHelper.visitBinaryOperator("Not used", dcmRuleVisitor);
        });
        assertEquals("line 1: 0 token recognition error at: 'N'", exception.getMessage());
	}
	
	@Test
	void testVisitLevelBinaryCondition() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
        TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
        
        String script = "level = 2 ";
        SkillLevelCondition condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertTrue(condition.evaluate(2));
        assertFalse(condition.evaluate(1));
        assertFalse(condition.evaluate(3));
        assertFalse(condition.evaluate(0));
        
        script = "level <> 2";
        condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertFalse(condition.evaluate(2));
        assertTrue(condition.evaluate(1));
        assertTrue(condition.evaluate(3));
        assertTrue(condition.evaluate(0));
        
        script = "level != 2";
        condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertFalse(condition.evaluate(2));
        assertTrue(condition.evaluate(1));
        assertTrue(condition.evaluate(3));
        assertTrue(condition.evaluate(0));
        
        script = "level < 2";
        condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertTrue(condition.evaluate(1));
        assertFalse(condition.evaluate(2));
        assertFalse(condition.evaluate(3));
        
        script = "level <= 2";
        condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertTrue(condition.evaluate(2));
        assertTrue(condition.evaluate(1));
        assertFalse(condition.evaluate(3));
        
        script = "level >= 2";
        condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertTrue(condition.evaluate(2));
        assertTrue(condition.evaluate(3));
        assertFalse(condition.evaluate(1));
        assertFalse(condition.evaluate(0));
        
        script = "level > 2";
        condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
        assertFalse(condition.evaluate(2));
        assertFalse(condition.evaluate(1));
        assertTrue(condition.evaluate(3));
	}
	
	@Test
	void testVisitLevelSqlCondition() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		
		String script = "level in 2 .. 3";
		SkillLevelCondition condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
		assertTrue(condition.evaluate(2));
		assertTrue(condition.evaluate(3));
		assertFalse(condition.evaluate(1));
		assertFalse(condition.evaluate(4));
		assertFalse(condition.evaluate(0));
		
		script = "level not in 2 .. 3";
		condition = ruleParserHelper.visitLevelCondition(script, dcmRuleVisitor);
		assertFalse(condition.evaluate(2));
		assertFalse(condition.evaluate(3));
		assertTrue(condition.evaluate(1));
		assertTrue(condition.evaluate(4));
		assertTrue(condition.evaluate(0));   
		
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitLevelCondition("level is 2", dcmRuleVisitor);
		});
		assertEquals("line 1: 6 token recognition error at: 'is'", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitLevelCondition("level ", dcmRuleVisitor);
		});
		assertEquals("line 1: 6 no viable alternative at input 'level'", exception.getMessage());
	}
	
	@Test
	void testVisitRuleAction() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
        TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
        String script = "queue to";
        assertEquals(RuleAction.QUEUE_TO, ruleParserHelper.visitRuleAction(script, dcmRuleVisitor));
        
        script = "queue     to";
        assertEquals(RuleAction.QUEUE_TO, ruleParserHelper.visitRuleAction(script, dcmRuleVisitor));
        
        Exception exception = assertThrows(ParseCancellationException.class, () -> {
            ruleParserHelper.visitRuleAction("action", dcmRuleVisitor);
        });
        assertEquals("line 1: 2 token recognition error at: 't'", exception.getMessage());
	}
	
	@Test
	void testVisitSkill() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
        TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
        
        String script = "@S: 1111a level = 2";
        SkillSelector skillSelector = ruleParserHelper.visitSkill(script, dcmRuleVisitor);
        assertTrue(skillSelector.evaluate("1111a", 2));
        assertFalse(skillSelector.evaluate("1111a", 1));
        assertFalse(skillSelector.evaluate("2222a", 2));
        
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitSkill("@S: skill", dcmRuleVisitor);
		});
		assertEquals("line 1: 4 token recognition error at: 's'", exception.getMessage());
	}
	
	@Test
	void testVisitSkillSet() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		
		String scripts[] = {
				"(@S: 1111a level = 3, @S: 2222a level < 2)",
				"""
					(@S: 1111a level < 4, @S: 1111a level < 4, @S: 1111a level > 2, @S: 2222a level < 2)		
				"""		
		};
		SkillSetSelector skillSetSelector = ruleParserHelper.visitSkillSet(scripts[0], dcmRuleVisitor);
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 3, "2222a", 1)));
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 3, "2222a", 1, "3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 3, "2222a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
		
		skillSetSelector = ruleParserHelper.visitSkillSet(scripts[1], dcmRuleVisitor);
		
		assertEquals(3, skillSetSelector.getSkillSelectors().size());
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 3, "2222a", 1)));
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 3, "2222a", 1, "3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
		
		
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitSkillSet("@S: 1111a level = 2, @S: 2222a level < 2", dcmRuleVisitor);
		});
		assertEquals("line 1: 0 missing '(' at '@S:'", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitSkillSet("(@S: 1111a level = 2)", dcmRuleVisitor);
		});
		assertEquals("line 1: 20 mismatched input ')' expecting ','", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitSkillSet("(@S: 1111a level = 2,)", dcmRuleVisitor);
		});
		assertEquals("line 1: 21 mismatched input ')' expecting '@S:'", exception.getMessage());
	}
	
	@Test
	void testVisitSkillOrSet() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		
		String script = "(@S: 1111a level = 2, @S: 2222a level < 2)";
		SkillSetSelector skillSetSelector = ruleParserHelper.visitSkillOrSet(script, dcmRuleVisitor);
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1, "3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
		
		script = "@S: 1111a level = 2";
		SkillSetSelector skillSelector = ruleParserHelper.visitSkillOrSet(script, dcmRuleVisitor);
        assertTrue(skillSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
        assertFalse(skillSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
        assertFalse(skillSelector.evaluate(Map.of("2222a", 1)));
	}
	
	@Test
	void testVisitOrSkills_single() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		
		String script = "(@S: 1111a level = 2, @S: 2222a level < 2)";
		List<SkillSetSelector> skillSetSelectors = ruleParserHelper.visitOrSkills(script, dcmRuleVisitor);
		assertEquals(1, skillSetSelectors.size());
		SkillSetSelector skillSetSelector = skillSetSelectors.get(0);
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1, "3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
		
		script = "@S: 1111a level = 2";
		skillSetSelectors = ruleParserHelper.visitOrSkills(script, dcmRuleVisitor);
		skillSetSelector = skillSetSelectors.get(0);
        assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
        assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
        assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
        
        script = "@S: 1111a level = 2 and @S: 2222a level < 2";
        skillSetSelectors = ruleParserHelper.visitOrSkills(script, dcmRuleVisitor);
        assertEquals(2, skillSetSelectors.size());
        
        skillSetSelector = skillSetSelectors.get(0);
        assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
        assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
        assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
        
        skillSetSelector = skillSetSelectors.get(1);
        assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
        assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 2)));
        assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1)));
	}
	
	@Test
	void testVisitOrSkills_combination() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		
		String script = "(@S: 1111a level = 2, @S: 2222a level < 2) and @S: 3333a level = 3";
		List<SkillSetSelector> skillSetSelectors = ruleParserHelper.visitOrSkills(script, dcmRuleVisitor);
		assertEquals(2, skillSetSelectors.size());
		
		SkillSetSelector skillSetSelector = skillSetSelectors.get(0);
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1, "3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
		
		skillSetSelector = skillSetSelectors.get(1);
		assertTrue(skillSetSelector.evaluate(Map.of("3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("3333a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("4444a", 3)));
		
		script = "@S: 3333a level = 3 and (@S: 1111a level = 2, @S: 2222a level < 2)";
		skillSetSelectors = ruleParserHelper.visitOrSkills(script, dcmRuleVisitor);
		assertEquals(2, skillSetSelectors.size());
		
		skillSetSelector = skillSetSelectors.get(1);
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1)));
		assertTrue(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 1, "3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 1, "2222a", 1)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2, "2222a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("1111a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("2222a", 1)));
		
		skillSetSelector = skillSetSelectors.get(0);
		assertTrue(skillSetSelector.evaluate(Map.of("3333a", 3)));
		assertFalse(skillSetSelector.evaluate(Map.of("3333a", 2)));
		assertFalse(skillSetSelector.evaluate(Map.of("4444a", 3)));
	}

	@Test
	void testVisitOrSkills_exception() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();

		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitOrSkills("@S: 1111a level = 2 p, @S: 2222a level < 2", dcmRuleVisitor);
		});
		assertEquals("line 1: 20 token recognition error at: 'p,'", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitOrSkills("@S: 1111a level = 2 or @S: 2222a level < 2", dcmRuleVisitor);
		});
		assertEquals("line 1: 20 token recognition error at: 'o'", exception.getMessage());
	}
	
	@Test
	void testVisitRoutingRule() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		RoutingRule routingRule = null;
		List<Map<String, Integer>> positiveSkills = null; 
		List<Map<String, Integer>> negativeSkills = null;
		String skillOid1 = "1111a";
		String skillOid2 = "2222a";
		String skillOid3 = "3333a";
		
		String scripts[] = {
							"queue to @S: 1111a with priority 1",
							 "queue to @S:1111a with priority 10",
							 "queue to @S: 1111a level = 2 with priority 100",
							 "queue to (@S: 1111a level = 2, @S: 2222a level >= 5) with priority 100",
							 "queue to @S: 1111a level = 2 and @S: 2222a level >= 5 with priority 100",
							 "queue to (@S: 1111a level = 2, @S: 2222a level >= 5) and @S:3333a with priority 100",
							 "queue to least  busy  of (@S: 1111a level = 2, @S: 2222a level >= 5) and @S:3333a with priority 100",
							 "queue to least busy of @S:3333a with priority 100"
							};
		int caseNo = 0;
		// case 1: simple routing rule
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);
		positiveSkills = List.of(Map.of(skillOid1, 1), Map.of(skillOid1, 1, skillOid2, 1), Map.of(skillOid1, 10)); 
		negativeSkills = List.of(Map.of(skillOid2, 10));
		assertRoutingRule(routingRule, 1, false, positiveSkills, negativeSkills);
		assertEquals(1, routingRule.getSelectors().size());

		// case 2: simple routing rule with 10 priority
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);
		assertRoutingRule(routingRule, 10, false, positiveSkills, negativeSkills);
		
		// case 3: simple routing rule with level condition
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);
		positiveSkills = List.of(Map.of(skillOid1, 2), Map.of(skillOid1, 2, skillOid2, 1)); 
		negativeSkills = List.of(Map.of(skillOid1, 1), Map.of(skillOid1, 3), Map.of(skillOid1, 1, skillOid2, 1), Map.of(skillOid1, 10));
		assertRoutingRule(routingRule, 100, false, positiveSkills, negativeSkills);
		assertEquals(1, routingRule.getSelectors().size());
		
		// case 4: simple routing rule with level condition. 
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);
		positiveSkills = List.of(Map.of(skillOid1, 2, skillOid2, 5), 
				Map.of(skillOid1, 2, skillOid2, 7),
				Map.of(skillOid1, 2, skillOid2, 5, skillOid3, 1)); 
		
		negativeSkills = List.of(Map.of(skillOid1, 1, skillOid2, 5), 
				Map.of(skillOid1, 2, skillOid2, 4),
				Map.of(skillOid1, 1, skillOid2, 4, skillOid3, 1)); 
		
		assertRoutingRule(routingRule, 100, false, positiveSkills, negativeSkills);
		assertEquals(1, routingRule.getSelectors().size());
		
		// case 5: queue to @S: 1111a level = 2 and @S: 2222a level >= 5 with priority 100 
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);
		positiveSkills = List.of(Map.of(skillOid1, 2, skillOid2, 4), 
				Map.of(skillOid1, 2), 
				Map.of(skillOid2, 5), 
				Map.of(skillOid1, 1, skillOid2, 5), 
				Map.of(skillOid1, 2, skillOid2, 7),
				Map.of(skillOid1, 1, skillOid2, 8, skillOid3, 1),
				Map.of(skillOid1, 2, skillOid2, 1, skillOid3, 1)); 
		
		negativeSkills = List.of(
				Map.of(skillOid1, 1), 
				Map.of(skillOid2, 4), 
				Map.of(skillOid1, 1, skillOid2, 4),
				Map.of(skillOid1, 1, skillOid2, 4, skillOid3, 1)); 
		
		assertRoutingRule(routingRule, 100, false, positiveSkills, negativeSkills);
		assertEquals(2, routingRule.getSelectors().size());
		
		// case 6: queue to (@S: 1111a level = 2, @S: 2222a level >= 5) and @S:3333a with priority 100
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);

		positiveSkills = List.of(Map.of(skillOid1, 2, skillOid2, 5), 
								 Map.of(skillOid3, 2), 
								 Map.of(skillOid3, 1),
								 Map.of(skillOid1, 1, skillOid2, 5, skillOid3, 1), 
								 Map.of(skillOid1, 2, skillOid2, 4, skillOid3, 1), 
								 Map.of(skillOid1, 2, skillOid2, 5, "any", 1)); 
		
		negativeSkills = List.of(
				 Map.of(skillOid1, 1, skillOid2, 5), 
				 Map.of(skillOid1, 2, skillOid2, 4), 
				 Map.of(skillOid1, 2), 
				 Map.of(skillOid2, 1));  
		
		assertRoutingRule(routingRule, 100, false, positiveSkills, negativeSkills);
		assertEquals(2, routingRule.getSelectors().size());
		
		// case 7: queue to least busy of (@S: 1111a level = 2, @S: 2222a level >= 5) and @S:3333a with priority 100
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo++], dcmRuleVisitor);
		positiveSkills = List.of(Map.of(skillOid1, 2, skillOid2, 5), 
				 Map.of(skillOid3, 2), 
				 Map.of(skillOid3, 1),
				 Map.of(skillOid1, 1, skillOid2, 5, skillOid3, 1), 
				 Map.of(skillOid1, 2, skillOid2, 4, skillOid3, 1), 
				 Map.of(skillOid1, 2, skillOid2, 5, "any", 1)); 

		negativeSkills = List.of(
		Map.of(skillOid1, 1, skillOid2, 5), 
		Map.of(skillOid1, 2, skillOid2, 4), 
		Map.of(skillOid1, 2), 
		Map.of(skillOid2, 1)); 
				
		assertRoutingRule(routingRule, 100, true, positiveSkills, negativeSkills);
		assertEquals(2, routingRule.getSelectors().size());
		
		// case 8: queue to least busy @S:3333a with priority 100
		routingRule = ruleParserHelper.visitRoutingRule(scripts[caseNo], dcmRuleVisitor);
		positiveSkills = List.of(Map.of(skillOid3, 2), 
				Map.of(skillOid1, 2, skillOid2, 4, skillOid3, 1)); 
		
		negativeSkills = List.of(
				Map.of(skillOid1, 1, skillOid2, 5), 
				Map.of(skillOid1, 2, skillOid2, 4), 
				Map.of(skillOid1, 2), 
				Map.of(skillOid2, 1)); 
		
		assertRoutingRule(routingRule, 100, false, positiveSkills, negativeSkills);
		assertEquals(1, routingRule.getSelectors().size());
	}

	private void assertRoutingRule(RoutingRule routingRule, int priority, boolean leastBusyOf,
			List<Map<String, Integer>> positiveSkills, List<Map<String, Integer>> negativeSkills) {
		assertEquals(RuleAction.QUEUE_TO, routingRule.getAction());
		assertEquals(priority, routingRule.getPriority());
		assertEquals(leastBusyOf, routingRule.isLeastBusyOf());
		
		for (Map<String, Integer> skills : positiveSkills) {
			assertTrue(routingRule.evaluate(skills), "positiveSkill: " + skills);
		}
		
		for (Map<String, Integer> skills : negativeSkills) {
			assertFalse(routingRule.evaluate(skills), "negativeSkill: " + skills);
		}
	}
	
	@Test
	void testVisitRoutingRuleException() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		// case 1: simple routing rule	
		String scripts[] = {
				"queue to with priority 0",
				"queue to @S: 1111a with priority 0",
				"queue to @S: 1111a with 100",
				"queue to @S: 1111a priority 100",
				"queue to @S: 1111a with priority -1",
				"queue to @S: 1111a level = 2 with priority 1.1",
				"queue to @S: 1111a level = with priority 1.1",
				"queue to (@S: 1111a level = 2 and @S: 2222a level >= 5) with priority 100",
				 "queue to @S: 1111a level = 2 or @S: 2222a level >= 5 with priority 100",
				 "queue to (@S: 1111a level = 2, @S: 2222a >= 5) and @S:3333a with priority 100",				 
				 "queue to leastbusy of @S:3333a with priority 100",
				 "queue to least busyof @S:3333a with priority 100"
		};
		
		String expectMessage[] = {
				"line 1: 9 mismatched input 'with' expecting {'(', '@S:', least busy of}",
                "line 1: 33 mismatched input '0' expecting {'and', 'level', 'with'}",
                "line 1: 24 missing 'priority' at '100'",
                "line 1: 19 missing 'with' at 'priority'",
                "line 1: 33 token recognition error at: '-'",
                "line 1: 44 token recognition error at: '.1'",
                "line 1: 27 missing NUMBER at 'with'",
                "line 1: 30 mismatched input 'and' expecting ','",
                "line 1: 29 token recognition error at: 'o'",
                "line 1: 41 extraneous input '>=' expecting {',', ')'}",
                "line 1: 9 token recognition error at: 'leastb'",
                "line 1: 9 token recognition error at: 'least busyo'"
		};
		
		for (int i = 0; i < scripts.length; i++) {
			final int k = i;
			Exception exception = assertThrows(ParseCancellationException.class, () -> {
				ruleParserHelper.visitRoutingRule(scripts[k], dcmRuleVisitor);
			});
			assertEquals(expectMessage[i], exception.getMessage(), "Failed for case: " + i);
		}
	}
	
	@Test
	void testVisitRoutingRuleGroup() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		String scripts[] = {"queue to @S: 1111a with priority 1",
							"""
				            queue to @S: 1111a level = 2 with priority 2
				            queue to @S: 1111a level < 2 with priority 2
				            queue to least busy of @S: 1111a and @S: 3333a with priority 1
				            queue to @S: 1111a with priority 1
				            queue to least busy of @S: 3333a with priority 1
				            queue to @S: 1111a and @S: 2222a with priority 1
							"""};
		
		RoutingGroupRule routingGroupRule = null;
		
		int caseNo = 0;

		routingGroupRule = ruleParserHelper.visitRoutingRuleGroup(scripts[caseNo++], dcmRuleVisitor);
		
		assertEquals(0, routingGroupRule.getWaitAfterSeconds());
		assertEquals(1, routingGroupRule.getRules().size());

		routingGroupRule = ruleParserHelper.visitRoutingRuleGroup(scripts[caseNo], dcmRuleVisitor);
		
		assertEquals(0, routingGroupRule.getWaitAfterSeconds());
		assertEquals(3, routingGroupRule.getRules().size());
		List<RoutingRule> rules = routingGroupRule.getRules();
		assertEquals(1, rules.get(0).getPriority());
		assertTrue(rules.get(0).isLeastBusyOf());
		assertEquals(2, rules.get(0).getSelectors().size());
		
		assertEquals(1, rules.get(1).getPriority());
		assertFalse(rules.get(1).isLeastBusyOf());
		assertEquals(3, rules.get(1).getSelectors().size());

		assertEquals(2, rules.get(2).getPriority());
		assertFalse(rules.get(2).isLeastBusyOf());
		assertEquals(2, rules.get(2).getSelectors().size());
		
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitRoutingRuleGroup("wait 100 queue to @S: 1111a with priority 1", dcmRuleVisitor);
		});
		assertEquals("line 1: 0 mismatched input 'wait' expecting queue to", exception.getMessage());
	}
	
	@Test
	void testVisitRoutingWaitingRuleGroup() {
		DCMRuleVisitorImpl dcmRuleVisitor = new DCMRuleVisitorImpl();
		assertNull(dcmRuleVisitor.visit(null));
		assertNull(dcmRuleVisitor.visitChildren(null));
		assertNull(dcmRuleVisitor.visitTerminal(null));
		assertNull(dcmRuleVisitor.visitErrorNode(null));
		
		TestRuleParserHelper ruleParserHelper = new TestRuleParserHelper();
		String scripts[] = {"wait 100 queue to @S: 1111a with priority 1",
							"""
				            wait 200 
				            queue to @S: 1111a level = 2 with priority 2
				            queue to @S: 1111a level < 2 with priority 2
				            queue to least busy of @S: 1111a and @S: 3333a with priority 1
				            queue to @S: 1111a with priority 1
				            queue to least busy of @S: 3333a with priority 1
				            queue to @S: 1111a and @S: 2222a with priority 1
							"""};
		
		RoutingGroupRule routingGroupRule = null;
		
		int caseNo = 0;
		routingGroupRule = ruleParserHelper.visitRoutingWaitRuleGroup(scripts[caseNo++], dcmRuleVisitor);
		assertEquals(100, routingGroupRule.getWaitAfterSeconds());

		assertEquals(1, routingGroupRule.getRules().size());
		
		routingGroupRule = ruleParserHelper.visitRoutingWaitRuleGroup(scripts[caseNo], dcmRuleVisitor);
		assertEquals(200, routingGroupRule.getWaitAfterSeconds());
		assertEquals(3, routingGroupRule.getRules().size());
		
		Exception exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitRoutingWaitRuleGroup("wait queue to @S: 1111a with priority 1", dcmRuleVisitor);
		});
		assertEquals("line 1: 5 missing NUMBER at 'queue to'", exception.getMessage());
		
		exception = assertThrows(ParseCancellationException.class, () -> {
			ruleParserHelper.visitRoutingWaitRuleGroup("wait 100 set queue to @S: 1111a with priority 1", dcmRuleVisitor);
		});
		assertEquals("line 1: 9 token recognition error at: 's'", exception.getMessage());
	}
}
