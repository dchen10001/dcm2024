package com.nice.dcm.simlation.core.event.service;

import java.util.List;
import java.util.concurrent.Future;

public interface EventService<E> {
    /**
     * Inserts the specified element into this queue of event service.
     * And waiting for being processed by listener.
     * 
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    void offer(long currentTime, List<E> events);
    
    /**
     * start the event service at the time. Any event must be in the same time.
     * @param currentTime
     * @return
     */
    public long start();
}
