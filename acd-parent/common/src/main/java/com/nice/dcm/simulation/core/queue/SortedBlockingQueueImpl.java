package com.nice.dcm.simulation.core.queue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class SortedBlockingQueueImpl<E> implements SortedQueue<E> {
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	private PriorityBlockingQueue<E> queue;
	
	/*
	 * E must implement Comparable interface
	 */
	public SortedBlockingQueueImpl() {
		queue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY);
	}
	
	/*
	 * provide Comparator to sort the Priority Queue
	 */
	public SortedBlockingQueueImpl(Comparator<? super E> comparator) {
		queue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY, comparator);
	}
	

	/*
	 * add element to queue
	 */
	public void add(E element) {
		queue.add(element);
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
	 * remove element from queue
	 */
	public boolean remove(E element) {
		return queue.remove(element);
	}
	
	/*
	 * find element from the queue with matcher
	 */
	public E find(Matcher<E> matcher, E element) {
		for (E e : queue) {
			if (matcher.match(e, element)) {
				return e;
			}
		}
		return null;
	}	
}
