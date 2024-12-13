package com.nice.dcm.simulation.core.queue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simulation.core.event.TimeEvent;

public class TimeEventServiceImpl extends EventServiceImpl<TimeEvent<?>> {
	private static final Logger logger = LoggerFactory.getLogger(TimeEventServiceImpl.class);
	
	private long currentTime;
	
	public TimeEventServiceImpl() {
		super();
	}
	
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	
	@Override
	public boolean offer(TimeEvent<?> e) {
		if (e.getEventSeconds() == currentTime) {
			return super.offer(e);
        }
		String msg = "Invalid TimeEvent. currentTime is " 
				+ currentTime + ", event time is " + e.getEventSeconds() + ", event detail: " + e;
		logger.error(msg);
		throw new IllegalArgumentException(msg);
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}	
	
	@Override
	protected EventListener<TimeEvent<?>> getListener() {
		if (super.getListener() == null) {
			super.setListener(new TimeEventListenerImpl());
		}
		return super.getListener();
	}
}
