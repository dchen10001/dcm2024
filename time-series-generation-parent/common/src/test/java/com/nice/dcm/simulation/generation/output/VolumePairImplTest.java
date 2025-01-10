package com.nice.dcm.simulation.generation.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VolumePairImplTest {
	@Test
	void testVolumePairImpl() {
		VolumePairImpl volumePairImpl = new VolumePairImpl(1, 0.5);
		assertEquals(1, volumePairImpl.getVolume());
		assertEquals(0.5, volumePairImpl.getPossibility());
	}
	
	@Test
	void testVolumePairImplException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairImpl(-1, 0.5);
		});
		assertEquals("volume is less than 0.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairImpl(1, -0.5);
		});
		assertEquals("possibility must be between 0 and 1.", exception.getMessage());
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairImpl(1, 1.5);
		});
		assertEquals("possibility must be between 0 and 1.", exception.getMessage());
	}
	
}
