package com.nice.dcm.simulation.core.schedule;

import java.util.Set;

public interface Activity {
	String getActivityOid();
	
	long getStartTime();
	
	long getEndTime();
	
	Set<String> getSkills();
}
