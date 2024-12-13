package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.RuleAction;

class RuleActionTest {
	@Test
	void testQueueStatus() {
		assertEquals("QUEUE_TO", RuleAction.QUEUE_TO.toString());
		assertSame(RuleAction.QUEUE_TO, RuleAction.valueOf("QUEUE_TO"));
		
	}
}
