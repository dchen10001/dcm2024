package com.nice.dcm.sample.generation.output;

public interface TimeSeries {
	long[] getArrivals();
	int size();
	long getArrival(int index);
}
