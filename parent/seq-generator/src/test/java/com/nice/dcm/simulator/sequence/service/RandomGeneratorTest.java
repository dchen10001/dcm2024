package com.nice.dcm.simulator.sequence.service;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomGeneratorTest {
	@Test
	void generatorTest() {
		RandomGenerator generator = new RandomGenerator();
		int numOfSeq  = 17;
		int intervalLen = 9;
		Collection<Integer> seeds = generator.generate(numOfSeq, intervalLen);

		Assertions.assertEquals(numOfSeq, seeds.size());
		int lastSeed = -1;
		for(Integer seed : seeds) {
			System.out.println(seed);
			Assertions.assertTrue(seed < intervalLen && seed >= 0);
			Assertions.assertTrue(lastSeed <= seed);
			lastSeed = seed;
		}
		
		seeds = generator.generate(0, intervalLen);
		Assertions.assertTrue(seeds.isEmpty());
	}
}
