package com.nice.dcm.sample.generation;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

public class UniformRandomProviderFactory {
	
	private UniformRandomProviderFactory() {
	
	}
	
	public static UniformRandomProvider createMersenneTwister(long seed) {
		return create(RandomSource.MT, seed);
	}
	
	public static UniformRandomProvider createMersenneTwister() {
		return create(RandomSource.MT);
	}

	public static UniformRandomProvider createKiss(long seed) {
		return create(RandomSource.KISS, seed);
	}
	
	public static UniformRandomProvider createKiss() {
		return create(RandomSource.KISS);
	}

	public static UniformRandomProvider create(RandomSource source) {
		return source.create();
	}

	public static UniformRandomProvider create(RandomSource source, long seed) {
		return source.create(seed);
	}
}
