package com.e2.wfm.gurobidemo.dcm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.e2.wfm.gurobidemo.dcm.input.ContactType;
import com.e2.wfm.gurobidemo.dcm.input.DCMModelInput;
import com.e2.wfm.gurobidemo.dcm.input.Employee;
import com.e2.wfm.gurobidemo.dcm.input.EmployeeSchedule;
import com.e2.wfm.gurobidemo.dcm.input.SchedulingUnit;
import com.e2.wfm.gurobidemo.dcm.input.SkillGroup;
import com.e2.wfm.gurobidemo.dcm.output.DCMModelOutput;
import com.e2.wfm.gurobidemo.dcm.output.SkillGroupValue;

public class ModelInputConfigurable extends ModelInputConstant {
    private int numOfContactTypes;
    private int operationLowerBound;
    private int operationUpperBound;
    private double requriementLowerBound;
    private double requriementUpperBound;

    private int numOfSkillGroups;
    private int empNumPerSkillGroup;
    
    private WeeklyRule[] weeklyRules;

    private Random rn = new Random();
    
    public static ModelInputConfigurable createSmallSize() {
    	int numOfContactTypes = 2;
    	int operationLowerBound = 28; 
    	int operationUpperBound = 76;
    	
		double requriementLowerBound = 5;
		double requriementUpperBound = 20;
		int numOfSkillGroups = 3;
		int empNumPerSkillGroup = 20;
		WeeklyRule[] weeklyRules = {WeeklyRuleSinglePattern.weeklyRuleSinglePattern1, 
				WeeklyRuleSinglePattern.weeklyRuleSinglePattern2};
    	return new ModelInputConfigurable(numOfContactTypes, operationLowerBound, operationUpperBound,
    		 requriementLowerBound, requriementUpperBound, numOfSkillGroups, empNumPerSkillGroup,
    			weeklyRules);
    }
    		
    public static ModelInputConfigurable createMiddleSize() {
    	int numOfContactTypes = 10;
    	int operationLowerBound = 28; 
    	int operationUpperBound = 76;
    	
    	double requriementLowerBound = 5;
    	double requriementUpperBound = 20;
    	int numOfSkillGroups = 20;
    	int empNumPerSkillGroup = 50;
    	WeeklyRule[] weeklyRules = {WeeklyRuleSinglePattern.weeklyRuleSinglePattern1, 
    			WeeklyRuleSinglePattern.weeklyRuleSinglePattern2, WeeklyRuleSinglePattern.weeklyRuleSinglePattern3, WeeklyRuleSinglePattern.weeklyRuleSinglePattern4};
    	return new ModelInputConfigurable(numOfContactTypes, operationLowerBound, operationUpperBound,
    			requriementLowerBound, requriementUpperBound, numOfSkillGroups, empNumPerSkillGroup,
    			weeklyRules);
    }
    
    
    public static ModelInputConfigurable createLargeSize() {
    	int numOfContactTypes = 45;
    	int operationLowerBound = 28; 
    	int operationUpperBound = 76;
    	
    	double requriementLowerBound = 5;
    	double requriementUpperBound = 20;
    	int numOfSkillGroups = 60;
    	int empNumPerSkillGroup = 50;
    	WeeklyRule[] weeklyRules = {WeeklyRuleSinglePattern.weeklyRuleSinglePattern1, 
    			WeeklyRuleSinglePattern.weeklyRuleSinglePattern2, WeeklyRuleSinglePattern.weeklyRuleSinglePattern3, WeeklyRuleSinglePattern.weeklyRuleSinglePattern4};
    	return new ModelInputConfigurable(numOfContactTypes, operationLowerBound, operationUpperBound,
    			requriementLowerBound, requriementUpperBound, numOfSkillGroups, empNumPerSkillGroup,
    			weeklyRules);
    }
    
    public ModelInputConfigurable(int numOfContactTypes, int operationLowerBound, int operationUpperBound,
			double requriementLowerBound, double requriementUpperBound, int numOfSkillGroups, int empNumPerSkillGroup,
			WeeklyRule[] weeklyRules) {
		this.numOfContactTypes = numOfContactTypes;
		this.operationLowerBound = operationLowerBound;
		this.operationUpperBound = operationUpperBound;
		this.requriementLowerBound = requriementLowerBound;
		this.requriementUpperBound = requriementUpperBound;
		this.numOfSkillGroups = numOfSkillGroups;
		this.empNumPerSkillGroup = empNumPerSkillGroup;
		this.weeklyRules = weeklyRules;
	}

