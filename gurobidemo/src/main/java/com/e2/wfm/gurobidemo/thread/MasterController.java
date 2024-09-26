package com.e2.wfm.gurobidemo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.e2.wfm.gurobidemo.queue.EventService;
import com.e2.wfm.gurobidemo.queue.EventServiceImpl;
import com.e2.wfm.gurobidemo.queue.Listener;

public class MasterController {
    private static final long DEFAULT_NEXT_TIME = 1; // second
    private final ExecutorService executor;
    
    private EventService<String> eventService;
    
    private List<TimeEventService> timeEventServices = new ArrayList<>();
    
    private final long endTimeInSeconds;
    
    public MasterController(long endTimeInSeconds) {
        timeEventServices.add(new SimpleTimeEventServiceImpl("Contact Generator"));
        timeEventServices.add(new SimpleTimeEventServiceImpl("Agent Manager"));
        executor = Executors.newFixedThreadPool(timeEventServices.size() + 1);
        this.endTimeInSeconds = endTimeInSeconds;
        
        Listener<String> routinglistener = new Listener<String>() {
            private long currentTime = 0;
            
            @Override
            public void onEventAdded(String e) {
                System.out.println("Event added: " + e + " at " + currentTime);
            }

            @Override
            public void setCurrentTime(long currentTime) {
                this.currentTime = currentTime;
            }

            @Override
            public long getCurrentTime() {
                return currentTime;
            }

            @Override
            public long getNextTime() {
                return DEFAULT_NEXT_TIME;
            }
        };
        this.eventService = new EventServiceImpl<>(routinglistener, 2);
    }
    
    public void start() {
        long currentTime = 0;
        
        while (currentTime < endTimeInSeconds) {
            //System.out.println("currentTime: " + currentTime);
            Future<Long> acdFuture = this.eventService.start(currentTime);
            
            List<Future<Long>> futures = new ArrayList<>();
            for (TimeEventService timeEventService : timeEventServices) {
                futures.add(execute(timeEventService, currentTime));
            }

            long nextTime = getNextTime(futures);
            
            this.eventService.terminate();
            long actNextTime = getNextTime(acdFuture);
            if (nextTime > actNextTime) {
                nextTime = actNextTime;
            }
            
            currentTime = currentTime + nextTime;
        }
    }
    
    private long getNextTime(Future<Long> future) {
        try {
            return future.get();
        } catch (Exception e) {
            return DEFAULT_NEXT_TIME;
        }
    }
    
    private long getNextTime(List<Future<Long>> futures) {
        long nextTime = endTimeInSeconds;
        for (Future<Long> future : futures) {
            long result = getNextTime(future);
            if (nextTime > result) {
                nextTime = result;
            }
        }
        return nextTime;
    }
    
    private Future<Long> execute(TimeEventService timeEventService, long currentTime) {
        return executor.submit(() -> {
            return timeEventService.apply(currentTime);
        });
    }
    
}
