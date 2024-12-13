package com.nice.dcm.simulation.core.queue.service;

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
	int offer(List<E> events);

	/**
	 * Inserts the specified element into this queue of event service.
	 * @param event
	 * @return
	 */
	boolean offer(E event);
    
	/**
	 * Set the event listener to process the event.
	 * 
	 * @param listener
	 */
    void setListener(EventListener<E> listener);

	/**
	 * Set the maximum number of session that can be processed in parallel.
	 * 
	 * @param maxSession
	 */
    void setMaxSession(int maxSession);
    
    /**
     * Set the timeout for the event service.
     * @param timeout
     */
    void setTimeout(long timeout);
    
	/**
	 * Get the size of the event service.
	 */
    int size();

    /**
     * start the event service at the time. Any event must be in the same time.
     * @param currentTime
     * @return
     */
    Future<Long> start();
}
