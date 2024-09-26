package com.e2.wfm.gurobidemo.thread;

public interface TimeEventService {
    long apply(long currentTime);
    String getName();
}
