package com.nice.dcm.simulation.distribution.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.BinarySkillLevelConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSetSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

class RoutingRuleSetImplTest {
	@Test
	void testConstruction() {
		
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"), new SkillSelectorImpl("skillOid2", condition));
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);		
		List<SkillSetSelector> selectors = List.of(skillSelector);
		RoutingRule rule = new RoutingRuleImpl(selectors, 1);
		RoutingGroupRule defaultGroupRule = new RoutingGroupRuleImpl(rule);
		
		
		
		SkillLevelCondition condition1 = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		List<SkillSelector> skillSelectorsList1 = List.of(new SkillSelectorImpl("skillOid1"), new SkillSelectorImpl("skillOid2", condition1));
		SkillSetSelectorImpl skillSelector1 = new SkillSetSelectorImpl(skillSelectorsList1);		
		List<SkillSetSelector> selectors1 = List.of(skillSelector1);
		RoutingRule rule1 = new RoutingRuleImpl(selectors1, 2);
		
		SkillLevelCondition condition2 = new BinarySkillLevelConditionImpl(BinaryOperator.EQUAL, 2);
		List<SkillSelector> skillSelectorsList2 = List.of(new SkillSelectorImpl("skillOid3"), new SkillSelectorImpl("skillOid4", condition2));
		SkillSetSelectorImpl skillSelector2 = new SkillSetSelectorImpl(skillSelectorsList2);		
		List<SkillSetSelector> selectors2 = List.of(skillSelector2);
		RoutingRule rule2 = new RoutingRuleImpl(selectors2, 1);

		List<RoutingGroupRule> groupRules = List.of(new RoutingGroupRuleImpl(100, rule1), new RoutingGroupRuleImpl(100, rule2));
		
		RoutingRuleSet routingRuleSet = new RoutingRuleSetImpl(defaultGroupRule);
		assertEquals(defaultGroupRule, routingRuleSet.getGroupRule());
		assertEquals(0, routingRuleSet.getGroupRules().size());
		
		routingRuleSet = new RoutingRuleSetImpl(defaultGroupRule, null);
		assertEquals(defaultGroupRule, routingRuleSet.getGroupRule());
		assertEquals(0, routingRuleSet.getGroupRules().size());
		
		routingRuleSet = new RoutingRuleSetImpl(defaultGroupRule, groupRules);
		assertEquals(defaultGroupRule, routingRuleSet.getGroupRule());
		assertEquals(2, routingRuleSet.getGroupRules().size());
		
		Exception exception = assertThrows(NullPointerException.class, () -> {
			new RoutingRuleSetImpl(null);
		});

		assertEquals("groupRule is marked non-null but is null", exception.getMessage());
		
		exception = assertThrows(NullPointerException.class, () -> {
			new RoutingRuleSetImpl(null, null);
		});
		assertEquals("groupRule is marked non-null but is null", exception.getMessage());
		
		final List<RoutingGroupRule> list = List.of(new RoutingGroupRuleImpl(rule1), new RoutingGroupRuleImpl(100, rule2));
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new RoutingRuleSetImpl(defaultGroupRule, list);
		});
		assertEquals("waitAfterSeconds must be greater than zero in list of group rules.", exception.getMessage());
	}
}
