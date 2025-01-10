package com.nice.dcm.simulation.generation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.statistics.distribution.ContinuousDistribution.Sampler;
import org.apache.commons.statistics.distribution.ExponentialDistribution;
import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.generation.output.VolumePairRange;

class AbstractTimeSeriesGeneratorImplTest {
	@Test
	void test() {
		AbstractTimeSeriesGeneratorImpl generator1 = new AbstractTimeSeriesGeneratorImpl();
		assertNotNull(generator1.getUniformRandomProvider());
		
		double mean = 5.0;
		ExponentialDistribution generator = ExponentialDistribution.of(1.0 / mean);
		Sampler sampler = generator.createSampler(generator1.getUniformRandomProvider());
		for (int k = 0; k < 5; k++) {
			System.out.println("k : " + k);
			double total = 0.0;
			for (int i = 0; i < 5; i++) {
				double a = sampler.sample();
				total += a;
				System.out.println("total : " + total + ", sample : " + a);
			}
		}
	}
	
	@Test
	void testGetVolumePairRangeLowerValue() {
		AbstractTimeSeriesGeneratorImpl generator = new AbstractTimeSeriesGeneratorImpl();
		assertNotNull(generator.getUniformRandomProvider());
		double probability = 0.60;

		double mean = 0.01; 
		VolumePairRange volumePairRange = generator.getVolumePairRange(mean, probability);
		assertTrue(volumePairRange.getAccumulatePossibility() >= probability);
		assertEquals(0, volumePairRange.getLowerBound().getVolume());
		assertEquals(1, volumePairRange.getUpperBound().getVolume());
		
		mean = 0.1; 
		volumePairRange = generator.getVolumePairRange(mean, probability);
		assertTrue(volumePairRange.getAccumulatePossibility() >= probability);
		assertEquals(0, volumePairRange.getLowerBound().getVolume());
		assertTrue(volumePairRange.getLowerBound().getPossibility() > 0.9048);
		assertEquals(1, volumePairRange.getUpperBound().getVolume());
		assertTrue(Math.abs(volumePairRange.getUpperBound().getPossibility() - 0.090483) < 0.0001);
		
		mean = 0.9; 
		volumePairRange = generator.getVolumePairRange(mean, probability);
		assertTrue(volumePairRange.getAccumulatePossibility() >= probability);
		assertEquals(0, volumePairRange.getLowerBound().getVolume());
		assertTrue(volumePairRange.getLowerBound().getPossibility() > 0.4065);
		assertEquals(1, volumePairRange.getUpperBound().getVolume());
		assertTrue(Math.abs(volumePairRange.getUpperBound().getPossibility() - 0.36591) < 0.0001);
				
		mean = 5.9; 
		volumePairRange = generator.getVolumePairRange(mean, probability);
		assertTrue(volumePairRange.getAccumulatePossibility() >= probability);
		assertEquals(5, volumePairRange.getLowerBound().getVolume());
		assertTrue(volumePairRange.getLowerBound().getPossibility() > 0.1632);
		assertEquals(9, volumePairRange.getUpperBound().getVolume());
		assertTrue(Math.abs(volumePairRange.getUpperBound().getPossibility() - 0.06539) < 0.0001);
	}
	
	
	@Test
	void testGetVolumePairRangeHighValue() {
		AbstractTimeSeriesGeneratorImpl generator = new AbstractTimeSeriesGeneratorImpl();
		assertNotNull(generator.getUniformRandomProvider());
		double probability = 0.60;

		double mean = 150.1; 
		VolumePairRange volumePairRange = generator.getVolumePairRange(mean, probability);
		assertTrue(volumePairRange.getAccumulatePossibility() >= 0.51385);
		assertEquals(150, volumePairRange.getLowerBound().getVolume());
		assertTrue(volumePairRange.getLowerBound().getPossibility() > 0.03255);
		assertEquals(194, volumePairRange.getUpperBound().getVolume());
		assertTrue(Math.abs(volumePairRange.getUpperBound().getPossibility() - 8.0E-5) < 0.0001);
		
		mean = 150.9; 
		volumePairRange = generator.getVolumePairRange(mean, probability);
		assertTrue(volumePairRange.getAccumulatePossibility() >= 0.53979);
		assertEquals(150, volumePairRange.getLowerBound().getVolume());
		assertTrue(volumePairRange.getLowerBound().getPossibility() > 0.03246);
		assertEquals(195, volumePairRange.getUpperBound().getVolume());
		assertTrue(Math.abs(volumePairRange.getUpperBound().getPossibility() - 7.8E-5) < 0.0001);
	}
	
	@Test
	void testGetVolumePairRange1() {
		AbstractTimeSeriesGeneratorImpl generator = new AbstractTimeSeriesGeneratorImpl();
		double means[] = {0.1, 0.9, 5.9, 150.1, 150.9};
		for(double mean : means) {
			VolumePairRange volumePairRange = generator.getVolumePairRange(mean);
			assertEquals(volumePairRange.getAccumulatePossibility(), volumePairRange.getLowerBound().getPossibility() + volumePairRange.getUpperBound().getPossibility());
			assertEquals((int)mean, volumePairRange.getLowerBound().getVolume());
			assertEquals(1 + (int)mean, volumePairRange.getUpperBound().getVolume());
		}
	}
}
