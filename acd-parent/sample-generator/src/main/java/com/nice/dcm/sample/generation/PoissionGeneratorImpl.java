package com.nice.dcm.sample.generation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.statistics.distribution.ContinuousDistribution.Sampler;
import org.apache.commons.statistics.distribution.ExponentialDistribution;

import com.nice.dcm.sample.generation.input.CallVolumes;
import com.nice.dcm.sample.generation.input.IntervalCallVolume;
import com.nice.dcm.sample.generation.output.TimeSeries;
import com.nice.dcm.sample.generation.output.TimeSeriesImpl;

public class PoissionGeneratorImpl implements TimeSeriesGenerator {
	public static final int DEFAULT_CAPACITY = 16;

	private Long seed;
	
	@Override
	public TimeSeries generate(CallVolumes callVolumes, long startTime, long endTime, long intervalSize) {
		this.validation(callVolumes, startTime, endTime, intervalSize);
		int totalSize = getTotalSize(callVolumes);
		long[] sequence = new long[totalSize];
		
		UniformRandomProvider uniformRandomProvider = getUniformRandomProvider(this.seed);
		int index = 0;
		for (IntervalCallVolume callVolume : callVolumes.getIntervals()) {
			long start = callVolume.getStartTime();
			long[] intervalSeq = generateNormalDistribution(callVolume.getCallVolume(), intervalSize, uniformRandomProvider);
			
			sequence = add(sequence, intervalSeq, start, index);
			index += intervalSeq.length;
		}
		
		long[] finalSequence = new long[index];
		System.arraycopy(sequence, 0, finalSequence, 0, index);
		return new TimeSeriesImpl(finalSequence);
	}

	
	protected long[] generateNormalDistribution(double callVolume, long intervalInSeconds, UniformRandomProvider uniformRandomProvider) {
		double arrivalRate = callVolume / intervalInSeconds; // call per seconds
		int size = (int) callVolume + DEFAULT_CAPACITY;
		ExponentialDistribution exponentialDistribution = ExponentialDistribution.of(1.0 / arrivalRate);
		
		Sampler sampler = exponentialDistribution.createSampler(uniformRandomProvider);
		
		double currentInSeconds = 0d;
		
		long[] arrivalSequence = new long[size];
		int i = 0;
        while (currentInSeconds < intervalInSeconds) {
        	double arriving = sampler.sample();
        	arriving = round(arriving, 1);
        	currentInSeconds += arriving;
			if (currentInSeconds >= intervalInSeconds) {
				break;
			}
			arrivalSequence[i++] = Math.round(currentInSeconds);
        }
        
        long[] sequence = new long[i];
        System.arraycopy(arrivalSequence, 0, sequence, 0, i);
		return sequence;
	}
	
	private UniformRandomProvider getUniformRandomProvider(Long seed) {
		if(seed != null) {
			UniformRandomProviderFactory.createKiss(seed);
		}
		return UniformRandomProviderFactory.createKiss();
	}
	
	protected long[] add(long[] sequence, long[] intervalSeq, long startTime, int startIndex) {
		if (sequence.length < startIndex + intervalSeq.length) {
			sequence = grow(sequence, intervalSeq.length);
		}
		for (int i = 0; i < intervalSeq.length; i++) {
			sequence[startIndex++] = startTime + intervalSeq[i];
		}
		return sequence;
	}
	
	protected long[] grow(long[] array) {
		return grow(array, DEFAULT_CAPACITY);
	}
	
	protected long[] grow(long[] array, int size) {
		long[] newArray = new long[array.length + size];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	@Override
	public GeneratorType getType() {
		return GeneratorType.POISSION;
	}

	public void setSeed(Long seed) {
		this.seed = seed;
	}
	
	@Override
	public int getTotalSize(CallVolumes callVolumes) {
		double size = 0;
		for (IntervalCallVolume interval : callVolumes.getIntervals()) {
			size += interval.getCallVolume();
		}
		return (int)size + DEFAULT_CAPACITY;
	}
}
