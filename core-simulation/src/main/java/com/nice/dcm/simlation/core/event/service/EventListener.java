package com.nice.dcm.simlation.core.event.service;

public interface EventListener<E> {
	void onEvent(E element);
	void setCurrentTime(long currentTime);
}
