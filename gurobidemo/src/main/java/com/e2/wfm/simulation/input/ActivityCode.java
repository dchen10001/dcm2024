package com.e2.wfm.simulation.input;

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
public class ActivityCode extends IdName {
	private final boolean open;

	@JsonCreator
	public ActivityCode(@NonNull @JsonProperty("oid") String oid, 
			@JsonProperty("id") Long id, 
			@NonNull @JsonProperty("name") String name, 
			@JsonProperty("open") boolean open) {
		super(oid, id, name);
		this.open = open;
	}
	
}
