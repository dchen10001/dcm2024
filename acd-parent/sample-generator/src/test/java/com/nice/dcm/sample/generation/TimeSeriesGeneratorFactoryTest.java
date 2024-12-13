package com.nice.dcm.sample.generation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TimeSeriesGeneratorFactoryTest {
	@Test
	void testCreateGenerator() {
		TimeSeriesGeneratorFactory factory = TimeSeriesGeneratorFactory.getInstance();
		TimeSeriesGenerator generator = factory.getGenerator(GeneratorType.RANDOM);
		assertNotNull(generator);
		assertTrue(generator instanceof RandomGeneratorImpl);
		
		generator = factory.getGenerator(GeneratorType.SEQUENCE);
		assertNotNull(generator);
		assertTrue(generator instanceof SequenceGeneratorImpl);
		
		generator = factory.getGenerator(GeneratorType.POISSION);
		assertNotNull(generator);
		assertTrue(generator instanceof PoissionGeneratorImpl);
	}
	
	@Test
	void testCreateGeneratorByString() {
		TimeSeriesGeneratorFactory factory = TimeSeriesGeneratorFactory.getInstance();
		TimeSeriesGenerator generator = factory.getGenerator("RANDOM");
		assertNotNull(generator);
		assertTrue(generator instanceof RandomGeneratorImpl);
		
		generator = factory.getGenerator("random");
		assertTrue(generator instanceof RandomGeneratorImpl);
		
		generator = factory.getGenerator("SEQUENCE");
		assertNotNull(generator);
		assertTrue(generator instanceof SequenceGeneratorImpl);
		
		generator = factory.getGenerator("sequence");
		assertTrue(generator instanceof SequenceGeneratorImpl);
		
		generator = factory.getGenerator("POISSION");
		assertNotNull(generator);
		assertTrue(generator instanceof PoissionGeneratorImpl);
		
		generator = factory.getGenerator("poission");
		assertTrue(generator instanceof PoissionGeneratorImpl);
		
		generator = factory.getGenerator("unknown");
		assertNull(generator);
	}
}
