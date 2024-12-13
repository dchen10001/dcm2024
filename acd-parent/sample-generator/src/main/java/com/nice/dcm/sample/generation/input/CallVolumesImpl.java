package com.nice.dcm.sample.generation.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class CallVolumesImpl implements CallVolumes {
	private final List<IntervalCallVolume> intervals;

	public CallVolumesImpl(List<IntervalCallVolume> intervalCallVolumes) {
		this.intervals = sort(intervalCallVolumes);
	}
	
	private List<IntervalCallVolume> sort(List<IntervalCallVolume> intervalCallVolumes) {
		if (intervalCallVolumes == null || intervalCallVolumes.isEmpty()) {
			return List.of();
		}
		
		List<IntervalCallVolume> list = new ArrayList<>(intervalCallVolumes);
		list.sort((a, b) -> Long.compare(a.getStartTime(), b.getStartTime()));
		
		return Collections.unmodifiableList(list);
	}
	
	@Override
	public void validate() {
        for (int i = 0; i < intervals.size() - 1; i++) {        	
        	if (intervals.get(i).getEndTime() != intervals.get(i + 1).getStartTime()) {
        		throw new IllegalArgumentException("IntervalCallVolumes is not continuous at " + intervals.get(i).getEndTime() + " and " + intervals.get(i + 1).getStartTime());
        	}
        }
	}
	
	@Override
	public int size() {
		return intervals.size();
	}
}
