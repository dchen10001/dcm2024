package com.e2.wfm.gurobidemo.thread;

public class SimpleTimeEventServiceImpl implements TimeEventService {

    private final String name;
    
    public SimpleTimeEventServiceImpl(String name) {
        this.name = name;
    }
    
    @Override
    public long apply(long currentTime) {
        System.out.println(name + " at time: " + currentTime);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return 5;
    }

    @Override
    public String getName() {
        return name;
    }

}
