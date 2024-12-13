package com.nice.dcm.simulation.distribution.rule.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SqlOperatorTest {
	@Test
	void testEvaluateIn() {
		SqlOperator operator = SqlOperator.IN;
		assertEquals("in", operator.getOperator());
		assertTrue(operator.evaluate(1, 2, 0));
		assertTrue(operator.evaluate(1, 2, 1));
		assertFalse(operator.evaluate(1, 2, 2));
		assertFalse(operator.evaluate(1, 2, 3));
		assertFalse(operator.evaluate(1, 2, 4));
	}
	
	@Test
	void testEvaluateNotIn() {
		SqlOperator operator = SqlOperator.NOT_IN;
		assertEquals("not in", operator.getOperator());
		assertFalse(operator.evaluate(1, 2, 0));
		assertFalse(operator.evaluate(1, 2, 1));
		assertTrue(operator.evaluate(1, 2, 2));
		assertTrue(operator.evaluate(1, 2, 3));
		assertTrue(operator.evaluate(1, 2, 4));
	}
	
	@Test
	void testEvaluate() {
		SqlOperator operator = SqlOperator.NOT_USED;
		assertEquals("", operator.getOperator());
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> operator.evaluate(1, 2, 3));
		assertTrue(e.getMessage().contains("Unknown operator"));
	}
	
	@Test
	void testFromStringValue() {
		assertSame(SqlOperator.IN, SqlOperator.fromString("in"));
		assertSame(SqlOperator.NOT_IN, SqlOperator.fromString("not in"));

		Exception e = assertThrows(IllegalArgumentException.class, () -> SqlOperator.fromString("unknown"));
		assertTrue(e.getMessage().contains("unknown"));
	}
}
