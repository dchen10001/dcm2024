package com.nice.dcm.simlation.core.event.queue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;

import com.nice.dcm.simlation.core.event.AgentEvent;
import com.nice.dcm.simlation.core.event.AgentEventImpl;
import com.nice.dcm.simlation.core.event.ContactEventImpl;
import com.nice.dcm.simlation.core.event.EventType;
import com.nice.dcm.simlation.core.event.TimeEvent;

public class EventBlockingQueueTest {
	@BeforeEach
	void setUp() {
		TimeEvent.reSetId();
	}
	
	
	private EventBlockingQueueImpl<TimeEvent> getQueue(int iteration, int size) {
		EventBlockingQueueImpl<TimeEvent> queue = new EventBlockingQueueImpl<>();
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
	
	private EventBlockingQueueImpl<TimeEvent> getQueueOrderById(int iteration, int size) {
		EventBlockingQueueImpl<TimeEvent> queue = new EventBlockingQueueImpl<>(new Comparator<TimeEvent>() {
			@Override
			public int compare(TimeEvent o1, TimeEvent o2) {
				long ret = o1.getId() - o2.getId();
				if (ret == 0) {
					return 0;
				}
				return ret > 0 ? 1 : -1;
			}
		});
		
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
