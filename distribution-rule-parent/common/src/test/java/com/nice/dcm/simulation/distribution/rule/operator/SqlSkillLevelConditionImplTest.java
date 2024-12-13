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

class SqlSkillLevelConditionImplTest {
	@Test
	void testConstructor() {
		SqlOperator operator = SqlOperator.IN;
        SqlSkillLevelConditionImpl condition = new SqlSkillLevelConditionImpl(operator, 1, 2);
        assertSame(operator, condition.getOperator());
        assertEquals(1, condition.getLowerBound());
        assertEquals(2, condition.getUpperBound());
        
        Exception e = assertThrows(IllegalArgumentException.class, () -> new SqlSkillLevelConditionImpl(operator, 2, 1));
        assertTrue(e.getMessage().contains("upperBound is less than lowerBound"));
        
        e = assertThrows(IllegalArgumentException.class, () -> new SqlSkillLevelConditionImpl(operator, -2, 1));
        assertTrue(e.getMessage().contains("lowerBound is negative"));
        
        e = assertThrows(NullPointerException.class, () -> new SqlSkillLevelConditionImpl(null, 1, 2));
        assertTrue(e.getMessage().contains("operator is marked non-null but is null"));
	}
	
	@Test
	void testEvaluate() {
		SqlOperator operator = SqlOperator.IN;
		SqlSkillLevelConditionImpl condition = new SqlSkillLevelConditionImpl(operator, 1, 2);
		assertTrue(condition.evaluate(1));
		assertTrue(condition.evaluate(2));
		assertFalse(condition.evaluate(3));
	}
	
	@Test
	void testCompareTo() {
		SqlOperator operator = SqlOperator.IN;
        SqlSkillLevelConditionImpl condition = new SqlSkillLevelConditionImpl(operator, 1, 2);
        SqlSkillLevelConditionImpl condition2 = new SqlSkillLevelConditionImpl(operator, 1, 2);
        assertEquals(0, condition.compareTo(condition2));
        assertTrue(condition.compareTo(null)>0);
        
        SqlSkillLevelConditionImpl condition1 = new SqlSkillLevelConditionImpl(operator, 1, 1);
        assertTrue(condition.compareTo(condition1)>0);
        
        SqlSkillLevelConditionImpl condition3 = new SqlSkillLevelConditionImpl(operator, 2, 3);
        assertTrue(condition.compareTo(condition3)<0);
        
        SqlSkillLevelConditionImpl conditionNot = new SqlSkillLevelConditionImpl(SqlOperator.NOT_IN, 1, 2);
        assertTrue(condition.compareTo(conditionNot)<0);
        
        BinarySkillLevelConditionImpl binaryCondition = new BinarySkillLevelConditionImpl(BinaryOperator.EQUAL, 1);
        assertTrue(condition.compareTo(binaryCondition)<0);
        
        List<SkillLevelCondition> list = new ArrayList<>();
        list.add(binaryCondition);
        list.add(condition2);
        list.add(condition1);
        list.add(conditionNot);
        list.add(condition3);
        
        Collections.sort(list);
        assertSame(condition1, list.get(0));
        assertSame(condition2, list.get(1));
        assertSame(condition3, list.get(2));
        assertSame(conditionNot, list.get(3));
        assertSame(binaryCondition, list.get(4));
	}
}
