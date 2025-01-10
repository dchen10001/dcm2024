package com.nice.dcm.simulation.generation;

import com.nice.dcm.simulation.generation.output.VolumePairRange;

public interface TimeSeriesGenerator {
	void doEqualSequences(int volume);
	
	VolumePairRange getVolumePairRange(double mean);
	
	VolumePairRange getVolumePairRange(double mean, double probability);
	
	default double getMean(double volume, long intervalInSeconds) {
		if(volume <= 0) {
			throw new IllegalArgumentException("volume must be great than 0.");			
		}
		
		if(intervalInSeconds < 1) {
			throw new IllegalArgumentException("interval must be great than 1.");
		}
		
		double arrivalRate = volume / intervalInSeconds; // call per seconds
		
		double mean = 1.0 / arrivalRate;
		if(mean == Double.POSITIVE_INFINITY) {
			throw new IllegalArgumentException("volume is too lower which causes mean is infinity.");	
		}
		return mean;
	}
}
