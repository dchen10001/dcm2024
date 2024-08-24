package com.e2.wfm.gurobidemo.dcm.util;

public class ClockWatch {
	private long start;
	private long end;
	
	public void start() {
		start = System.currentTimeMillis();
		end = 0;
	}
	
	public void stop(String msg) {
		end = System.currentTimeMillis();
		System.out.println(msg + " took ================================================= " + getDuration() + " ms");
	}
	
	public long getDuration() {
		return end - start;
	}
	
	public String getDurationString() {
		return String.valueOf(getDuration());
	}	
}
