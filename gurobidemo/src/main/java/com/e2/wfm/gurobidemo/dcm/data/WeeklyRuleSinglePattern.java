package com.e2.wfm.gurobidemo.dcm.data;

public class WeeklyRuleSinglePattern implements WeeklyRule {

	public static WeeklyRuleSinglePattern weeklyRuleSinglePattern1 = createWeeklyRuleSinglePattern(6, DailyRuleTwoBreakOneLunch.dailyRule1);
	public static WeeklyRuleSinglePattern weeklyRuleSinglePattern2 = createWeeklyRuleSinglePattern(6, DailyRuleTwoBreakOneLunch.dailyRule2);
	public static WeeklyRuleSinglePattern weeklyRuleSinglePattern3 = createWeeklyRuleSinglePattern(7, DailyRuleTwoBreakOneLunch.dailyRule1);
	public static WeeklyRuleSinglePattern weeklyRuleSinglePattern4 = createWeeklyRuleSinglePattern(7, DailyRuleTwoBreakOneLunch.dailyRule2);

	public static WeeklyRuleSinglePattern createWeeklyRuleSinglePattern(int numOfWorkingDays, DailyRule dailyRule) {
		DailyRule[] dailyRules = new DailyRule[WeeklyRule.numOfDays];
		for (int i = 0; i < numOfWorkingDays; i++) {
			dailyRules[i] = dailyRule;
		}
		return new WeeklyRuleSinglePattern(dailyRules);
	}
	
	private DailyRule[] dailyRules;
	
	public WeeklyRuleSinglePattern(DailyRule[] dailyRules) {
		this.dailyRules = dailyRules;
	}
	
	public int getIntervals() {
		return numOfDays * DailyRule.dailyInterval;
	}
	
	public int[] getRandomSchedule() {
        int[] result = new int[getIntervals()];
        int startIndex = 0;
        for(int i = 0; i < numOfDays; i++) {
        	if(this.dailyRules[i] != null) {
        		int[] schedule = this.dailyRules[i].getRandomSchedule();
        		for(int j = 0; j < schedule.length; j++) {
        			result[startIndex + j] = schedule[j];
        		}
        	}
        	startIndex += DailyRule.dailyInterval;
        }
        return result;
	}
	
	public int[] getHighestScore(double[] dualValues) {
		int[] result = new int[getIntervals()];
        int startIndex = 0;
        double[] dualValuesDaily = new double[DailyRule.dailyInterval];
        
        for(int i = 0; i < numOfDays; i++) {
			
        	if(this.dailyRules[i] != null) {
        		for (int j = 0; j < DailyRule.dailyInterval; j++) {
        			dualValuesDaily[j] = dualValues[startIndex + j];
        		}
        		
        		int[] schedule = this.dailyRules[i].getHighestScore(dualValuesDaily);

        		for(int j = 0; j < schedule.length; j++) {
        			result[startIndex + j] = schedule[j];
        		}
        	}
        	startIndex += DailyRule.dailyInterval;
        }
        return result;
	}
	
	
}
