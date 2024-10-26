package com.nice.dcm.simlation.core.event.queue;

import java.util.PriorityQueue;

public class EventQueue<E> {
	
	private PriorityQueue<E> queue;
	
	public EventQueue() {
		queue = new PriorityQueue<>();
	}
	
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
	protected E peek() {
		return queue.peek();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	public void clear() {
		queue.clear();
	}
	
	public boolean remove(E event) {
		return queue.remove(event);
	}
	
	protected E find(Matcher<E> matcher, E event) {
		for (E e : queue) {
			if (matcher.match(e, event)) {
				return e;
			}
		}
		return null;
	}
}
