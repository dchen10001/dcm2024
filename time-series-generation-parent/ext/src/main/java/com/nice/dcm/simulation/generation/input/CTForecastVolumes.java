package com.nice.dcm.simulation.generation.input;

import java.util.List;

public interface CTForecastVolumes {
	String getContactTypeOid();
	int getId();
	List<IntervalForecastVolume> getForecastVolumes();
}
