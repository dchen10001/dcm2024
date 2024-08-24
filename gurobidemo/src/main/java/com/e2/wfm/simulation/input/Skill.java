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
public class Skill extends IdName {
	
	@JsonCreator
	public Skill(@NonNull @JsonProperty("oid") String oid, 
			@NonNull @JsonProperty("id") Long id, 
			@NonNull @JsonProperty("name") String name) {
		super(oid, id, name);
	}
}
