package com.e2.wfm.gurobidemo.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class EventServiceImpl<E> implements EventService<E> {
    public static final long DEFAULT_TIMEOUT = 1000; // 10ms
    
    private BlockingQueue<E> blockingQueue = new LinkedBlockingDeque<>();
    private final Listener<E> listener;
    private boolean terminated = false;
    private final int size;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    
    public EventServiceImpl(Listener<E> listener, int size) {
        this.listener = listener;
        this.size = size;
    }
    
    @Override
    public Future<Long> start(long currentTime) {
        this.terminated = false;
        listener.setCurrentTime(currentTime);
        return executor.submit(this::run);
    }

    protected long run() {
        ExecutorService eventExecutor = Executors.newFixedThreadPool(size);
        while (true) {
            try {
                E e = blockingQueue.poll(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
                if (e == null && terminated) {
                    break;
                } else if (e != null) {
                    eventExecutor.submit(() -> {
                        listener.onEventAdded(e);
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eventExecutor.shutdown();
        
        try {
            eventExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listener.getNextTime();
    }
    
    @Override
    public void terminate() {
        terminated = true;
    }
    
    @Override
    public boolean offer(E e) {
        return blockingQueue.offer(e);
    }

}
