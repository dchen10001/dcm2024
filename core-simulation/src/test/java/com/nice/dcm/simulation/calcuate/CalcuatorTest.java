package com.nice.dcm.simulation.calcuate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CalcuatorTest {
	Calcuator calcuator = new Calcuator();
	
	@Test
	void multipilerTest() {
		Calcuator calcuator = new Calcuator();
		assertEquals(1.0, calcuator.getMultipier(1));
		assertEquals(1.1, calcuator.getMultipier(2));
		assertEquals(1.2, calcuator.getMultipier(3));
		assertEquals(1.3, calcuator.getMultipier(4));
		assertEquals(1.4, calcuator.getMultipier(5));
		assertEquals(1.5, calcuator.getMultipier(6));
	}
	
	@Test
	void adjusted2ContactsTest() {
		double[] remainingTimes = new double[] {8, 10};
		double[] adjustedRemainingTimes = calcuator.adjustRemainingTime(remainingTimes);
		assertEquals(8.8, adjustedRemainingTimes[0]);
		assertEquals(10.8, adjustedRemainingTimes[1]);
		
		adjustedRemainingTimes = calcuator.adjustRemainingTimeEx(remainingTimes);
		assertEquals(8.8, adjustedRemainingTimes[0]);
		assertEquals(10.8, adjustedRemainingTimes[1]);
	}
	
	
	@Test
	void adjusted3ContactsTest() {
		double[] remainingTimes = new double[] {6.8, 8.8, 10};
		double[] adjustedRemainingTimes = calcuator.adjustRemainingTime(remainingTimes);

		assertTrue(Math.abs(8.16 - adjustedRemainingTimes[0]) < 0.001);
		assertTrue(Math.abs(10.36 - adjustedRemainingTimes[1])< 0.001);
		assertTrue(Math.abs(11.56 - adjustedRemainingTimes[2])< 0.001);
		
		adjustedRemainingTimes = calcuator.adjustRemainingTimeEx(remainingTimes);
		assertTrue(Math.abs(8.16 - adjustedRemainingTimes[0]) < 0.001);
		assertTrue(Math.abs(10.36 - adjustedRemainingTimes[1])< 0.001);
		assertTrue(Math.abs(11.56 - adjustedRemainingTimes[2])< 0.001);
	}
	
	@Test
	void adjustedMoreContactsTest() {
		double[] remainingTimes = new double[] {8, 8, 8, 10, 10};
		double[] adjustedRemainingTimes = calcuator.adjustRemainingTimeEx(remainingTimes);

		//8 * 1.4 = 11.2
		assertTrue(Math.abs(11.2 - adjustedRemainingTimes[0]) < 0.001);
		assertTrue(Math.abs(11.2 - adjustedRemainingTimes[1])< 0.001);
		assertTrue(Math.abs(11.2 - adjustedRemainingTimes[2])< 0.001);
		
		// 2 * 1.1 = 2.2
		// 11.2 + 2.2 = 13.4
		assertTrue(Math.abs(13.4 - adjustedRemainingTimes[3])< 0.001);
		assertTrue(Math.abs(13.4 - adjustedRemainingTimes[4])< 0.001);
	}
}
