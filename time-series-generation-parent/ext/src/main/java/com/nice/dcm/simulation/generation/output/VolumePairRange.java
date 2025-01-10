package com.nice.dcm.simulation.generation.output;

public interface VolumePairRange {
	double getAccumulatePossibility();
	VolumePair getUpperBound();
	VolumePair getLowerBound();
}
