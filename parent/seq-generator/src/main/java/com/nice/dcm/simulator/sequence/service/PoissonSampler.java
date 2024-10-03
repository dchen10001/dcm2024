package com.nice.dcm.simulator.sequence.service;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PoissonSampler {
	
	public static int sample(double lamda, int range) {
		if(lamda <= 0) {
			return 0;
		}
		
        PoissonDistribution poisson = new PoissonDistribution(lamda); 
        
        int sample = (int)lamda;
        int start = sample - range;
        if(start < 0) {
        	start = 0;
        }
        int end = sample + range;
        
        double probabilityOfSample = 0;
        for(int i = start; i < end; i++) {       	
        	double tmpProbabilityOfSample = poisson.probability(i);
        	System.out.println("P[" + i +
        			"[ = " + tmpProbabilityOfSample);
        	if(tmpProbabilityOfSample > probabilityOfSample) {
        		sample = i;
        		probabilityOfSample = tmpProbabilityOfSample;
        	}
        }
        return sample;
	}
	
	public static int sample(double lamda) {
		if(lamda <= 0) {
			return 0;
		}
		
		PoissonDistribution poisson = new PoissonDistribution(lamda); 
		return poisson.sample();
	}
	
	public static void main(String[] args){
		int sample = PoissonSampler.sample(0.85, 5);
		System.out.println(sample);
	}
}
