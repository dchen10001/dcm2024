package com.nice.dcm.simlation.core.event.queue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class EventBlockingQueueImpl<E> implements EventQueue<E> {
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	private PriorityBlockingQueue<E> queue;
	
	/*
	 * E must implement Comparable interface
	 */
	public EventBlockingQueueImpl() {
		queue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY);
	}
	
	/*
	 * provide Comparator to sort the Priority Queue
	 */
	public EventBlockingQueueImpl(Comparator<? super E> comparator) {
		queue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY, comparator);
	}
	
	/*
	 * add event to queue
	 */
	public void add(E event) {
		queue.add(event);
	}
	
	/**
	 * This method retrieves and removes the head of the queue.
	 */	
	public E poll() {
		return queue.poll();
	}
	
	/**
	 * This method retrieves, but does not remove, the head of the queue.
	 */
	public E peek() {
		return queue.peek();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	/*
	 * return the size of queue
	 */
	public int size() {
		return queue.size();
	}
	
	/*
	 * clear queue
	 */
	public void clear() {
		queue.clear();
	}
	
	/*
	 * remove event from queue
	 */
	public boolean remove(E event) {
		return queue.remove(event);
	}
	
	/*
	 * find event from the queue with matcher
	 */
	public E find(Matcher<E> matcher, E event) {
		for (E e : queue) {
			if (matcher.match(e, event)) {
				return e;
			}
		}
		return null;
	}
}
