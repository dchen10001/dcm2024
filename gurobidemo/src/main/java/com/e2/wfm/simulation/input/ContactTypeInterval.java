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
@EqualsAndHashCode
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class ContactTypeInterval {
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	
	private final double callVolume;
	private final Duration avgHandleTime;
	private final Duration avgAbandonedTime;
	
	@JsonCreator
	public ContactTypeInterval(
			@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime, 
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime, 
			@JsonProperty("callVolume") double callVolume,
			@JsonProperty("avgHandleTime") Duration avgHandleTime, 
			@JsonProperty("avgAbandonedTime") Duration avgAbandonedTime) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.callVolume = callVolume;
		this.avgHandleTime = avgHandleTime;
		this.avgAbandonedTime = avgAbandonedTime == null ? Duration.ZERO : avgAbandonedTime;
	}
	
	
}
