package com.nice.dcm.simulation.generation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.generation.output.VolumePairRange;

class TimeSeriesGeneratorTest {
	@Test
	void testGetMean() {
		TimeSeriesGenerator timeSeriesGenerator = new TimeSeriesGenerator() {

			@Override
			public VolumePairRange getVolumePairRange(double mean) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public VolumePairRange getVolumePairRange(double mean, double probability) {
				// TODO Auto-generated method stub
				return null;
			}

		};
		
		double mean = timeSeriesGenerator.getMean(1, 1);
		assertEquals(1, mean);
		
		mean = timeSeriesGenerator.getMean(5, 1);
		assertEquals(0.2, mean);
	}
	
	@Test
	void testGetMeanException() {
		TimeSeriesGenerator timeSeriesGenerator = new TimeSeriesGenerator() {

			@Override
			public VolumePairRange getVolumePairRange(double mean) {
				return null;
			}

			@Override
			public VolumePairRange getVolumePairRange(double mean, double probability) {
				return null;
			}

		};
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			timeSeriesGenerator.getMean(0, 1);
		});
		assertEquals("volume must be great than 0.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			timeSeriesGenerator.getMean(1, 0);
		});
		assertEquals("interval must be great than 1.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			timeSeriesGenerator.getMean(1.0 / Double.MAX_VALUE, 1);
		});
		assertEquals("volume is too lower which causes mean is infinity.", exception.getMessage());
	}
	
}
