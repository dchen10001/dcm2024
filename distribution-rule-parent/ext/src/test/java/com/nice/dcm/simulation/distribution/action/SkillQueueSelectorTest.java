package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SkillQueueSelectorTest {
	@Test
	void testGenerateId() {
		assertEquals(0, SkillQueueSelector.generateId());
		assertEquals(1, SkillQueueSelector.generateId());
		assertEquals(2, SkillQueueSelector.generateId());
		assertEquals(3, SkillQueueSelector.generateId());
		
		SkillQueueSelector.resetId();
		assertEquals(0, SkillQueueSelector.generateId());
		assertEquals(1, SkillQueueSelector.generateId());
		assertEquals(2, SkillQueueSelector.generateId());
		assertEquals(3, SkillQueueSelector.generateId());		
	}
}
