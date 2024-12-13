package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.QueueStatus;

class QueueStatusTest {
	@Test
	void testQueueStatus() {
		assertEquals("LEAST_BUSY", QueueStatus.LEAST_BUSY.toString());
		assertSame(QueueStatus.LEAST_BUSY, QueueStatus.valueOf("LEAST_BUSY"));
		
	}
}
