package com.e2.wfm.simulation.input;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class ServiceTarget {
	private final Double serviceLevelPercent;
	private final Duration serviceLevelDuration;
	private final Duration  asaDuration;
	
	public ServiceTarget(Double serviceLevelPercent, Duration serviceLevelDuration, Duration asaDuration) {
		this.serviceLevelPercent = serviceLevelPercent;
		this.serviceLevelDuration = serviceLevelDuration == null ? Duration.ZERO : serviceLevelDuration;
		this.asaDuration = asaDuration == null ? Duration.ZERO : asaDuration;
	}
}
