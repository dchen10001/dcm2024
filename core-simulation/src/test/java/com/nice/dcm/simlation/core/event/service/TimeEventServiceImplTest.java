package com.nice.dcm.simlation.core.event.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nice.dcm.simlation.core.event.TimeEvent;

class TimeEventServiceImplTest {

	@Test	
	void testOffer() {
		MockListener listener = new MockListener();
		TimeEventServiceImpl service = new TimeEventServiceImpl(listener, 5);
		for (long currentTime = 10; currentTime < 100; currentTime += 10) {
			List<TimeEvent> events = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				events.add(new MockTimeEvent(currentTime));
			}
			service.offer(currentTime, events);
			
			Future<Long> f = service.start();
			
			try {
				Long ret = f.get();
				Assertions.assertEquals(events.size(), ret);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("testOffer done");
	}
}
