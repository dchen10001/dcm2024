package com.e2.wfm.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.e2.wfm.simulation.input.Activity;
import com.e2.wfm.simulation.input.ActivityCode;
import com.e2.wfm.simulation.input.ContactType;
import com.e2.wfm.simulation.input.ContactTypeInterval;
import com.e2.wfm.simulation.input.DistributionScript;
import com.e2.wfm.simulation.input.DistributionScriptByPeriod;
import com.e2.wfm.simulation.input.Employee;
import com.e2.wfm.simulation.input.EmployeeSchedule;
import com.e2.wfm.simulation.input.EmployeeSkill;
import com.e2.wfm.simulation.input.ServiceTargetsByPeriod;
import com.e2.wfm.simulation.input.Shift;
import com.e2.wfm.simulation.input.SimulationParameter;
import com.e2.wfm.simulation.input.Skill;
import com.e2.wfm.simulation.input.SkillLevel;
import com.e2.wfm.simulation.input.ValidationSimulatorInput;
import com.e2.wfm.simulation.output.CTMetric;
import com.e2.wfm.simulation.output.CTMetricInterval;
import com.e2.wfm.simulation.output.SimulationResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class SimulationAPITestHelp {
	OffsetDateTime start = OffsetDateTime.parse("2022-02-07T00:00:00Z");
	OffsetDateTime end = start.plusHours(1);
	
	@Test
	void inputTest() {
		ObjectMapper mapper = createObjectMap();
		try {
			ValidationSimulatorInput validationSimulatorInput = getInputStream("simulatorInput.json", ValidationSimulatorInput.class, mapper);
			System.out.println(validationSimulatorInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void simulationOutJsonTest() {
		ObjectMapper mapper = createObjectMap();
		SimulationResult simulationResult = new SimulationResult(UUID.randomUUID().toString(), "1.0",
				SimulationParameter.builder().intervalLength(Duration.ofMinutes(900)).startDateTime(start)
						.endDateTime(end).skillLevel(SkillLevel.ONE_IS_HIGHEST_LEVEL).build(),
				toCTMetric());
		
		try {
			String json = mapper.writeValueAsString(simulationResult);
			System.out.println(json);
			SimulationResult clone = mapper.readValue(json.getBytes(), SimulationResult.class);
			assertEquals(simulationResult, clone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	List<CTMetric> toCTMetric() {
		return List.of(new CTMetric("contactTypeOid", toCTMetricInterval()));
	}
	
	List<CTMetricInterval> toCTMetricInterval() {
		return List.of(new CTMetricInterval(start, end, 11, 5, null, 11));
	}
	
	@Test
	void ValidationSimulatorInputJsonTest() {
		ObjectMapper mapper = createObjectMap();
		
		ContactType ct = toContactType().get(0);
		try {
			String json = mapper.writeValueAsString(ct);
			ContactType ct1 = mapper.readValue(json.getBytes(), ContactType.class);
			assertEquals(ct, ct1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ValidationSimulatorInput validationSimulatorInput = ValidationSimulatorInput.builder().problemId(UUID.randomUUID().toString())
				.version("1.0")
				.simulationParameter(SimulationParameter.builder().intervalLength(Duration.ofMinutes(900)).startDateTime(start).endDateTime(end).skillLevel(SkillLevel.ONE_IS_HIGHEST_LEVEL).build())
				.employees(toEmployee())
				.skills(toSkill())
				.contactTypes(toContactType())
				.activityCodes(toActivityCode())
				.employeeSchedules(toEmployeeSchedule())
				.build();
		
		try {
			String json = mapper.writeValueAsString(validationSimulatorInput);
			System.out.println(json);
			ValidationSimulatorInput validationSimulatorInputClone = mapper.readValue(json.getBytes(), ValidationSimulatorInput.class);
			assertEquals(validationSimulatorInput, validationSimulatorInputClone);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	private List<EmployeeSchedule> toEmployeeSchedule() {
		return List
				.of(EmployeeSchedule.builder().employeeOid("employeeOid")
						.shifts(toShift()).activities(toActivity()).build());
	}
	
	private List<Shift> toShift() {
		return List.of(Shift.builder().startDateTime(start).endDateTime(end).activities(toActivity()).build());
	}
	
	private List<Activity> toActivity() {
		return List.of(
				Activity.builder().activityCodeOid("activityCodeOid").startDateTime(start).endDateTime(end).build());
	}
	
	private List<Employee> toEmployee() {
		return List.of(Employee.builder().oid("oid").id(1L).name("employeeName")
				.skills(List.of(EmployeeSkill.builder().skillOid("skillOid").level(10).build()))
				.build());
	}
	
	private List<ActivityCode> toActivityCode() {
		return List.of(ActivityCode.builder().oid("oid").id(1L).name("activityCodeName").build());
	}
	
	private List<Skill> toSkill() {
		return List.of(Skill.builder().oid("oid").id(1L).name("skillName").build());
	}
	
	private List<ContactType> toContactType() {
		ContactType contactType = ContactType.builder()
				.oid("oid").id(1L).name("contactName")
				.intervals(toContactTypeInterval())
				.defaultDistributionScript(DistributionScript.builder()
						.name("defaultDistributionName")
						.script("queue to @S:0000-0000-0000-0001 with priority 1").build())
				.distributionScriptsByPeriods(List.of(DistributionScriptByPeriod.builder().name("ruleName")
						.script("queue to @S:1111-0000-0000-0001 with priority 1")
						.startDateTime(start).endDateTime(end).build()))
				.defaultServiceTarget(ServiceTargetsByPeriod.builder()
						.startDateTime(start)
						.endDateTime(end)
						.serviceLevelDuration(Duration.ofHours(8))
						.asaDuration(Duration.ofHours(4))
						.serviceLevelPercent(0.85d)
						.build())
				.serviceTargetsByPeriods(toServiceTargetsByPeriod())
				.build();
		return List.of(contactType);
	}
	
	private List<ServiceTargetsByPeriod> toServiceTargetsByPeriod() {
		return List.of(ServiceTargetsByPeriod.builder().startDateTime(start).endDateTime(end)
				.serviceLevelDuration(Duration.ofHours(8)).asaDuration(Duration.ofHours(4)).serviceLevelPercent(0.85d)
				.build());
	}
	
	private List<ContactTypeInterval> toContactTypeInterval() {
		return List.of(ContactTypeInterval.builder().startDateTime(start).endDateTime(end)
				.callVolume(100).avgHandleTime(Duration.ofMinutes(20)).avgAbandonedTime(Duration.ofMinutes(10)).build());
	}
	
	public static ObjectMapper createObjectMap() {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    	objectMapper.registerModule(new JavaTimeModule());
    	objectMapper.registerModule(new Jdk8Module());
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	return objectMapper;
    }	
	
	public static <T> T getInputStream(String filePath, Class<T> cls, 
			ObjectMapper mapper) throws IOException {
		InputStream input = cls.getClassLoader().getResourceAsStream(filePath);
		return mapper.readerFor(cls).readValue(input);
	}
}
