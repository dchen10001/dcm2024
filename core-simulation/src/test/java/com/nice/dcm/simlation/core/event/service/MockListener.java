package com.nice.dcm.simlation.core.event.service;

import com.nice.dcm.simlation.core.event.TimeEvent;

public class MockListener implements EventListener<TimeEvent> {
	private long currentTime;
	
	@Override
	public void onEvent(TimeEvent element) {
		System.out.println("currentTime: " + currentTime + "Event: " + element);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

}
