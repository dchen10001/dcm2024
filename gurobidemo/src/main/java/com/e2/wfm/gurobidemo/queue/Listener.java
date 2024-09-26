package com.e2.wfm.gurobidemo.queue;

public interface Listener <E> {
    void setCurrentTime(long currentTime);
    void onEventAdded(E element);
    long getCurrentTime();
    long getNextTime();
}
