package com.nice.dcm.simulation.distribution.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.BinarySkillLevelConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class RoutingGroupRuleImplTest {
	@Test
	void testConstruction() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"), new SkillSelectorImpl("skillOid2", condition));
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);		
		Collection<SkillSetSelector> selectors = List.of(skillSelector);

		RoutingRule rule = new RoutingRuleImpl(selectors, 1);
		RoutingGroupRule groupRule = new RoutingGroupRuleImpl(rule);
		assertEquals(0, groupRule.getWaitAfterSeconds());
		assertEquals(1, groupRule.getRules().size());
		assertEquals(rule, groupRule.getRules().get(0));
		
		groupRule = new RoutingGroupRuleImpl(300, rule);
		assertEquals(300, groupRule.getWaitAfterSeconds());
		assertEquals(1, groupRule.getRules().size());
		assertEquals(rule, groupRule.getRules().get(0));
		
		Exception exception = assertThrows(NullPointerException.class, () -> {
			new RoutingGroupRuleImpl((RoutingRule)null);
		});
		assertEquals("rule is marked non-null but is null", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingGroupRuleImpl(-1, rule);
		});
		assertEquals("waitAfterSeconds must be greater than or equal to zero", exception.getMessage());
		
	}
	
	@Test
	void testConstructionList() {
		SkillLevelCondition condition1 = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		List<SkillSelector> skillSelectorsList1 = List.of(new SkillSelectorImpl("skillOid1"), new SkillSelectorImpl("skillOid2", condition1));
		SkillSetSelectorImpl skillSelector1 = new SkillSetSelectorImpl(skillSelectorsList1);		
		Collection<SkillSetSelector> selectors1 = List.of(skillSelector1);
		
		RoutingRule rule1 = new RoutingRuleImpl(selectors1, 2);
		
		SkillLevelCondition condition2 = new BinarySkillLevelConditionImpl(BinaryOperator.EQUAL, 2);
		List<SkillSelector> skillSelectorsList2 = List.of(new SkillSelectorImpl("skillOid3"), new SkillSelectorImpl("skillOid4", condition2));
		SkillSetSelectorImpl skillSelector2 = new SkillSetSelectorImpl(skillSelectorsList2);		
		Collection<SkillSetSelector> selectors2 = List.of(skillSelector2);
		RoutingRule rule2 = new RoutingRuleImpl(selectors2, 1);
		
		
		RoutingGroupRule groupRule = new RoutingGroupRuleImpl(List.of(rule1, rule2));
		assertEquals(0, groupRule.getWaitAfterSeconds());
		assertEquals(2, groupRule.getRules().size());
		assertEquals(rule2, groupRule.getRules().get(0));
		assertEquals(rule1, groupRule.getRules().get(1));
		
		groupRule = new RoutingGroupRuleImpl(300, List.of(rule1, rule2));
		assertEquals(300, groupRule.getWaitAfterSeconds());
		assertEquals(2, groupRule.getRules().size());
		assertEquals(rule2, groupRule.getRules().get(0));
		assertEquals(rule1, groupRule.getRules().get(1));

		final List<RoutingRule> rules = null;
		Exception exception = assertThrows(NullPointerException.class, () -> {
			new RoutingGroupRuleImpl(rules);
		});
		assertEquals("rules is marked non-null but is null", exception.getMessage());
		
		exception = assertThrows(NullPointerException.class, () -> {
			new RoutingGroupRuleImpl(0, rules);
		});
		assertEquals("rules is marked non-null but is null", exception.getMessage());
		
		final List<RoutingRule> rules1 = List.of();
		exception = assertThrows(IllegalArgumentException.class, () -> {
				new RoutingGroupRuleImpl(rules1);
			});
		assertEquals("rules is empty", exception.getMessage());
		
		final List<RoutingRule> rules2 = List.of();
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingGroupRuleImpl(100, rules2);
		});
		assertEquals("rules is empty", exception.getMessage());
		
		final List<RoutingRule> rules3 = List.of(rule1, rule2);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingGroupRuleImpl(-1, rules3);
		});
		
		assertEquals("waitAfterSeconds must be greater than or equal to zero", e1.getMessage());
	}
	
	@Test
	void testConstructionRuleMerge() {
		//queue to @S: 1111a level = 2 with priority 2
		RoutingRule rule1 = new RoutingRuleImpl(List.of(new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("1111a", 
				new BinarySkillLevelConditionImpl(BinaryOperator.EQUAL, 2))))), 2);
		assertEquals(1, rule1.getSelectors().size());
		
		//queue to @S: 1111a level < 2 with priority 2
		RoutingRule rule2 = new RoutingRuleImpl(List.of(new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("1111a", 
				new BinarySkillLevelConditionImpl(BinaryOperator.LESS_THAN, 2))))), 2);
		assertEquals(1, rule2.getSelectors().size());
		
        //queue to least busy of @S: 1111a and @S: 3333a with priority 1
	    RoutingRule rule3 = new RoutingRuleImpl(QueueStatus.LEAST_BUSY,
	    		List.of(new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("1111a"))),
	    				new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("3333a")))), 
	    		1);
	    assertEquals(2, rule3.getSelectors().size());
	    assertTrue(rule3.isLeastBusyOf());
	    
        //queue to @S: 1111a with priority 1
	    RoutingRule rule4 = new RoutingRuleImpl(
	    		List.of(new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("1111a")))), 1);
	    assertEquals(1, rule4.getSelectors().size());
	    assertFalse(rule4.isLeastBusyOf());
	    
        //queue to least busy of @S: 3333a with priority 1
	    RoutingRule rule5 = new RoutingRuleImpl(QueueStatus.LEAST_BUSY,
	    		List.of(new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("3333a")))), 1);
	    assertEquals(1, rule5.getSelectors().size());
	    assertFalse(rule5.isLeastBusyOf());
	    
        //queue to @S: 1111a and @S: 2222a with priority 1
	    RoutingRule rule6 = new RoutingRuleImpl(
	    		List.of(new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("1111a"))),
	    				new SkillSetSelectorImpl(List.of(new SkillSelectorImpl("2222a")))), 	    		
	    		1);
	    assertEquals(2, rule6.getSelectors().size());
	    assertFalse(rule6.isLeastBusyOf());
	    
		RoutingGroupRule routingGroupRule = new RoutingGroupRuleImpl(List.of(rule1, rule2, rule3, rule4, rule5, rule6));
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
	}
}
