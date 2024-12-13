package com.nice.dcm.sample.generation;

import com.nice.dcm.sample.generation.input.CallVolumes;
import com.nice.dcm.sample.generation.input.IntervalCallVolume;
import com.nice.dcm.sample.generation.output.TimeSeries;

public interface TimeSeriesGenerator {
	
	GeneratorType getType();
	
	TimeSeries generate(CallVolumes callVolumes, long startTime, long endTime, long intervalSize);
	
	default int getVolume(double callVolume) {
		return (int)callVolume;
	}
	
	default int getTotalSize(CallVolumes callVolumes) {
		int size = 0;
		for (IntervalCallVolume interval : callVolumes.getIntervals()) {
			size += (int)interval.getCallVolume();
		}
		return size;
	}
	
	default void validation(CallVolumes callVolumes, long startTime, long endTime, long intervalSize) {
		if (callVolumes == null) {
			throw new IllegalArgumentException("Call volumes cannot be null");
		}
		if (startTime < 0) {
			throw new IllegalArgumentException("Start time cannot be negative");
		}
		if (endTime < 0) {
			throw new IllegalArgumentException("End time cannot be negative");
		}
		if (intervalSize <= 0) {
			throw new IllegalArgumentException("Interval size must be positive");
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException("End time must be greater than or equal to start time");
		}
		
		if ((endTime - startTime) % intervalSize != 0) {
			throw new IllegalArgumentException("Difference between end time and start time must be divisible by interval size");
		}
		
		callVolumes.validate();
		
		long totalLength = endTime - startTime;
		for (int i = 0; i < callVolumes.size(); i++) {
			if (callVolumes.getIntervals().get(i).getStartTime() < 0) {
				throw new IllegalArgumentException("Call volume start time cannot be greater than interval start time");
			}
			if (callVolumes.getIntervals().get(i).getEndTime() > totalLength) {
				throw new IllegalArgumentException("Call volume end time cannot be less than interval end time");
			}
			
			if (callVolumes.getIntervals().get(i).getDuration() != intervalSize) {
                throw new IllegalArgumentException("Interval duration must be equal to interval size");
            }
		}
	}
}
