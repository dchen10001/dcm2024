package com.nice.dcm.sample.generation.output;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeSeriesImpl implements TimeSeries {
	private final long[] arrivals;

	@Override
	public int size() {
		return arrivals.length;
	}

	@Override
	public long getArrival(int index) {
		if(index >= 0 && index < arrivals.length)
            return arrivals[index];
        else
            throw new IllegalArgumentException("Index out of bounds. index must between "
            		+ 0 + " and " + (arrivals.length - 1) + " but was " + index);
	}
}
