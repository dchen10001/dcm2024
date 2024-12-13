package com.nice.dcm.simulation.distribution.rule.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BinaryOperatorTest {
	@Test
	void testEvaluateLessThan() {
		BinaryOperator operator = BinaryOperator.LESS_THAN;
		assertEquals("<", operator.getOperator());
		assertTrue(operator.evaluate(1, 2));
		assertFalse(operator.evaluate(1, 1));
		assertFalse(operator.evaluate(2, 1));
	}

	@Test
	void testEvaluateLessThanOrEqual() {
		BinaryOperator operator = BinaryOperator.LESS_THAN_OR_EQUAL;
		assertEquals("<=", operator.getOperator());
		assertTrue(operator.evaluate(1, 2));
		assertTrue(operator.evaluate(1, 1));
		assertFalse(operator.evaluate(2, 1));
	}
	
	@Test
	void testEvaluateEqual() {
		BinaryOperator operator = BinaryOperator.EQUAL;
		assertEquals("=", operator.getOperator());
		assertFalse(operator.evaluate(1, 2));
		assertTrue(operator.evaluate(1, 1));
		assertFalse(operator.evaluate(2, 1));
	}
	
	@Test
	void testEvaluateNotEqual() {
		BinaryOperator operator = BinaryOperator.NOT_EQUAL;
		assertEquals("<>", operator.getOperator());
		assertTrue(operator.evaluate(1, 2));
		assertFalse(operator.evaluate(1, 1));
		assertTrue(operator.evaluate(2, 1));
	}
	
	@Test
	void testEvaluateGreaterThan() {
		BinaryOperator operator = BinaryOperator.GREATER_THAN;
        assertEquals(">", operator.getOperator());
        assertFalse(operator.evaluate(1, 2));
        assertFalse(operator.evaluate(1, 1));
        assertTrue(operator.evaluate(2, 1));
	}
	
	@Test
	void testEvaluateGreaterEqual() {
		BinaryOperator operator = BinaryOperator.GREATER_THAN_OR_EQUAL;
		assertEquals(">=", operator.getOperator());
		assertFalse(operator.evaluate(1, 2));
		assertTrue(operator.evaluate(1, 1));
		assertTrue(operator.evaluate(2, 1));
	}
	
	@Test
	void testEvaluate() {
		BinaryOperator operator = BinaryOperator.NOT_USED;
		assertEquals("", operator.getOperator());
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> operator.evaluate(1, 2));
		assertTrue(e.getMessage().contains("Unknown operator"));
	}
	
	@Test
	void testFromStringValue() {
		assertSame(BinaryOperator.LESS_THAN, BinaryOperator.fromString("<"));
		assertSame(BinaryOperator.LESS_THAN_OR_EQUAL, BinaryOperator.fromString("<="));
		assertSame(BinaryOperator.EQUAL, BinaryOperator.fromString("="));
		assertSame(BinaryOperator.NOT_EQUAL, BinaryOperator.fromString("<>"));
		assertSame(BinaryOperator.GREATER_THAN, BinaryOperator.fromString(">"));
		assertSame(BinaryOperator.GREATER_THAN_OR_EQUAL, BinaryOperator.fromString(">="));
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> BinaryOperator.fromString("unknown"));
		assertTrue(e.getMessage().contains("unknown"));
	}
}
