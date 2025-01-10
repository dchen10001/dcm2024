package com.nice.dcm.simulation.generation.output;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class VolumePairImpl implements VolumePair {
	private final int volume;
	private final double possibility;
	
	public VolumePairImpl(int volume, double possibility) {
		super();
		if (volume < 0) {
			throw new IllegalArgumentException("volume is less than 0.");
		}
		
		if (possibility < 0 || possibility > 1) {
			throw new IllegalArgumentException("possibility must be between 0 and 1.");
		}
		
		this.volume = volume;
		this.possibility = possibility;
	}
}
