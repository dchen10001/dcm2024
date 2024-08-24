package com.e2.wfm.simulation.input;

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
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class Employee extends IdName {
	
	@Builder.Default
	private final List<EmployeeSkill> skills = List.of();;

	@JsonCreator
	public Employee(@NonNull @JsonProperty("oid") String oid, 
			@JsonProperty("id") Long id, 
			@JsonProperty("name") String name, 
			@JsonProperty("skills") List<EmployeeSkill> skills) {
		super(oid, id, name);
		this.skills = skills == null ? List.of() : skills;
	}
	
}
