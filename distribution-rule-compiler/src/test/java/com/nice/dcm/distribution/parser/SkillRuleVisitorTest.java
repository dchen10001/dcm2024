package com.nice.dcm.distribution.parser;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.ActionRule.ActionType;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.RoutingRule;
import com.nice.dcm.distribution.parser.rule.RoutingRuleGroup;
import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;
import com.nice.dcm.distribution.parser.rule.Node;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

class SkillRuleVisitorTest {
	ParserUtil util = new ParserUtil();
	SkillRuleVisitor visitor = new SkillRuleVisitor();
	
	@Test
	void routingRuleSetTest() {
		String script = "queue to @S: a2 with priority 2 \n" +
						"queue to @S: a1 with priority 1" + 
					    "wait 20 \n" +
					    "queue to @S: a4 with priority 4 " +
					    "wait 10 \n" +
					    "queue to @S: a3 with priority 3 ";
		RoutingRuleSet rule = (RoutingRuleSet)util.vistorRoutingRuleSet(script, visitor);
		Assertions.assertEquals(3, rule.getRoutingRuleGroups().size());
		RoutingRuleGroup g1 = rule.getRoutingRuleGroups().get(0);
		Assertions.assertEquals(0, g1.getWaitAfterSeconds());
		Assertions.assertEquals(2, g1.getRules().size());
		
		RoutingRuleGroup g2 = rule.getRoutingRuleGroups().get(1);
		Assertions.assertEquals(10, g2.getWaitAfterSeconds());
		Assertions.assertEquals(1, g2.getRoutingRules().size());
		Assertions.assertEquals(Set.of("a3"), g2.getRoutingRules().get(0).getSkills());
		
		RoutingRuleGroup g3 = rule.getRoutingRuleGroups().get(2);
		Assertions.assertEquals(20, g3.getWaitAfterSeconds());
		Assertions.assertEquals(1, g3.getRoutingRules().size());
		Assertions.assertEquals(Set.of("a4"), g3.getRoutingRules().get(0).getSkills());
		
		script = "queue to @S: a2 with priority 2 \n";
		rule = (RoutingRuleSet)util.vistorRoutingRuleSet(script, visitor);
		
		Assertions.assertEquals(1, rule.getRoutingRuleGroups().size());
		g1 = rule.getRoutingRuleGroups().get(0);
		Assertions.assertEquals(0, g1.getWaitAfterSeconds());
		Assertions.assertEquals(1, g1.getRoutingRules().size());
		RoutingRule r = g1.getRoutingRules().get(0);
		Assertions.assertEquals(2, r.getPriority());
		Assertions.assertEquals(Set.of("a2"), r.getSkills());
		
		script = "queue to @S: a2 with priority 2 \n" +
				"queue to @S: a1 with priority 1";
		rule = (RoutingRuleSet)util.vistorRoutingRuleSet(script, visitor);
		Assertions.assertEquals(1, rule.getRoutingRuleGroups().size());
		g1 = rule.getRoutingRuleGroups().get(0);
		Assertions.assertEquals(0, g1.getWaitAfterSeconds());
		Assertions.assertEquals(2, g1.getRoutingRules().size());
	}
	
	@Test
	void routingRuleGroupTest() {

		String script = "queue to @S: a1 with priority 1";
		Node rule = util.vistorRoutingRuleGroup(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRuleGroup);
		RoutingRuleGroup routingRuleGroup = ((RoutingRuleGroup)rule);
		Set<RoutingRule> rules = routingRuleGroup.getRules();
		Assertions.assertEquals(1, rules.size());
		RoutingRule routingRule = rules.iterator().next();
		Assertions.assertEquals(ActionType.QUEUE_TO, routingRule.getAction().getAction());
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of("a1"), routingRule.getSkills());
		
		
		script = "queue to @S: a2 with priority 2 \n" +
					"queue to @S: a1 with priority 1";
		
		rule = util.vistorRoutingRuleGroup(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRuleGroup);
		routingRuleGroup = ((RoutingRuleGroup)rule);
		rules = routingRuleGroup.getRules();
		Assertions.assertEquals(2, rules.size());
		int pri = 1;
		for(RoutingRule r : rules) {
			Assertions.assertEquals(pri, r.getPriority());
			Assertions.assertEquals(Set.of("a" + pri), r.getSkills());
			pri++;
		}
		
		try {
			script = "test queue to @S: a2 with priority 2 \n" +
					"wait 10 \n" + 
					"queue to @S: a1 with priority 1";
			rule = util.vistorRoutingRuleGroup(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 token recognition error at: 't'", 
					e.getMessage());
		}
	}
	
	@Test
	void routingRuleTest() {

		String script = "queue to @S: a1 with priority 1";
		Node rule = util.vistorRoutingRule(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRule);
		RoutingRule routingRule = ((RoutingRule)rule);
		Assertions.assertEquals(ActionType.QUEUE_TO, routingRule.getAction().getAction());
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of("a1"), routingRule.getSkills());
		
		script = "queue to @S: a1 and @S: a1 with priority 1";
		rule = util.vistorRoutingRule(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRule);
		routingRule = ((RoutingRule)rule);
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of("a1"), routingRule.getSkills());
		
		script = "queue to @S: a1 and @S: a2 with priority 1";
		rule = util.vistorRoutingRule(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRule);
		routingRule = ((RoutingRule)rule);
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertTrue(routingRule.getSkills() instanceof TreeSet);
		Assertions.assertEquals(Set.of("a1", "a2"), routingRule.getSkills());
		
		
