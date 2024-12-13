package com.nice.dcm.simulation.core.schedule;

import java.util.Set;

import com.nice.dcm.simulation.core.exception.RangeOverlapException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ActivityImpl implements Activity, Comparable<Activity> {
	private final String activityOid;
	private final long startTime;
	private final long endTime;
	private final Set<String> skills;
	
	@Override
	public int compareTo(Activity o){
		if(this.isOverlap(o)) {
			throw new RangeOverlapException("Two Activities are overlap each other.", 
					this.getStartTime(), this.getEndTime(),
					o.getStartTime(), o.getEndTime());
		}
		long ret = this.getStartTime() - o.getStartTime();
		if (ret == 0) {
			return 0;
		}
		return ret > 0 ? 1 : -1;
	}
	
	private boolean isOverlap(Activity o) {
		return (this.getStartTime() > o.getEndTime())
				|| (o.getStartTime() > this.getEndTime());
	}
}
