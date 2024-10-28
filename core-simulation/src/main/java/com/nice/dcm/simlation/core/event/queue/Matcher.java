package com.nice.dcm.simlation.core.event.queue;

/**
 * A functional interface to match two objects
 * @param <E>
 */
public interface Matcher<E> {
	boolean match(E e1, E e2);
}
