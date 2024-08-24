package com.e2.wfm.gurobidemo.dcm.data;

public interface DailyRule {
	public static final int dailyInterval = 96;
	public int[] getHighestScore(double[] dualValues);
	public int[] getRandomSchedule();
}
