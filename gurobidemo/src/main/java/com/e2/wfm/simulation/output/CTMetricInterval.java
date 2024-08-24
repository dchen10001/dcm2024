package com.e2.wfm.simulation.output;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

public record CTMetricInterval (@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime, 
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime, 
			 @JsonProperty("numContacts") int numContacts, 
			 @JsonProperty("numAnswered") int numAnswered,
			@JsonProperty("serviceLevelPct") Double serviceLevelPct,
			@JsonProperty("averageSpeedOfAnswer") double averageSpeedOfAnswer) {
}
