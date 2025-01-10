package com.nice.dcm.simulation.generation.output;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class VolumePairRangeImpl implements VolumePairRange {
	private final double accumulatePossibility;
	private final VolumePair upperBound;
	private final VolumePair lowerBound;
	
	public VolumePairRangeImpl(double accumulatePossibility, @NonNull VolumePair lowerBound, VolumePair upperBound) {
		super();
		if (accumulatePossibility < 0d || accumulatePossibility > 1d) {
			throw new IllegalArgumentException("probability must be in the range [0, 1].");
		}
		
		if (upperBound != null && lowerBound.getVolume() >= upperBound.getVolume()) {
			throw new IllegalArgumentException("upper bound must be greater than lower bound.");
		}
		
		this.accumulatePossibility = accumulatePossibility;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
}
