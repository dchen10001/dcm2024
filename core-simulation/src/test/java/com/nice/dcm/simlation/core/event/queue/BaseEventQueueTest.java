package com.nice.dcm.simlation.core.event.queue;

import java.util.Comparator;

import com.nice.dcm.simlation.core.event.AgentEvent;
import com.nice.dcm.simlation.core.event.AgentEventImpl;
import com.nice.dcm.simlation.core.event.ContactEventImpl;
import com.nice.dcm.simlation.core.event.EventType;
import com.nice.dcm.simlation.core.event.TimeEvent;

abstract class BaseEventQueueTest {
	protected EventQueue<TimeEvent> getSortedByTimeQueue(int iteration, int size, boolean threadSafe) {
		EventQueue<TimeEvent>  queue = threadSafe ? new EventBlockingQueueImpl<>() : new EventQueueImpl<>();
		addDataToQueue(queue, iteration, size);
		return queue;
	}
	
	protected EventQueue<TimeEvent> getSortedByIdQueueOrder(int iteration, int size, boolean threadSafe) {
		Comparator<TimeEvent> comparator = getComparator();
		
		EventQueue<TimeEvent> queue = threadSafe ? new EventBlockingQueueImpl<>(comparator) : new EventQueueImpl<>(comparator);
		addDataToQueue(queue, iteration, size);
		return queue;
	}
	
	private Comparator<TimeEvent> getComparator() {
		return new Comparator<TimeEvent>() {
			@Override
			public int compare(TimeEvent o1, TimeEvent o2) {
				long ret = o1.getId() - o2.getId();
				if (ret == 0) {
					return 0;
				}
				return ret > 0 ? 1 : -1;
			}
		};
	}
	
	private void addDataToQueue(EventQueue<TimeEvent> queue, int iteration, int size) {
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
	}	
}
