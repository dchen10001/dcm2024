package com.nice.dcm.distribution.parser;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.ActionRule.ActionType;
import com.nice.dcm.distribution.parser.rule.AgentStatusNode.AgentStatus;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.ComparableOidSet;
import com.nice.dcm.distribution.parser.rule.Node;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.RoutingRule;
import com.nice.dcm.distribution.parser.rule.RoutingRuleGroup;
import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.SkillSetRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

class SkillRuleVisitorTest {
	ParserUtil util = new ParserUtil();
	SkillRuleVisitor visitor = new SkillRuleVisitor();
	
	@Test
	void routingRuleSetTest() {
		String script = """
		                queue to @S: a2 with priority 2
						queue to @S: a1 with priority 1
					    wait 20
					    queue to @S: a4 with priority 4
					    wait 10
					    queue to @S: a3 with priority 3    
		            """;
		RoutingRuleSet rule = (RoutingRuleSet)util.visitRoutingRuleSet(script, visitor);
		Assertions.assertEquals(3, rule.getRuleGroups().size());
		RoutingRuleGroup g1 = rule.getRuleGroups().get(0);
		Assertions.assertEquals(0, g1.getWaitAfterSeconds());
		Assertions.assertEquals(2, g1.getRules().size());
		
		RoutingRuleGroup g2 = rule.getRuleGroups().get(1);
		Assertions.assertEquals(10, g2.getWaitAfterSeconds());
		Assertions.assertEquals(1, g2.getRoutingRules().size());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a3")), g2.getRoutingRules().get(0).getSkills());
		
		RoutingRuleGroup g3 = rule.getRuleGroups().get(2);
		Assertions.assertEquals(20, g3.getWaitAfterSeconds());
		Assertions.assertEquals(1, g3.getRoutingRules().size());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a4")), g3.getRoutingRules().get(0).getSkills());
		

		script = """
		        queue to @S:a2 and @S:a2 with priority 2
				queue to @S:a1 with priority 1 
				queue to @S:a1 with priority 1 
			    wait 20
			    queue to @S:a4 with priority 4
			    wait 10
			    queue to @S:a3 with priority 3
		        """;
			rule = (RoutingRuleSet)util.visitRoutingRuleSet(script, visitor);
			Assertions.assertEquals(3, rule.getRuleGroups().size());
			 g1 = rule.getRuleGroups().get(0);
			Assertions.assertEquals(0, g1.getWaitAfterSeconds());
			//TODO: same priority should be merged
			Assertions.assertEquals(3, g1.getRules().size());
			
			 g2 = rule.getRuleGroups().get(1);
			Assertions.assertEquals(10, g2.getWaitAfterSeconds());
			Assertions.assertEquals(1, g2.getRoutingRules().size());
			Assertions.assertEquals(Set.of(new ComparableOidSet("a3")), g2.getRoutingRules().get(0).getSkills());
			
			 g3 = rule.getRuleGroups().get(2);
			Assertions.assertEquals(20, g3.getWaitAfterSeconds());
			Assertions.assertEquals(1, g3.getRoutingRules().size());
			Assertions.assertEquals(Set.of(new ComparableOidSet("a4")), g3.getRoutingRules().get(0).getSkills());

		script = "queue to @S: a2 with priority 2 \n";
		rule = (RoutingRuleSet)util.visitRoutingRuleSet(script, visitor);
		
		Assertions.assertEquals(1, rule.getRuleGroups().size());
		g1 = rule.getRuleGroups().get(0);
		Assertions.assertEquals(0, g1.getWaitAfterSeconds());
		Assertions.assertEquals(1, g1.getRoutingRules().size());
		RoutingRule r = g1.getRoutingRules().get(0);
		Assertions.assertEquals(2, r.getPriority());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a2")), r.getSkills());
		
		script = """ 
		        queue to @S: a2 with priority 2
				queue to @S: a1 with priority 1
		        """;
		rule = (RoutingRuleSet)util.visitRoutingRuleSet(script, visitor);
		Assertions.assertEquals(1, rule.getRuleGroups().size());
		g1 = rule.getRuleGroups().get(0);
		Assertions.assertEquals(0, g1.getWaitAfterSeconds());
		Assertions.assertEquals(2, g1.getRoutingRules().size());
		
		//TODO: same skill with different priority should takes the highest priority or invalid
//		try {
//			script = """
//			        queue to @S:a2 and @S:a2 with priority 2
//					queue to @S:a1 with priority 1
//					queue to @S:a1 with priority 2
//				    wait 20
//				    queue to @S:a4 with priority 4
//				    wait 10
//				    queue to @S:a3 with priority 3
//				    """;
//			util.visitRoutingRuleSet(script, visitor);
//			Assertions.fail("Invalid queue to");
//		} catch(ParseCancellationException e) {
//			Assertions.assertEquals("line 3:0. Duplicated rule with different priority. One is 1 and one is 2", 
//					e.getMessage());
//		}
//		
		try {
			script = """
			        queue to @S: a2 with priority 2
					queue to @S: a1 with priority 1
				    wait 20
				    queue to @S: a4 with priority 4
				    wait 10
				    queue to @S: a3 with priority 3
				    wait 100
			        """;
			util.visitRoutingRuleSet(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 8:0 mismatched input '<EOF>' expecting 'queue to'", 
					e.getMessage());
		}
		
		try {
			script = """
			        queue to @S: a2 with priority 2
					queue to @S: a1 with priority 1
					wait 20
					queue to @S: a4 with priority 4
					wait 10
					wait 10
					queue to @S: a3 with priority 3 
			        """;
			
			util.visitRoutingRuleSet(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 6:0 mismatched input 'wait' expecting 'queue to'", 
					e.getMessage());
		}
		
	}
	
