package com.nice.dcm.sample.generation.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class IntervalCallVolumeImpl implements IntervalCallVolume {
	private final long startTime;
	private final int endTime;
	private final double callVolume;
	
	@Override
	public long getDuration() {
		return endTime - startTime;
	}
}
