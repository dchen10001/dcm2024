package com.e2.wfm.simulation.input;

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
public class ValidationSimulatorInput{
	private final String problemId;
	private final String version;
	private final SimulationParameter simulationParameter;
	private final List<Employee> employees;
	private final List<Skill> skills;
	private final List<ContactType> contactTypes;
	private final List<ActivityCode> activityCodes;
	private final List<EmployeeSchedule> employeeSchedules;
	
	@JsonCreator
	public ValidationSimulatorInput(
			@NonNull @JsonProperty("problemId") String problemId, 
			@NonNull @JsonProperty("version") String version,
			@NonNull @JsonProperty("simulationParameter") SimulationParameter simulationParameter,
			@NonNull @JsonProperty("employees") List<Employee> employees,
			@NonNull @JsonProperty("skills") List<Skill> skills,
			@NonNull @JsonProperty("contactTypes") List<ContactType> contactTypes,
			@NonNull @JsonProperty("activityCodes") List<ActivityCode> activityCodes,
			@NonNull @JsonProperty("employeeSchedules") List<EmployeeSchedule> employeeSchedules) {
		this.problemId = problemId;
		this.version = version;	
		this.simulationParameter = simulationParameter;	
		this.employees = employees;	
		this.skills = skills;	
		this.contactTypes = contactTypes;	
		this.activityCodes = activityCodes;	
		this.employeeSchedules = employeeSchedules;	
	}
}
