package com.nice.dcm.sample.generation.input;

public interface IntervalCallVolume {
	long getStartTime();
	int getEndTime();
	double getCallVolume();
	long getDuration();
}
