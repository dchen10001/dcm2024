package com.e2.wfm.gurobidemo.dcm.data;

public interface WeeklyRule {
	public static final int numOfDays = 7;
	public int[] getRandomSchedule();
	public int[] getHighestScore(double[] dualValues);
}
