package com.nice.dcm.simulator.sequence.service;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import com.nice.dcm.simulator.sequence.spi.IntGenerator;

public class RandomGenerator implements IntGenerator {

	@Override
	public Collection<Integer> generate(int numOfSeq, int intervalLength) {
		final int ratio = (numOfSeq > intervalLength) ? (1+ numOfSeq / intervalLength) : 1;
		
		IntStream seeds = ThreadLocalRandom.current()
				.ints(0, intervalLength * ratio)
				.distinct().limit(numOfSeq);
		
		return seeds.boxed()
				.sorted()
				.map(s -> applyRatio(s, ratio, intervalLength))
				.toList();
	}
	
	private int applyRatio(int s, int ratio, int intervalLength) {
		int seed = (int)Math.round((double)s / ratio);
		if(seed >= intervalLength) {
			return seed - 1;
		}
		return seed;
	}
}
