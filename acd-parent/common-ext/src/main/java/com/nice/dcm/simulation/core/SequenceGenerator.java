package com.nice.dcm.simulation.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface SequenceGenerator {
	final Map<Class<?>, AtomicLong> idMap = new ConcurrentHashMap<>();

	static long generateId(Class<?> cls) {
		AtomicLong idGenerator = idMap.computeIfAbsent(cls, k ->new AtomicLong(0));
		return idGenerator.getAndIncrement();
	}
	
	static void reSetId(Class<?> cls) {
		AtomicLong idGenerator = idMap.computeIfAbsent(cls, k ->new AtomicLong(0));
		idGenerator.set(0);
	}

	static void reSetAllIds() {
		idMap.values().forEach(idGenerator -> idGenerator.set(0));
	}
}
