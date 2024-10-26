package com.nice.dcm.simlation.core.event.queue;

public interface Matcher<E> {
	boolean match(E e1, E e2);
}