	@Test
	void routingWaitRuleGroupTest() {
		
		String script = """
		        	    wait 10
		        		queue to @S: a1 with priority 1
		        """;
		
		Node rule = util.visitRoutingWaitRuleGroup(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRuleGroup);
		RoutingRuleGroup routingRuleGroup = ((RoutingRuleGroup)rule);
		Assertions.assertEquals(10, routingRuleGroup.getWaitAfterSeconds());
		
		
		List<RoutingRule> rules = routingRuleGroup.getRules();
		Assertions.assertEquals(1, rules.size());
		RoutingRule routingRule = rules.iterator().next();
		Assertions.assertEquals(ActionType.QUEUE_TO, routingRule.getAction().getAction());
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a1")), routingRule.getSkills());
		
		script = """
		        wait 10
				queue to @S: a2 with priority 2
				queue to @S: a1 with priority 1
		        """;
		
		rule = util.visitRoutingWaitRuleGroup(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRuleGroup);
		routingRuleGroup = ((RoutingRuleGroup)rule);
		Assertions.assertEquals(10, routingRuleGroup.getWaitAfterSeconds());
		rules = routingRuleGroup.getRules();
		Assertions.assertEquals(2, rules.size());
		int pri = 1;
		for(RoutingRule r : rules) {
			Assertions.assertEquals(pri, r.getPriority());
			Assertions.assertEquals(Set.of(new ComparableOidSet("a" + pri)), r.getSkills());
			pri++;
		}
		
		try {
			script = "queue to @S: a1 with priority 1";
			util.visitRoutingWaitRuleGroup(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 mismatched input 'queue to' expecting 'wait'", 
					e.getMessage());
		}
		
		try {
			script = """
			        test queue to @S: a2 with priority 2
					wait 10 
					queue to @S: a1 with priority 1
			        """;
			util.visitRoutingWaitRuleGroup(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:6 token recognition error at: 't'", 
					e.getMessage());
		}
	}
	
	
	@Test
	void routingRuleGroupTest() {

		String script = "queue to @S: a1 with priority 1";
		Node rule = util.visitRoutingRuleGroup(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRuleGroup);
		RoutingRuleGroup routingRuleGroup = ((RoutingRuleGroup)rule);
		List<RoutingRule> rules = routingRuleGroup.getRules();
		Assertions.assertEquals(1, rules.size());
		RoutingRule routingRule = rules.iterator().next();
		Assertions.assertEquals(ActionType.QUEUE_TO, routingRule.getAction().getAction());
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a1")), routingRule.getSkills());

		script = """
		        queue to @S: a2 with priority 2
				queue to @S: a1 with priority 1
		        """;
		rule = util.visitRoutingRuleGroup(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRuleGroup);
		routingRuleGroup = ((RoutingRuleGroup)rule);
		rules = routingRuleGroup.getRules();
		Assertions.assertEquals(2, rules.size());
		int pri = 1;
		for(RoutingRule r : rules) {
			Assertions.assertEquals(pri, r.getPriority());
			Assertions.assertEquals(Set.of(new ComparableOidSet("a" + pri)), r.getSkills());
			pri++;
		}
		
		try {
			script = """
			        test queue to @S: a2 with priority 2
					wait 10 
					queue to @S: a1 with priority 1
			        """;
			util.visitRoutingRuleGroup(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:6 token recognition error at: 't'", 
					e.getMessage());
		}
	}
	
