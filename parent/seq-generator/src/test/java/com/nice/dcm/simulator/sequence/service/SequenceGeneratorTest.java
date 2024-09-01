package com.nice.dcm.simulator.sequence.service;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SequenceGeneratorTest {
	@Test
	void generatorTest() {
		SequenceGenerator generator = new SequenceGenerator();
		int numOfSeq  = 10;
		int intervalLen = 9;
		Collection<Integer> seeds = generator.generate(numOfSeq, intervalLen);

		Assertions.assertEquals(numOfSeq, seeds.size());
		for(Integer seed : seeds) {
			Assertions.assertTrue(seed < intervalLen && seed >= 0);
		}
		
		seeds = generator.generate(0, intervalLen);
		Assertions.assertTrue(seeds.isEmpty());
	}
}
