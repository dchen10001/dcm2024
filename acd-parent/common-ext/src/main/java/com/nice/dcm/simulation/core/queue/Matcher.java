package com.nice.dcm.simulation.core.queue;

/**
 * A functional interface to match two objects
 * @param <E>
 */
public interface Matcher<E> {
	boolean match(E e1, E e2);
}