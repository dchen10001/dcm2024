package com.nice.dcm.simulation.core.queue.service;

public interface EventListener<E> {
	/**
	 * Process the event when the event is arrived.
	 * 
	 * @param element
	 */
	void onEvent(E element);
}
