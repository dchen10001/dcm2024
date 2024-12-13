package com.nice.dcm.simulation.core.queue;

public interface SortedQueue<E> {

	/**
	 * add element to queue
	 */
	void add(E element);
	
	/**
	 * This method retrieves and removes the head element of the queue.
	 */	
	public E poll();
	
	/**
	 * This method retrieves, but does not remove, the head element of the queue.
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
	 * remove element from queue
	 */
	public boolean remove(E element);
	
	
	/*
	 * find element from the queue with matcher
	 */
	public E find(Matcher<E> matcher, E event);
}
