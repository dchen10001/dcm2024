package com.nice.dcm.simulation.core.queue.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

public abstract class EventServiceImpl<E> implements EventService<E> {
	public static final long DEFAULT_TIMEOUT = 1000; // 10ms
	public static final int DEFAULT_MAX_SESSION = 5;

	private long timeout = DEFAULT_TIMEOUT;
	
	private int maxSession = DEFAULT_MAX_SESSION;
	
	private BlockingQueue<E> blockingQueue;
	
	protected EventListener<E> listener;

    private ExecutorService executor;
    
    private boolean initialized = false;

	protected abstract Logger getLogger();

	public void init() {
		if(this.initialized) {
			return;
		}

		getLogger().info("Init EventServiceImpl. maxSession: {}, timeout: {}", this.maxSession, this.timeout);
		this.initialized = true;
		this.blockingQueue = new LinkedBlockingDeque<>();
		this.executor = Executors.newFixedThreadPool(maxSession);
	}
	
	@Override
	public int offer(List<E> events) {
		int count = 0;
		for (E e : events) {
			if (offer(e)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean offer(E e) {
		return blockingQueue.offer(e);
	}
	
	@Override
	public Future<Long> start() {
		getLogger().info("Start Listener Server.");
		return executor.submit(this::run);
	}
	
	protected long run() {
		init();
		long eventCount = 0;
		while (true) {
			E e;
			try {
				e = blockingQueue.poll(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
				if (e == null && blockingQueue.isEmpty()) {
					break;
				} else if (e != null) {
					eventCount++;
					executor.submit(() -> {
						getListener().onEvent(e);
					});
				}
			} catch (InterruptedException ex) {
				getLogger().error("InterruptedException", ex);
				Thread.currentThread().interrupt();
			}
		}
		return eventCount;
	}
	
	@Override
	public void setListener(EventListener<E> listener) {
		this.listener = listener;
	}

	@Override
	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	@Override
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	@Override
	public int size() {
		return blockingQueue.size();
	}
	
	protected EventListener<E> getListener() {
		return listener;
	}	
}
