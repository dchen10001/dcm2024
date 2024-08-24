package com.e2.wfm.simulation.input;

import java.time.Duration;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
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
public class SimulationParameter {
	private final Duration intervalLength;
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	private final SkillLevel skillLevel;

	public SimulationParameter(@JsonProperty("intervalLength") Duration intervalLength, 
			@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime, 
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime, 
			@JsonProperty("skillLevel") SkillLevel skillLevel) {
        this.intervalLength = intervalLength;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.skillLevel = skillLevel == null ? SkillLevel.ONE_IS_HIGHEST_LEVEL : skillLevel;
    }
}
