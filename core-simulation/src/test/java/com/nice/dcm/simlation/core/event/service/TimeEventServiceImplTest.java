package com.nice.dcm.simlation.core.event.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simlation.core.event.TimeEvent;

public class TimeEventServiceImplTest {

	@Test	
	public void testOffer() {
		MockListener listener = new MockListener();
		TimeEventServiceImpl service = new TimeEventServiceImpl(listener, 5);
		long currentTime = 10;
		List<TimeEvent> events = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
            events.add(new MockTimeEvent(currentTime));
        }
		
		service.setCurrentTime(currentTime);
		service.offer(events);
		Future<Long> f = service.start();
		
		try {
			Long ret = f.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.terminate();
		System.out.println("testOffer done");
	}
}
