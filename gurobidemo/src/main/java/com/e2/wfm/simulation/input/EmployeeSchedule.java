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
@EqualsAndHashCode
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class EmployeeSchedule {
	private final String employeeOid;
	
	@Builder.Default
    private final List<Shift> shifts = List.of();
	
	@Builder.Default
    private final List<Activity> activities = List.of();
    
    @JsonCreator
	public EmployeeSchedule(@NonNull @JsonProperty("employeeOid") String employeeOid, 
			@NonNull @JsonProperty("shifts") List<Shift> shifts, 
			@NonNull @JsonProperty("activities") List<Activity> activities) {
		super();
		this.employeeOid = employeeOid;
		this.shifts = shifts;
		this.activities = activities;
	} 
}
