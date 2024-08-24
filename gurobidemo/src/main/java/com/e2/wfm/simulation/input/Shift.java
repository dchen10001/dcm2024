package com.e2.wfm.simulation.input;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Builder(builderClassName = "builder")
@EqualsAndHashCode
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class Shift {
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	
	@Builder.Default    
	private final List<Activity> activities = List.of();
	
	@JsonCreator
	public Shift(@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime, 
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime, 
			@NonNull @JsonProperty("activities") List<Activity> activities) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.activities = activities;
	}
}