	@Override
	protected int getNumOfDays() {
		return 7;
	}

	@Override
	public DCMModelInput createModelInput(int maxIteration) {
		int interval = getInterval();
		List<ContactType> contactTypes = createContactTypes(numOfContactTypes, 
				 operationLowerBound,  operationUpperBound,
	             requriementLowerBound,  requriementUpperBound);
		List<SkillGroup> skillGroups = createSkillGroups(numOfSkillGroups, contactTypes);
		List<Employee> employees = createEmployees(empNumPerSkillGroup, skillGroups);
		List<SchedulingUnit> schedulingUnits = createSchedulingUnits(interval, employees);
		return new DCMModelInput(skillGroups, schedulingUnits, contactTypes, employees);
	}

	@Override
	public List<EmployeeSchedule> getBeseSchedules(DCMModelOutput modelOutput, List<Employee> employees) {
		SkillGroupValue[] skillGroupValues = modelOutput.getGroupValues();
		List<EmployeeSchedule> employeeSchedules = new ArrayList<>();
		for (Employee employee : employees) {
			long groupId = employee.getSkillGroupId();
			SkillGroupValue skillGroupValue = skillGroupValues[(int) groupId];
			double[] dualValues = skillGroupValue.getDualValues();
			int ruleId = employee.getWeeklyRuleId();
			int[] schedule = weeklyRules[ruleId].getHighestScore(dualValues);
			if (schedule.length > 0) {
				employeeSchedules.add(new EmployeeSchedule(employee.getId(), groupId, schedule));
			}
		}
		return employeeSchedules;
	}

	private List<SkillGroup> createSkillGroups(int numOfSkillGroups, List<ContactType> contactTypes) {
		List<SkillGroup> skillGroups = new ArrayList<>();
		int numOfContactType = contactTypes.size();
		for (int i = 0; i < numOfSkillGroups; i++) {
			int contactTypeIndex = rn.nextInt(numOfContactType);
			skillGroups.add(new SkillGroup(i, contactTypes.get(contactTypeIndex)));
		}
		
		for (int i = 0; i < numOfContactType; i++) {
			int skillGroupIndex = rn.nextInt(numOfSkillGroups);
			skillGroups.get(skillGroupIndex).addContactType(contactTypes.get(i));
		}
		return skillGroups;
	}
    
	private List<ContactType> createContactTypes(int numOfContactTypes, 
			int operationLowerBound, int operationUpperBound,
            double requriementLowerBound, double requriementUpperBound) {
		int interval = getInterval();
		List<ContactType> contactTypes = new ArrayList<>();
		for (int i = 0; i < numOfContactTypes; i++) {
			double[] requirements = new double[interval];
			for (int k = operationLowerBound; k < operationUpperBound; k++) {
				requirements[k] = getRandomValue(requriementLowerBound, requriementUpperBound);
			}
			contactTypes.add(new ContactType(i, requirements));
		}
		return contactTypes;
	}

	private List<Employee> createEmployees(int empNumPerSkillGroup, List<SkillGroup> skillGroups) {
		List<Employee> employees = new ArrayList<>();
		long empId = 0;
		for (SkillGroup skillGroup : skillGroups) {
			long groupId = skillGroup.getId();
			long schedulingUnitId = 0;
			for (int i = 0; i < empNumPerSkillGroup; i++) {
				int weeklyRuleId = rn.nextInt(weeklyRules.length);
				int[] schedule = weeklyRules[weeklyRuleId].getRandomSchedule();
				skillGroup.addEmployee(new Employee(empId, groupId, schedulingUnitId, schedule, weeklyRuleId));
				empId++;
			}
			employees.addAll(skillGroup.getEmployees());
		}
		return employees;
	}

	private List<SchedulingUnit> createSchedulingUnits(int interval, List<Employee> employees) {
		List<SchedulingUnit> schedulingUnits = new ArrayList<>();
		int[] minimumSeatLimits = new int[interval];
		int[] maximumSeatLimits = new int[interval];
		for (int i = 0; i < interval; i++) {
			minimumSeatLimits[i] = 0;
			maximumSeatLimits[i] = 0;
		}
		schedulingUnits.add(new SchedulingUnit(0, minimumSeatLimits, maximumSeatLimits, employees));
		return schedulingUnits;
	}	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	private double getRandomValue(double requriementLowerBound, double requriementUpperBound) {
		double value = rn.nextDouble(requriementUpperBound - requriementLowerBound + 1) + requriementLowerBound;
		return round(value, 2);
	}
}