	@Test
	void routingRuleTest() {

		String script = "queue to least busy of   @S: a1 with priority 1";
		Node rule = util.visitRoutingRule(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRule);
		RoutingRule routingRule = ((RoutingRule)rule);
		Assertions.assertEquals(AgentStatus.LEAST_BUSY, routingRule.getAgentStatus());
		Assertions.assertEquals(ActionType.QUEUE_TO, routingRule.getAction().getAction());
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a1")), routingRule.getSkills());
		
		script = "queue to @S: a1 and @S: a1 with priority 1";
		rule = util.visitRoutingRule(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRule);
		routingRule = ((RoutingRule)rule);
		Assertions.assertNull(routingRule.getAgentStatus());
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a1")), routingRule.getSkills());
		
		script = "queue to @S: a1 and @S: a2 with priority 1";
		rule = util.visitRoutingRule(script, visitor);
		Assertions.assertTrue(rule instanceof RoutingRule);
		routingRule = ((RoutingRule)rule);
		Assertions.assertEquals(1, routingRule.getPriority());
		Assertions.assertEquals(Set.of(new ComparableOidSet("a1"), new ComparableOidSet("a2")), routingRule.getSkills());
		
		try {
			script = "test queue to @S: a1";
			util.visitRoutingRule(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 token recognition error at: 't'", 
					e.getMessage());
		}
		
		try {
			script = "queue to @S: a1";
			util.visitRoutingRule(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:15 mismatched input '<EOF>' expecting {'and', 'with priority'}", 
					e.getMessage());
		}
		
		try {
			script = "queue to @S: a1 or @S: a1 with priority 1";
			util.visitRoutingRule(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:16 token recognition error at: 'o'", 
					e.getMessage());
		}
	}

