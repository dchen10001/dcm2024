package com.nice.dcm.simlation.core.event.queue;

public interface EventQueue<E> {

	/**
	 * add event to queue
	 */
	public void add(E event);
	
	/**
	 * This method retrieves and removes the head of the queue.
	 */	
	public E poll();
	
	/**
	 * This method retrieves, but does not remove, the head of the queue.
	 */
	public E peek();
	
	public boolean isEmpty();

	
	/**
	 * return the size of queue
	 */
	public int size();
	
	/*
	 * clear queue
	 */
	public void clear();
	
	/*
	 * remove event from queue
	 */
	public boolean remove(E event);
	
	/*
	 * find event from the queue with matcher
	 */
	public E find(Matcher<E> matcher, E event);
}
