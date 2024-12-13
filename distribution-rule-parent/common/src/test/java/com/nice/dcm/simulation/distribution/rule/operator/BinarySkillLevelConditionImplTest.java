package com.nice.dcm.simulation.distribution.rule.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;

class BinarySkillLevelConditionImplTest {
	@Test
	void testConstructor() {
		BinaryOperator operator = BinaryOperator.EQUAL;
		BinarySkillLevelConditionImpl condition = new BinarySkillLevelConditionImpl(operator, 2);
		assertSame(operator, condition.getOperator());
		assertEquals(2, condition.getRight());
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> new BinarySkillLevelConditionImpl(operator, -1));
		assertTrue(e.getMessage().contains("right is negative"));
		
		e = assertThrows(NullPointerException.class, () -> new BinarySkillLevelConditionImpl(null, 2));
		assertTrue(e.getMessage().contains("operator is marked non-null but is null"));
	}
	
	@Test
	void testEvaluate() {
		BinaryOperator operator = BinaryOperator.EQUAL;
		BinarySkillLevelConditionImpl condition = new BinarySkillLevelConditionImpl(operator, 2);
		assertTrue(condition.evaluate(2));
		assertFalse(condition.evaluate(3));
	}
	
	@Test
	void testCompareTo() {
		BinaryOperator operator = BinaryOperator.EQUAL;
		BinarySkillLevelConditionImpl condition = new BinarySkillLevelConditionImpl(operator, 2);
		BinarySkillLevelConditionImpl condition2 = new BinarySkillLevelConditionImpl(operator, 2);
		assertEquals(0, condition.compareTo(condition2));
		assertTrue(condition.compareTo(null)>0);
		
		BinarySkillLevelConditionImpl condition1 = new BinarySkillLevelConditionImpl(operator, 1);
		assertTrue(condition.compareTo(condition1)>0);
		
		BinarySkillLevelConditionImpl condition3 = new BinarySkillLevelConditionImpl(operator, 3);
		assertTrue(condition.compareTo(condition3)<0);
		
		BinarySkillLevelConditionImpl conditionNot2 = new BinarySkillLevelConditionImpl(BinaryOperator.NOT_EQUAL, 2);
		assertTrue(condition.compareTo(conditionNot2)<0);
		
		SqlSkillLevelConditionImpl sqlCondition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		assertTrue(condition.compareTo(sqlCondition)>0);
		
		List<SkillLevelCondition> list = new ArrayList<>();
		list.add(sqlCondition);
		list.add(condition2);
		list.add(condition1);
		list.add(condition3);
		list.add(conditionNot2);
		
		Collections.sort(list);
		assertEquals(sqlCondition, list.get(0));
		assertEquals(condition1, list.get(1));
		assertEquals(condition2, list.get(2));
		assertEquals(condition3, list.get(3));
	}
}
