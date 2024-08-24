package com.e2.wfm.simulation.input;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder(builderClassName = "builder")
@EqualsAndHashCode
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class EmployeeSkill {
	private final String skillOid;
    private final int level;
	
    @JsonCreator
    public EmployeeSkill(@NonNull @JsonProperty("skillOid") String skillOid, 
    		@JsonProperty("level") int level) {
		super();
		this.skillOid = skillOid;
		this.level = level;
	}  
}
