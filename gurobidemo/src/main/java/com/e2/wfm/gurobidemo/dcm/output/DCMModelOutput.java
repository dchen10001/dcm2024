package com.e2.wfm.gurobidemo.dcm.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.e2.wfm.gurobidemo.dcm.input.ScheduleCandidate;

public class DCMModelOutput {
	private final double objectiveValue;

	private final SkillGroupValue[] groupValues;
	private final SchedulingUnitValue[] schedulingUnitValues;
	private final EmployeeValue[] employeeValues;
	private final ContactTypeValue[] contactTypeValues;

	private Map<Long, int[]> coverages;

	public DCMModelOutput(double objectiveValue, SkillGroupValue[] groupValues,
			SchedulingUnitValue[] schedulingUnitValues, EmployeeValue[] employeeValues,
			ContactTypeValue[] contactTypeValues) {
		super();
		this.objectiveValue = objectiveValue;
		this.groupValues = groupValues;
		this.schedulingUnitValues = schedulingUnitValues;
		this.employeeValues = employeeValues;
		this.contactTypeValues = contactTypeValues;
	}

	public double getObjectiveValue() {
		return objectiveValue;
	}

	public SkillGroupValue[] getGroupValues() {
		return groupValues;
	}

	public SchedulingUnitValue[] getSchedulingUnitValues() {
		return schedulingUnitValues;
	}

	public EmployeeValue[] getEmployeeValues() {
		return employeeValues;
	}

	public void writeEmployeeValues() {
		for (EmployeeValue employeeValue : employeeValues) {
			System.out.println("Employee Id: " + employeeValue.getId());
			Map<Integer, Double> scheduleValues = employeeValue.getScheduleValues();
			List<Integer> idList = new ArrayList<>(scheduleValues.keySet());
			Collections.sort(idList);
			for (Integer key : idList) {
				Double value = scheduleValues.get(key);
				if(value != null && value.doubleValue() > 0) {
					System.out.print("{" + key + " : " + scheduleValues.get(key) + "}, ");
				}
			}
			System.out.println();
		}
	}

	public ContactTypeValue[] getContactTypeValues() {
		return contactTypeValues;
	}

	public Map<Long, int[]> getCoverages() {
		return coverages;
	}

	public void setCoverages(Map<Long, int[]> coverages) {
		this.coverages = coverages;
	}

	public static <T extends ScheduleCandidate> Map<Long, int[]> getCoverages(List<T> employees, int interval) {
		Map<Long, int[]> coverageByGroup = new HashMap<>();
		for (ScheduleCandidate employee : employees) {
			long skillGroupId = employee.getSkillGroupId();
			int[] coverages = coverageByGroup.computeIfAbsent(skillGroupId, k -> new int[interval]);
			int[] schedules = employee.getSchedules();
			for (int i = 0; i < interval; i++) {
				if (schedules[i] > 0) {
					coverages[i] += schedules[i];
				}
			}
		}
		return coverageByGroup;
	}
}
