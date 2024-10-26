package com.nice.dcm.simlation.core.event.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nice.dcm.simlation.core.event.TimeEvent;
import com.nice.dcm.simlation.core.event.service.generator.EventGenerator;

public class TimeEventQueue extends EventQueue<TimeEvent> implements EventGenerator {

	@Override
	public long getEventTime() {
		TimeEvent e = this.peek();
		if (e != null) {
			return e.getTimestamp();
		}
		return TimeEvent.INVALID_TIMESTAMP;
	}

	@Override
	public List<TimeEvent> getEvents(long currentTime) {
		if (this.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<TimeEvent> events = new ArrayList<>();
		while (!this.isEmpty()) {
			TimeEvent e = this.peek();
			if (e.getTimestamp() == currentTime) {
				events.add(this.poll());
			} else if (e.getTimestamp() < currentTime) {
				throw new IllegalStateException("Invalid event time: " + e.getTimestamp() + " < " + currentTime);
			} else {
				break;
			}
		}
		return events;
	}

}
