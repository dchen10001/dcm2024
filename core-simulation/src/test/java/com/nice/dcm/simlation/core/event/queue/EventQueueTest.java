package com.nice.dcm.simlation.core.event.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nice.dcm.simlation.core.event.AgentEvent;
import com.nice.dcm.simlation.core.event.AgentEventImpl;
import com.nice.dcm.simlation.core.event.ContactEventImpl;
import com.nice.dcm.simlation.core.event.EventType;
import com.nice.dcm.simlation.core.event.TimeEvent;
import com.nice.dcm.simlation.core.event.TimeEventImpl;

class EventQueueTest {
	
	@BeforeEach
	void setUp() {
		TimeEvent.reSetId();
	}
	
	@Test
	void testAddEventSorting() {
		EventQueue<TimeEvent> queue = getQueue(10, 5);
		int i = queue.size();		
	    TimeEvent e = queue.peek();
	    TimeEvent e1 = queue.peek();
	    Assertions.assertSame(e, e1);
		long currentTime = e.getTimestamp();
		int size = 0;
		
		while (!queue.isEmpty()) {
			e = queue.poll();
			if (e.getTimestamp() < currentTime) {
				Assertions.fail("Events are not sorted");
			} else {
				currentTime = e.getTimestamp();
			}
			size++;
		}
		Assertions.assertEquals(i, size);
		queue.clear();
	}
	
	@Test
	void testFindRemove() {
		EventQueue<TimeEvent> queue = getQueue(10, 5);
		
		int size = queue.size();
		
		TimeEvent event = new TimeEventImpl(size + 1, 0l, EventType.CONTACT_ARRIVAL);
		TimeEvent e = queue.find((e1, e2) -> {
			return e1.getId() == e2.getId();
		}, event);
		
		Assertions.assertNull(e);
		
		for (int i = size - 1; i >= 0; i--) {
			event = new TimeEventImpl(i, 0, EventType.CONTACT_ARRIVAL);
			e = queue.find((e1, e2) -> {
				return e1.getId() == e2.getId();
			}, event);
			Assertions.assertNotNull(e, " i = " + i);
			queue.remove(e);
		}

		e = queue.peek();
		Assertions.assertNull(e);
		Assertions.assertTrue(queue.isEmpty());
	}
	
	@Test
	void testPerformance() {
		EventQueue<TimeEvent> queue = getQueue(400, 25);
		
		int size = queue.size();
		Assertions.assertEquals(20000, size);
		long start = System.currentTimeMillis();
		TimeEvent event = new TimeEventImpl(size + 1, 0l, EventType.CONTACT_ARRIVAL);
		TimeEvent e = queue.find((e1, e2) -> {
			return e1.getId() == e2.getId();
		}, event);
		long end = System.currentTimeMillis();
		Assertions.assertNull(e);
		System.out.println("Time taken to find event = " + (end - start) + " ms");
		
		start = System.currentTimeMillis();
		while (!queue.isEmpty()) {
			e = queue.poll();
			Assertions.assertNotNull(e);
		}
		end = System.currentTimeMillis();
		System.out.println("Time taken to poll all = " + (end - start) + " ms");
		
		queue = getQueue(400, 25);
		start = System.currentTimeMillis();
		queue.clear();
		end = System.currentTimeMillis();
		System.out.println("Time taken to clear all = " + (end - start) + " ms");
		
	}
	
	private EventQueue<TimeEvent> getQueue(int iteration, int size) {
		EventQueue<TimeEvent> queue = new EventQueue<>();
		long timestamp = 0;
		for (int i = 0; i < iteration * size; i++) {
			ContactEventImpl event = new ContactEventImpl(timestamp, "ctOid" + i, EventType.CONTACT_ARRIVAL);
			if (i > 0 && i % size == 0) {
				timestamp += 10;
			}
			queue.add(event);
		}
		
		timestamp = 0;
		for (int i = 0; i < iteration * size; i++) {
			AgentEvent agentEvent = new AgentEventImpl(i * 100, "agentOid" + i, EventType.AGENT_LOGIN);
			if (i > 0 && i % size == 0) {
				timestamp += 10;
			}
			queue.add(agentEvent);
		}
		return queue;
	}
}
