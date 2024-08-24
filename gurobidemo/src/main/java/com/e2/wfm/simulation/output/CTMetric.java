package com.e2.wfm.simulation.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

public record CTMetric(@NonNull  @JsonProperty("contactTypeOid") String contactTypeOid, 
			@NonNull  @JsonProperty("intervals") List<CTMetricInterval> intervals) {
	
}
