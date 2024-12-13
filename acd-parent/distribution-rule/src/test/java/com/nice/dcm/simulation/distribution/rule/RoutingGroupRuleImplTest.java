package com.nice.dcm.simulation.distribution.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.operator.AndSkillLevelConditionsImpl;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SkillLevelConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

public class RoutingGroupRuleImplTest {
	@Test
	public void testEvaluate() {
		List<RoutingRule> rules = getRules();
		RoutingGroupRule routingGroupRuleImpl = new RoutingGroupRuleImpl(rules);
		assertEquals(0, routingGroupRuleImpl.getWaitAfterSeconds());
		assertEquals(100, routingGroupRuleImpl.getWaitAfterSeconds());
        assertEquals(3, routingGroupRuleImpl.getRules().size());
        int priority = 1;
		for (RoutingRule r : routingGroupRuleImpl.getRules()) {
			int p = r.getPriority();
			assertTrue(priority <= p);
			if (p > priority) {
				priority = p;
			}			
		}
	}
	
	@Test
	public void testHashEquals() {
		SkillLevelCondition s = new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 10));

		SkillLevelCondition s1 = new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 10));
		SkillLevelCondition s2 = new SkillLevelConditionImpl("oid1", new SqlConditionImpl(SqlOperator.IN, 10, 20));
		SkillLevelCondition s3 = new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 20));
		SkillLevelCondition s4 = new SkillLevelConditionImpl("oid2", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 10));

		assertEquals(s, s1);
		assertEquals(s.hashCode(), s1.hashCode());
		assertTrue(s.equals(s1));
		
		assertNotEquals(s, s2);
		assertNotEquals(s.hashCode(), s2.hashCode());
		assertFalse(s.equals(s2));
		
		assertNotEquals(s, s3);
		assertNotEquals(s.hashCode(), s3.hashCode());
		assertFalse(s.equals(s3));
		
		assertNotEquals(s, s4);
		assertNotEquals(s.hashCode(), s4.hashCode());
		assertFalse(s.equals(s4));
		
		AndSkillLevelConditions a = new AndSkillLevelConditionsImpl(List.of(s1, s2, s3));
		
		AndSkillLevelConditions a1 = new AndSkillLevelConditionsImpl(List.of(s3, s1, s2));
		
		AndSkillLevelConditions a2 = new AndSkillLevelConditionsImpl(List.of(s1, s2, s3));
		
		AndSkillLevelConditions a3 = new AndSkillLevelConditionsImpl(List.of(s1));
		AndSkillLevelConditions a4 = new AndSkillLevelConditionsImpl(List.of(s1, s2));
		
		assertEquals(a, a2);
		assertEquals(a.hashCode(), a2.hashCode());
		assertTrue(a.equals(a2));
		
		assertEquals(a, a1);
		assertEquals(a.hashCode(), a1.hashCode());
		assertTrue(a.equals(a1));
		
		assertNotEquals(a, a3);
		assertNotEquals(a.hashCode(), a3.hashCode());
		assertFalse(a.equals(a3));
		
		assertNotEquals(a, a4);
		assertNotEquals(a.hashCode(), a4.hashCode());
		assertFalse(a.equals(a4));
	}
	
	
	private List<RoutingRule> getRules() {
		AndSkillLevelConditions andSkillLevelConditions1 = new AndSkillLevelConditionsImpl(
						List.of(new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 10)),
								new SkillLevelConditionImpl("oid2", new BinaryConditionImpl(BinaryOperator.EQUAL, 10)))
					);
		
		AndSkillLevelConditions andSkillLevelConditions2 = new AndSkillLevelConditionsImpl(
				List.of(new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 20)),
						new SkillLevelConditionImpl("oid2", new BinaryConditionImpl(BinaryOperator.EQUAL, 10)))
				);
		
		AndSkillLevelConditions andSkillLevelConditions3 = new AndSkillLevelConditionsImpl(
				List.of(new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 10)),
						new SkillLevelConditionImpl("oid2", new BinaryConditionImpl(BinaryOperator.EQUAL, 10)))
			);

		AndSkillLevelConditions andSkillLevelConditions4 = new AndSkillLevelConditionsImpl(
				List.of(new SkillLevelConditionImpl("oid1", new BinaryConditionImpl(BinaryOperator.GREATER_THAN, 10)),
						new SkillLevelConditionImpl("oid2", new BinaryConditionImpl(BinaryOperator.EQUAL, 10)))
				);

		RoutingRuleImpl routingRuleImpl1 = new RoutingRuleImpl(Set.of(andSkillLevelConditions1, andSkillLevelConditions2), 1);
		RoutingRuleImpl routingRuleImpl2 = new RoutingRuleImpl(Set.of(andSkillLevelConditions3), 2);
		RoutingRuleImpl routingRuleImpl3 = new RoutingRuleImpl(Set.of(andSkillLevelConditions4), 1);
		return List.of(routingRuleImpl1, routingRuleImpl2, routingRuleImpl3);
	}
}
