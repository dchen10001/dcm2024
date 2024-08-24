package com.e2.wfm.gurobidemo.dcm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DailyRuleTwoBreakOneLunch implements DailyRule {

	private int shiftLen = 40; //8 hours  
    private int lunchLen = 4; //one hour 
    private int breakLen = 1; // 15 minutes
    
	//start relative index 
	private int[] shiftStarts = {28,29,30,31,32};
	
	//relative start index from start index
    private int[] break1Starts = {6,7,8,9};
    private int[] lunchStarts = {16,17,18,19,20};
    private int[] break2Starts = {28,29,30,31};
    
    private int scheduleSize = 0;

    private Random rn = new Random();
    
    public static final DailyRuleTwoBreakOneLunch dailyRule1 = new DailyRuleTwoBreakOneLunch(40, 4, 1, 
    			new int[]{28,29,30,31,32}, 
    			new int[]{6,7,8,9}, 
    			new int[]{16,17,18,19,20}, 
    			new int[]{28,29,30,31});    	

    public static final DailyRuleTwoBreakOneLunch dailyRule2 =  new DailyRuleTwoBreakOneLunch(40, 4, 1, 
    			new int[]{32,33,34,35,36}, 
    			new int[]{6,7,8,9}, 
    			new int[]{16,17,18,19,20}, 
    			new int[]{28,29,30,31});    	
    
    public DailyRuleTwoBreakOneLunch() {
    	
	}

	public DailyRuleTwoBreakOneLunch(int shiftLen, int lunchLen, int breakLen, int[] shiftStarts, 
			int[] break1Starts, int[] lunchStarts, int[] break2Starts) {
		super();
		this.shiftLen = shiftLen;
		this.lunchLen = lunchLen;
		this.breakLen = breakLen;
		this.shiftStarts = shiftStarts;
		this.break1Starts = break1Starts;
		this.lunchStarts = lunchStarts;
		this.break2Starts = break2Starts;
	}
	
	
	public int numOfSchedules() {
		if(scheduleSize == 0) {
			try {
				long size = multiple(shiftStarts.length, break1Starts.length, lunchStarts.length, break2Starts.length);
				scheduleSize = (int)size;
			} catch (ArithmeticException e) {
				scheduleSize = -1;
            }
		}
		return scheduleSize;
	}

	public int[][] ruleSchedule() {
		int size =  numOfSchedules();
		if (size < 0) {
			//overflow
			return null;
		}
		
		int[][] schedules = new int[(int) size][dailyInterval];
		int idx = 0;
		for (int shiftStart : shiftStarts) {
			for (int break1Start : break1Starts) {
				for (int lunchStart : lunchStarts) {
					for (int break2Start : break2Starts) {
						schedules[idx++] = toSchedule(shiftStart, break1Start, break2Start, lunchStart);
					}
				}
			}
		}
		return schedules;
	}
	
	public int[] getHighestScore(double[] dualValues) {
        double maxScore = - Double.MAX_VALUE;
        List<int[]> bestSchedules = new ArrayList<>();
		for (int shiftStart : shiftStarts) {
			for (int break1Start : break1Starts) {
				for (int lunchStart : lunchStarts) {
					for (int break2Start : break2Starts) {
						int[] schedule = toSchedule(shiftStart, break1Start, break2Start, lunchStart);
						double s = score(dualValues, schedule);
						if (s > maxScore) {
							maxScore = s;
							bestSchedules.clear();
							bestSchedules.add(schedule);
						} else if (s == maxScore) {
							if(bestSchedules.size() < Integer.MAX_VALUE - 1) {
								bestSchedules.add(schedule);
							}
						}
					}
				}
			}
		}
		
		if(bestSchedules.isEmpty()) {
			return null;
		} else if (bestSchedules.size() == 1) {
			return bestSchedules.get(0);
		} else {
			int idx = rn.nextInt(bestSchedules.size());
			return bestSchedules.get(idx);
		}
	}
	
	public int[] getRandomSchedule() {
		return toSchedule(getRandomIndex(shiftStarts), getRandomIndex(break1Starts), getRandomIndex(break2Starts), getRandomIndex(lunchStarts));
	}
	
	private int getRandomIndex(int[] values) {
		int idx = rn.nextInt(values.length);
		return values[idx];
	}
	
	protected int[] toSchedule(int startIdx, int break1Start, int break2Start, int lunchStart) {
		int[] schedules = new int[dailyInterval];
		// set schedule
		int start = startIdx;
		int end = startIdx + shiftLen;
		setSlotValue(schedules, start, end, 1);

		// set break1
		start = startIdx + break1Start;
		end = start + breakLen;
		setSlotValue(schedules, start, end, -1);

		// set lunch
		start = startIdx + lunchStart;
		end = start + lunchLen;
		setSlotValue(schedules, start, end, -1);

		// set break2
		start = startIdx + break2Start;
		end = start + breakLen;
		setSlotValue(schedules, start, end, -1);

		return schedules;
	}
	
	private void setSlotValue(int[] schedules, int start, int end, int value) {
		for (int i = start; i < end; i++) {
			schedules[i] = value;
		}
	}
    	
	private int multiple(int... vs) throws ArithmeticException {
		if (vs.length == 0) {
			return 0;
		}
		
		long value = 1;
		for(int v : vs) {
			value = value * v;
			if (value > Integer.MAX_VALUE) {
				throw new ArithmeticException("Overflow!");
			}
		}
		return (int)value;
	}
	
	private double score(double[] dualValues, int[] schedule) {
		double score = 0;
		for (int i = 0; i < schedule.length; i++) {
			score += (double) (dualValues[i] * schedule[i]);
		}
		return score;
	}
    
	public static void main(String[] args) {
		int numOfDays = 7;
		int numOfInterval = 96;
		
        int[] result = new int[numOfDays * numOfInterval];
        int startIndex = 0;
        for(int i = 0; i < numOfDays; i++) {
        	int[] schedule = new int[numOfInterval];
        	for(int j = 0; j < numOfInterval; j++) {
        		schedule[j] = j* (i+1);
        	}
        	
    		for(int j = 0; j < schedule.length; j++) {
    			result[startIndex + j] = schedule[j];
    		}
        	startIndex += numOfInterval;
        }
        System.out.println(result);
	}	
}
