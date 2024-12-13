package com.nice.dcm.sample.generation.input;

import java.util.List;

public interface CallVolumes {
	List<IntervalCallVolume> getIntervals();
	void validate();
	int size();
}
