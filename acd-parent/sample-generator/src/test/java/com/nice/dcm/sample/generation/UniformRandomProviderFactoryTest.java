package com.nice.dcm.sample.generation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.apache.commons.rng.UniformRandomProvider;
import org.junit.jupiter.api.Test;

public class UniformRandomProviderFactoryTest {
	@Test
	void testCreateMersenneTwisteSeed() {
		UniformRandomProvider provider = UniformRandomProviderFactory.createMersenneTwister(100L);
		assertNotNull(provider);
		
		IntStream intStream = provider.ints(100);
		int[] intArray = intStream.toArray();
		Double average = average(intArray);
		int max = max(intArray);
		int min = min(intArray);
		assertTrue(average >= min);
		assertTrue(average <= max);
		assertEquals(100, intArray.length);
	}
	
	private double average(int[] intArray) {
		double sum = 0;
		for (int v : intArray) {
			sum += v;
		}
		return sum / intArray.length;
	}
	
	private int min(int[] intArray) {
		int min = Integer.MAX_VALUE;
		for (int v : intArray) {
			if (min > v) {
				min = v;
			}
		}
		return min;
	}
	
	
	private int max(int[] intArray) {
		int max = 0;
		for (int v : intArray) {
			if (max < v) {
				max = v;
			}
		}
		return max;
	}

	@Test
	void testCreateMersenneTwister() {
		UniformRandomProvider provider = UniformRandomProviderFactory.createMersenneTwister();
		assertNotNull(provider);
		
		IntStream intStream = provider.ints(100);
		int[] intArray = intStream.toArray();
		Double average = average(intArray);
		int max = max(intArray);
		int min = min(intArray);
		assertTrue(average >= min);
		assertTrue(average <= max);
		assertEquals(100, intArray.length);
	}
	
	@Test
	void testKissSeed() {
		UniformRandomProvider provider = UniformRandomProviderFactory.createKiss(123L);
		assertNotNull(provider);
		
		IntStream intStream = provider.ints(100);
		int[] intArray = intStream.toArray();
		Double average = average(intArray);
		int max = max(intArray);
		int min = min(intArray);
		assertTrue(average >= min);
		assertTrue(average <= max);
		assertEquals(100, intArray.length);
	}
	
	@Test
	void testKiss() {
		UniformRandomProvider provider = UniformRandomProviderFactory.createKiss();
		assertNotNull(provider);
		
		IntStream intStream = provider.ints(100);
		int[] intArray = intStream.toArray();
		Double average = average(intArray);
		int max = max(intArray);
		int min = min(intArray);
		assertTrue(average >= min);
		assertTrue(average <= max);
		assertEquals(100, intArray.length);
	}
}
