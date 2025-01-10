package com.nice.dcm.simulation.generation.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VolumePairRangeImplTest {
	@Test
	void testVolumePairRangeImpl() {
		VolumePairImpl lowerBound = new VolumePairImpl(1, 0.5);
		VolumePairImpl upperBound = new VolumePairImpl(2, 0.6);
		VolumePairRangeImpl volumePairRangeImpl = new VolumePairRangeImpl(0.001, lowerBound, upperBound);
		assertEquals(lowerBound, volumePairRangeImpl.getLowerBound());
		assertEquals(upperBound, volumePairRangeImpl.getUpperBound());
		
		volumePairRangeImpl = new VolumePairRangeImpl(0.001, lowerBound, null);
		assertEquals(lowerBound, volumePairRangeImpl.getLowerBound());
		assertNull(volumePairRangeImpl.getUpperBound());
	}
	
	@Test
	void testVolumePairRangeImplException() {
		VolumePairImpl lowerBound = new VolumePairImpl(1, 0.5);
		VolumePairImpl upperBound = new VolumePairImpl(2, 0.6);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairRangeImpl(0.001, upperBound, lowerBound);
		});
		assertEquals("upper bound must be greater than lower bound.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairRangeImpl(0.001, lowerBound, lowerBound);
		});
		assertEquals("upper bound must be greater than lower bound.", exception.getMessage());
		
		exception = assertThrows(NullPointerException.class, () -> {
			new VolumePairRangeImpl(0.001, null, upperBound);
		});
		assertEquals("lowerBound is marked non-null but is null", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairRangeImpl(1.001, lowerBound, upperBound);
		});
		assertEquals("probability must be in the range [0, 1].", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new VolumePairRangeImpl(-0.001, lowerBound, upperBound);
		});
		assertEquals("probability must be in the range [0, 1].", exception.getMessage());
	}
}
