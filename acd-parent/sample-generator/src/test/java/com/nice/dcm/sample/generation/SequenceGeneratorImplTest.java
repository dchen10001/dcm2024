package com.nice.dcm.sample.generation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.statistics.distribution.PoissonDistribution;
import org.junit.jupiter.api.Test;

import com.nice.dcm.sample.generation.input.CallVolumes;
import com.nice.dcm.sample.generation.input.CallVolumesImpl;
import com.nice.dcm.sample.generation.input.IntervalCallVolume;
import com.nice.dcm.sample.generation.input.IntervalCallVolumeImpl;
import com.nice.dcm.sample.generation.output.TimeSeries;

class SequenceGeneratorImplTest {
	
	@Test
	void test10Generate() {
		double mean = 10.3;
		PoissonDistribution poissonDistribution = PoissonDistribution.of(mean);
		
		double p1 = poissonDistribution.probability(14);
		
		for(int i = 1; i < 6; i++) {
			int n = (int)mean - i;
			double p = poissonDistribution.probability(n);
			System.out.println("n: " + n + " p: " + p);
			n = (int)mean + i;
			p = poissonDistribution.probability(n);
			System.out.println("n: " + n + " p: " + p);			
		}
		
        SequenceGeneratorImpl generator = new SequenceGeneratorImpl();
        
        long startTime = 0;
        long endTime = 10;
        long intervalSize = 10;
        CallVolumes callVolumes = new  CallVolumesImpl(List.of(new IntervalCallVolumeImpl(0, 10, 5)));
        TimeSeries timeSeries = generator.generate(callVolumes, startTime, endTime, intervalSize);
        
        long[] arrivals = timeSeries.getArrivals();
        assertEquals(5, arrivals.length); 
		for (int i = 0; i < arrivals.length; i++) {
			assertEquals(1 + i * 2, arrivals[i]);
		}
	}
	
	@Test
	void testGenerate() {
		SequenceGeneratorImpl generator = new SequenceGeneratorImpl();
		
		CallVolumes callVolumes = getCallVolumes();
		long startTime = 0;
		long endTime = 24 * 60 * 60;
		long intervalSize = 900;
		TimeSeries timeSeries = generator.generate(callVolumes, startTime, endTime, intervalSize);
		
		long[] arrivals = timeSeries.getArrivals();
		assertEquals(480, arrivals.length); 
		assertEquals(28843, arrivals[0]);
		assertEquals(57518, arrivals[480 - 1]);
		
		for (int i = 1; i < arrivals.length; i++) {
			assertTrue(arrivals[i] < 86400 && arrivals[i] > 0);
			assertTrue(arrivals[i] > arrivals[i - 1]);
		}
	}
	
	@Test
	void testNoCallValueGenerate() {
        SequenceGeneratorImpl generator = new SequenceGeneratorImpl();
        
        CallVolumes callVolumes = new  CallVolumesImpl(List.of());
        long startTime = 0;
        long endTime = 24 * 60 * 60;
        long intervalSize = 900;
        TimeSeries timeSeries = generator.generate(callVolumes, startTime, endTime, intervalSize);
        
        long[] arrivals = timeSeries.getArrivals();
        assertEquals(0, arrivals.length);         
	}
	
	
	@Test
	void testNullCallValueGenerate() {
		SequenceGeneratorImpl generator = new SequenceGeneratorImpl();
		
		CallVolumes callVolumes = new  CallVolumesImpl(null);
		long startTime = 0;
		long endTime = 24 * 60 * 60;
		long intervalSize = 900;
		TimeSeries timeSeries = generator.generate(callVolumes, startTime, endTime, intervalSize);
		
		long[] arrivals = timeSeries.getArrivals();
		assertEquals(0, arrivals.length);         
	}
	
	private CallVolumes getCallVolumes() {
		List<IntervalCallVolume> intervalCallVolumes = new ArrayList<>();
		for (int i = 0; i < 96; i++) {
			double callVolume = 0;
			if(i < 32) {
				callVolume = 0;
			} else if (i < 48) {
				callVolume = 20;
			} else if (i < 64) {
				callVolume = 10;
			} else {
				callVolume = 0;
			}
			intervalCallVolumes.add(new IntervalCallVolumeImpl(i * 900, (i + 1) * 900, callVolume));
		}
		return new  CallVolumesImpl(intervalCallVolumes);
	}
}
