package com.nice.dcm.sample.generation;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.statistics.distribution.ContinuousDistribution.Sampler;
import org.apache.commons.statistics.distribution.NormalDistribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.sample.generation.input.CallVolumes;
import com.nice.dcm.sample.generation.input.IntervalCallVolume;
import com.nice.dcm.sample.generation.output.TimeSeries;
import com.nice.dcm.sample.generation.output.TimeSeriesImpl;

public class RandomGeneratorImpl implements TimeSeriesGenerator {
	private static final Logger logger = LoggerFactory.getLogger(RandomGeneratorImpl.class);
	
	private static final int DEFAULT_SD = 1;
	
	private double mean = -1;
	private double sd = DEFAULT_SD;
	
	@Override
	public TimeSeries generate(CallVolumes callVolumes, long startTime, long endTime, long intervalSize) {
		logger.debug("Generating sequence for call volumes: {}, start time: {}, end time: {}, interval size: {}", callVolumes, startTime, endTime, intervalSize);

		this.validation(callVolumes, startTime, endTime, intervalSize);
		int totalSize = getTotalSize(callVolumes);
		
		logger.debug("Total size: {}", totalSize);
		long[] sequence = new long[totalSize];
		
		double localMean = getMean(intervalSize);
		logger.debug("mean is {}.", localMean);
		NormalDistribution exp = NormalDistribution.of(localMean, this.getSd());
		UniformRandomProvider rng = RandomSource.KISS.create(123L);
		Sampler sampler = exp.createSampler(rng);
		
		int index = 0;
		for (IntervalCallVolume callVolume : callVolumes.getIntervals()) {
			long start = callVolume.getStartTime();

			long[] intervalSeq = generateNormalDistribution(getVolume(callVolume.getCallVolume()), intervalSize, sampler);
			for (int i = 0; i < intervalSeq.length; i++) {
				sequence[index++] = limitValue(start + intervalSeq[i], startTime, endTime);
			}
		}
		return new TimeSeriesImpl(sequence);
	}

	protected long limitValue(long arrival, long startTime, long endTime) {
		if (arrival < startTime) {
			return startTime;
		} else if (arrival > endTime) {
			return endTime;
		}
		return arrival;
	}

	protected long[] generateNormalDistribution(int callVolume, long intervalSize, Sampler sampler) {
		if (callVolume <= 0) {
			return new long[0];
		}
		double arravingRate = (double)intervalSize / callVolume;
		logger.debug("Arraving rate: {}/{} = {}", intervalSize, callVolume, arravingRate);
		long[] sequence =  new long[callVolume];
		for (int i = 0; i < callVolume; i++) {
			double arrival = Math.round(arravingRate * i + 0.5 * arravingRate);
			double sample = sampler.sample();
			sequence[i] = Math.round(arrival + sample);
		}
        return sequence;
	}

	public double getMean(long intervalSize) {
		if (this.mean < 0) {
			logger.debug("Mean is not set. Using call volume as mean");
			return 0.5 * intervalSize;
		}
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getSd() {
		return sd;
	}

	public void setSd(double sd) {
		this.sd = sd;
	}

	@Override
	public GeneratorType getType() {
		return GeneratorType.RANDOM;
	}
}
