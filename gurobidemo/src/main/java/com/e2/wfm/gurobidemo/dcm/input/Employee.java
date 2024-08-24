package com.e2.wfm.gurobidemo.dcm.input;

public class Employee extends Entity implements ScheduleCandidate {
	private final long skillGroupId;
	private final long schedulingUnitId;

	private int weeklyRuleId;
	private int[] schedules;

	public Employee(long id, long skillGroupId, long schedulingUnitId, int[] schedules, int weeklyRuleId) {
		super(id);
		this.skillGroupId = skillGroupId;
		this.schedulingUnitId = schedulingUnitId;
		this.schedules = schedules;
		this.weeklyRuleId = weeklyRuleId;
	}

	public Employee(long id, long skillGroupId, long schedulingUnitId, int[] schedules) {
		super(id);
		this.skillGroupId = skillGroupId;
		this.schedulingUnitId = schedulingUnitId;
		this.schedules = schedules;
	}

	public long getSkillGroupId() {
		return skillGroupId;
	}

	public long getSchedulingUnitId() {
		return schedulingUnitId;
	}

	public int[] getSchedules() {
		return schedules;
	}

	public int getWeeklyRuleId() {
		return weeklyRuleId;
	}
}
