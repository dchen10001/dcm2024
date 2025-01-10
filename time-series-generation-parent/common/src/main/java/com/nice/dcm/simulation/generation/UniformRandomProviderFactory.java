package com.nice.dcm.simulation.generation;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

public class UniformRandomProviderFactory {
	private UniformRandomProviderFactory() {	
	}
	
	public static UniformRandomProvider createMersenneTwister(Long seed) {
		return create(RandomSource.MT, seed);
	}
	
	public static UniformRandomProvider createMersenneTwister() {
		return create(RandomSource.MT, null);
	}

	public static UniformRandomProvider createKiss(Long seed) {
		return create(RandomSource.KISS, seed);
	}
	
	public static UniformRandomProvider createKiss() {
		return create(RandomSource.KISS, null);
	}

	public static UniformRandomProvider create(RandomSource source, Long seed) {
		return source.create(seed);
	}
}
