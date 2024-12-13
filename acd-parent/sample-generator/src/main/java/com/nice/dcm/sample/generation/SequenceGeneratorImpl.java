package com.nice.dcm.sample.generation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.sample.generation.input.CallVolumes;
import com.nice.dcm.sample.generation.input.IntervalCallVolume;
import com.nice.dcm.sample.generation.output.TimeSeries;
import com.nice.dcm.sample.generation.output.TimeSeriesImpl;

/**
 * Generates a sequence of call arrivals based on the call volumes.
 * First divide the interval into equal slot based on the call volume.
 *    |-----|-----|-----|-----|-----|
 *    0     2     4     6     8     10
 * The contact arrives in the middle of each slot
 *    ---|-----|-----|-----|-----|---
 *       1     3     5     7     9
 * 
 */
public class SequenceGeneratorImpl implements TimeSeriesGenerator {
	private static final Logger logger = LoggerFactory.getLogger(SequenceGeneratorImpl.class);
	
	@Override
	public TimeSeries generate(CallVolumes callVolumes, long startTime, long endTime, long intervalSize) {
		logger.debug("Generating sequence for call volumes: {}, start time: {}, end time: {}, interval size: {}", callVolumes, startTime, endTime, intervalSize);
		this.validation(callVolumes, startTime, endTime, intervalSize);
		int totalSize = getTotalSize(callVolumes);
		
		logger.debug("Total size: {}", totalSize);
		long[] sequence = new long[totalSize];
		
		int index = 0;
		for (IntervalCallVolume callVolume : callVolumes.getIntervals()) {
			long start = callVolume.getStartTime();
			long[] intervalSeq = generateSequence(getVolume(callVolume.getCallVolume()), intervalSize);
			for (int i = 0; i < intervalSeq.length; i++) {
				sequence[index++] = start + intervalSeq[i];
			}
		}
		return new TimeSeriesImpl(sequence);
	}

	protected long[] generateSequence(int callVolume, long intervalSize) {
		if (callVolume <= 0) {
			return new long[0];
		}
		
		double arravingRate = (double)intervalSize / callVolume;
		
		logger.debug("Arraving rate: {}/{} = {}", intervalSize, callVolume, arravingRate);
		long[] sequence = new long[callVolume];
		for (int i = 0; i < callVolume; i++) {
			sequence[i] = Math.round(arravingRate * i + 0.5 * arravingRate);
		}
        return sequence;
	}

	@Override
	public GeneratorType getType() {
		return GeneratorType.SEQUENCE;
	}
}