//		try {
//			script = "queue to @S: a1 with priority 1 wait";
//			rule = util.vistorRoutingRule(script, visitor);
//			Assertions.fail("Invalid queue to");
//		} catch(ParseCancellationException e) {
//			Assertions.assertEquals("line 1:32 extraneous input 'wait' expecting <EOF>", 
//					e.getMessage());
//		}
		
		try {
			script = "test queue to @S: a1";
			rule = util.vistorRoutingRule(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 token recognition error at: 't'", 
					e.getMessage());
		}
		
		try {
			script = "queue to @S: a1";
			rule = util.vistorRoutingRule(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:15 mismatched input '<EOF>' expecting {'and', 'with priority'}", 
					e.getMessage());
		}
		
		try {
			script = "queue to @S: a1 or @S: a1 with priority 1";
			rule = util.vistorRoutingRule(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:16 token recognition error at: 'o'", 
					e.getMessage());
		}
	}

	@Test
	void ruleActionTest() {
		String script = "queue to";
		Node rule = util.vistorRuleAction(script, visitor);
		Assertions.assertTrue(rule instanceof ActionRule);
		Assertions.assertEquals(ActionType.QUEUE_TO, ((ActionRule)rule).getAction());
		
		try {
			script = "route to";
			util.vistorRuleAction(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 token recognition error at: 'r'", 
					e.getMessage());
		}
	}
	
	@Test
	void waitingRuleTest() {
		String script = "wait 1";
		Node rule = util.vistorWait(script, visitor);
		Assertions.assertTrue(rule instanceof WaitRule);
		Assertions.assertEquals(1, ((WaitRule)rule).getWaitFor());
		
		try {
			script = "wait ";
			util.vistorWait(script, visitor);
			Assertions.fail("Invalid vistorWait");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:5 missing NUMBER at '<EOF>'", 
					e.getMessage());
		}
		
		try {
			script = "rest wait ";
			util.vistorWait(script, visitor);
			Assertions.fail("Invalid vistorWait");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 token recognition error at: 'r'", 
					e.getMessage());
		}
	}
	
	@Test
	void oidRuleTest() {
		String script = "100a923";
		Node rule = util.vistorOid(script, visitor);
		Assertions.assertTrue(rule instanceof OidRule);
		Assertions.assertEquals(script, ((OidRule)rule).getOid());
	}
	
	@Test
	void orderTest() {
		String script = "with priority 1";
		Node rule = util.vistorOrder(script, visitor);
		Assertions.assertTrue(rule instanceof OrderRule);
		Assertions.assertEquals(1, ((OrderRule)rule).getPriority());
		
		try {
			script = "with priority -1";
			util.vistorOrder(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:14 token recognition error at: '-'", 
					e.getMessage());
		}
		
		try {
			script = "with priority 01";
			util.vistorOrder(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:14 mismatched input '01' expecting NUMBER", 
					e.getMessage());
		}
		
		try {
			script = "with priority";
			util.vistorOrder(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:13 missing NUMBER at '<EOF>'", 
					e.getMessage());
		}
	}
	
	@Test
	void skillTest() {
		String script = "@S: 1 and";
		Node rule = util.vistorSkill(script, visitor);
		Assertions.assertTrue(rule instanceof SkillRule);
		Assertions.assertEquals("1", ((SkillRule)rule).getSkillOid());
		
		script = "@S: 11abcdef1111";
		rule = util.vistorSkill(script, visitor);
		Assertions.assertTrue(rule instanceof SkillRule);
		Assertions.assertEquals("11abcdef1111", ((SkillRule)rule).getSkillOid());
		
		try {
			script = "@S: skilloid";
			util.vistorSkill(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:4 token recognition error at: 's'", 
					e.getMessage());
		}
		
		try {
			script = "@S: ";
			util.vistorSkill(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:4 missing {NUMBER, UUID_OR_HEXA} at '<EOF>'", 
					e.getMessage());
		}
	}
	
	@Test
	void skillAndTest() {
		String script = "@S: 1";
		Node rule = util.vistorAndSkills(script, visitor);
		Assertions.assertTrue(rule instanceof AndSkillsRule);
		Assertions.assertEquals(Set.of(new SkillRule("1")), ((AndSkillsRule)rule).getSkills());
		
		script = "@S: 11abcdef1111";
		rule = util.vistorAndSkills(script, visitor);
		Assertions.assertTrue(rule instanceof AndSkillsRule);
		Assertions.assertEquals(Set.of(new SkillRule("11abcdef1111")), ((AndSkillsRule)rule).getSkills());
		
		script = "@S: 11abcdef1111 and @S: 1";
		rule = util.vistorAndSkills(script, visitor);
		Assertions.assertTrue(rule instanceof AndSkillsRule);
		Assertions.assertEquals(Set.of(new SkillRule("11abcdef1111"), 
				new SkillRule("1")), ((AndSkillsRule)rule).getSkills());
		
		try {
			script = "@S: 11abcdef1111 and ";
			util.vistorAndSkills(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:21 mismatched input '<EOF>' expecting '@S:'", 
					e.getMessage());
		}
		
		try {
			script = "@S: 11abcdef1111 and @S: ";
			util.vistorAndSkills(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:25 missing {NUMBER, UUID_OR_HEXA} at '<EOF>'", 
					e.getMessage());
		}
	}
}
