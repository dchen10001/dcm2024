package com.e2.wfm.simulation.input;

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
public class DistributionScriptByPeriod extends DistributionScript {
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	
	@JsonCreator
	public DistributionScriptByPeriod(@JsonProperty("name") String name, 
			@NonNull @JsonProperty("distributionScript") String script, 
			@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime,
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime) {
		super(name, script);
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
}
