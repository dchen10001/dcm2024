package com.e2.wfm.simulation.input;

import java.time.OffsetDateTime;
import java.util.List;

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
public class Activity {
	private final String activityCodeOid;
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	
	@JsonCreator
	public Activity(@NonNull @JsonProperty("activityCodeOid") String activityCodeOid, 
			@NonNull @JsonProperty("startDateTime") OffsetDateTime startDateTime, 
			@NonNull @JsonProperty("endDateTime") OffsetDateTime endDateTime) {
		super();
		this.activityCodeOid = activityCodeOid;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
	
}
