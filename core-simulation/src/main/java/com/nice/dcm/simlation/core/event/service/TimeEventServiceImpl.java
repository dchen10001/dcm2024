package com.nice.dcm.simlation.core.event.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simlation.core.event.TimeEvent;

public class TimeEventServiceImpl implements EventService<TimeEvent> {
	private static final Logger logger = LoggerFactory.getLogger(TimeEventServiceImpl.class);
	
	public static final long DEFAULT_TIMEOUT = 1000; // 10ms
	
	private long currentTime;
	
    private final BlockingQueue<TimeEvent> blockingQueue = new LinkedBlockingDeque<>();
    private final EventListener<TimeEvent> listener;

    private final ExecutorService executor;
    
	public TimeEventServiceImpl(EventListener<TimeEvent> listener, int maxSession) {
		this.listener = listener;
		this.executor = Executors.newFixedThreadPool(maxSession);
	}
	
	@Override
	public boolean offer(TimeEvent e) {
		if(e.getTimestamp() == currentTime) {
			return blockingQueue.offer(e);
		} else {
			String msg = "Invalid TimeEvent. currentTime is " 
					+ currentTime + ", event time is " + e.getTimestamp() + ", event detail: " + e;
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}

	@Override
	public void offer(List<TimeEvent> events) {
		for (TimeEvent e : events) {
			offer(e);
		}
	}
	
	@Override
	public Future<Long> start() {
        return executor.submit(this::run);
	}

	protected long run() {
		while (true) {
			try {
				TimeEvent e = blockingQueue.poll(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
				if (e == null && blockingQueue.isEmpty()) {
					break;
				} else if (e != null) {
					executor.submit(() -> {
						listener.onEvent(e);
					});
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	/**
	 * @return the queue size
	 */
	public int size() {
		return blockingQueue.size();
	}
	
	public void setCurrentTime(long currentTime) {
		if (this.currentTime < currentTime) {
			this.currentTime = currentTime;
			listener.setCurrentTime(currentTime);
		} else {
			throw new IllegalArgumentException("Invalid start time. currentTime is " + currentTime + ", previous current time " + this.currentTime);
		}
    }
}
