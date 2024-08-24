package com.e2.wfm.simulation.input;

import java.time.Duration;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class ServiceTargetsByPeriod extends ServiceTarget {
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	
	@JsonCreator
	public ServiceTargetsByPeriod(
			@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime, 
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime,
			@JsonProperty("serviceLevelPercent") Double serviceLevelPercent, 
			@JsonProperty("serviceLevelDuration") Duration serviceLevelDuration, 
			@JsonProperty("asaDuration") Duration asaDuration) {
		super(serviceLevelPercent, serviceLevelDuration, asaDuration);
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
}
