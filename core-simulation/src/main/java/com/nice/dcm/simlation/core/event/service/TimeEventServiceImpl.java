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
	public void offer(long currentTime, List<TimeEvent> events) {
		setCurrentTime(currentTime);
		events.forEach(this::offer);
	}
	
	@Override
	public Future<Long> start() {
        return executor.submit(this::run);
	}

	protected long run() {
		long eventCount = 0;
		while (true) {
			TimeEvent e;
			try {
				e = blockingQueue.poll(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
				if (e == null && blockingQueue.isEmpty()) {
					break;
				} else if (e != null) {
					eventCount++;
					executor.submit(() -> {
						listener.onEvent(e);
					});
				}
			} catch (InterruptedException ex) {
				logger.error("InterruptedException", ex);
				Thread.currentThread().interrupt();
			}
		}
		return eventCount;
	}

	/**
	 * @return the queue size
	 */
	public int size() {
		return blockingQueue.size();
	}
	
	protected boolean offer(TimeEvent e) {
		if(e.getTimestamp() == currentTime) {
			return blockingQueue.offer(e);
		} else {
			String msg = "Invalid TimeEvent. currentTime is " 
					+ currentTime + ", event time is " + e.getTimestamp() + ", event detail: " + e;
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}
	
	protected void setCurrentTime(long currentTime) {
		if (this.size() > 0) {
			throw new IllegalStateException("Queue is not empty. size is " + this.size());
		}
		
		if (this.currentTime < currentTime) {
			this.currentTime = currentTime;
			listener.setCurrentTime(currentTime);
		} else {
			throw new IllegalArgumentException("Invalid start time. currentTime is " + currentTime + ", previous current time " + this.currentTime);
		}
    }
}