	@Test
	void ruleActionTest() {
		String script = "queue to";
		Node rule = util.visitRuleAction(script, visitor);
		Assertions.assertTrue(rule instanceof ActionRule);
		Assertions.assertEquals(ActionType.QUEUE_TO, ((ActionRule)rule).getAction());
		
		try {
			script = "append to";
			util.visitRuleAction(script, visitor);
			Assertions.fail("Invalid queue to");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:1 token recognition error at: 'p'", 
					e.getMessage());
		}
	}
	
	@Test
	void waitingRuleTest() {
		String script = "wait 1";
		Node rule = util.visitWait(script, visitor);
		Assertions.assertTrue(rule instanceof WaitRule);
		Assertions.assertEquals(1, ((WaitRule)rule).getWaitFor());
		
		try {
			script = "wait ";
			util.visitWait(script, visitor);
			Assertions.fail("Invalid vistorWait");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:5 missing NUMBER at '<EOF>'", 
					e.getMessage());
		}
		
		try {
			script = "rest wait ";
			util.visitWait(script, visitor);
			Assertions.fail("Invalid vistorWait");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:0 token recognition error at: 'r'", 
					e.getMessage());
		}
	}
	
	@Test
	void oidRuleTest() {
		String script = "100a923";
		Node rule = util.visitOid(script, visitor);
		Assertions.assertTrue(rule instanceof OidRule);
		Assertions.assertEquals(script, ((OidRule)rule).getOid());
	}
	
	@Test
	void orderTest() {
		String script = "with priority 1";
		Node rule = util.visitOrder(script, visitor);
		Assertions.assertTrue(rule instanceof OrderRule);
		Assertions.assertEquals(1, ((OrderRule)rule).getPriority());
		
		try {
			script = "with priority -1";
			util.visitOrder(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:14 token recognition error at: '-'", 
					e.getMessage());
		}
		
		try {
			script = "with priority 01";
			util.visitOrder(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:14 mismatched input '01' expecting NUMBER", 
					e.getMessage());
		}
		
		try {
			script = "with priority";
			util.visitOrder(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:13 missing NUMBER at '<EOF>'", 
					e.getMessage());
		}
	}
	
	@Test
	void skillTest() {
		String script = "@S: 1 and";
		Node rule = util.visitSkill(script, visitor);
		Assertions.assertTrue(rule instanceof SkillRule);
		Assertions.assertEquals("1", ((SkillRule)rule).getSkillOid());
		
		script = "@S: 11abcdef1111";
		rule = util.visitSkill(script, visitor);
		Assertions.assertTrue(rule instanceof SkillRule);
		Assertions.assertEquals("11abcdef1111", ((SkillRule)rule).getSkillOid());
		
		try {
			script = "@S: skilloid";
			util.visitSkill(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:4 token recognition error at: 's'", 
					e.getMessage());
		}
		
		try {
			script = "@S: ";
			util.visitSkill(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:4 missing {NUMBER, UUID_OR_HEXA} at '<EOF>'", 
					e.getMessage());
		}
	}
	
	@Test
	void skillAndTest() {
		String script = "@S:1";
		AndSkillsRule rule = (AndSkillsRule)util.visitAndSkills(script, visitor);
		Assertions.assertTrue(rule instanceof AndSkillsRule);
		Assertions.assertEquals(Set.of(new ComparableOidSet("1")), rule.getSkills());
		
		script = "@S: 11abcdef1111";
		rule = (AndSkillsRule)util.visitAndSkills(script, visitor);
		Assertions.assertTrue(rule instanceof AndSkillsRule);
		Assertions.assertEquals(Set.of(new ComparableOidSet("11abcdef1111")), rule.getSkills());
		
		script = "@S: 11abcdef1111 and @S: 1";
		rule = (AndSkillsRule)util.visitAndSkills(script, visitor);
		Assertions.assertTrue(rule instanceof AndSkillsRule);
		Assertions.assertEquals(Set.of(new ComparableOidSet("11abcdef1111"), 
				new ComparableOidSet("1")), rule.getSkills());
		
		try {
			script = "@S: 11abcdef1111 and ";
			util.visitAndSkills(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:21 mismatched input '<EOF>' expecting {'(', '@S:'}", 
					e.getMessage());
		}
		
		try {
			script = "@S: 11abcdef1111 and @S: ";
			util.visitAndSkills(script, visitor);
			Assertions.fail("Invalid number");
		} catch(ParseCancellationException e) {
			Assertions.assertEquals("line 1:25 missing {NUMBER, UUID_OR_HEXA} at '<EOF>'", 
					e.getMessage());
		}
	}
	
	@Test
    void skillSetTest() {
	    String  script = "(@S:11abcdef1111, @S:11abcdef2222)";
	    SkillSetRule rule = (SkillSetRule)util.visitSkillSet(script, visitor);
	    Assertions.assertEquals(new ComparableOidSet(List.of("11abcdef1111", "11abcdef2222")), rule.getSkillSetKey());
	    
	    try {
	        script = "(@S:11abcdef1111)";
	        util.visitSkillSet(script, visitor);
	        Assertions.fail("Invalid number");
	    } catch(ParseCancellationException e) {
	        Assertions.assertEquals("line 1:16 mismatched input ')' expecting ','", 
	                e.getMessage());
	    }
	    
	    try {
	        script = "(@S:11abcdef1111 and @S:11abcdef1111)";
	        util.visitSkillSet(script, visitor);
	        Assertions.fail("Invalid number");
	    } catch(ParseCancellationException e) {
	        Assertions.assertEquals("line 1:17 mismatched input 'and' expecting ','", 
	                e.getMessage());
	    } 
	}
	
	@Test
	void skillOrSet() {
	    String  script = "(@S:11abcdef1111, @S:11abcdef2222)";
	    SkillSetRule rule = (SkillSetRule)util.visitSkillOrSet(script, visitor);
	    Assertions.assertEquals(new ComparableOidSet(List.of("11abcdef1111", "11abcdef2222")), rule.getSkillSetKey());
	    
	    script = "@S:11abcdef1111 ";
	    rule = (SkillSetRule)util.visitSkillOrSet(script, visitor);
        Assertions.assertEquals(new ComparableOidSet("11abcdef1111"), rule.getSkillSetKey());
        
	    try {
	        script = "(@S:11abcdef1111)";
	        util.visitSkillOrSet(script, visitor);
	        Assertions.fail("Invalid number");
	    } catch(ParseCancellationException e) {
	        Assertions.assertEquals("line 1:16 mismatched input ')' expecting ','", 
	                e.getMessage());
	    }
	    
	    try {
	        script = "(@S:11abcdef1111 and @S:11abcdef1111)";
	        util.visitSkillOrSet(script, visitor);
	        Assertions.fail("Invalid number");
	    } catch(ParseCancellationException e) {
	        Assertions.assertEquals("line 1:17 mismatched input 'and' expecting ','", 
	                e.getMessage());
	    } 
	}
}
