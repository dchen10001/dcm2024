package com.e2.wfm.gurobidemo.poisson;

import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ExponentialGenerator {
    public static void main(String[] argv) {
    	int timeUnit = 900; // 15 minutes
    	double callValue = 5; // 10 calls per time unit
    	
    	double lambda = 1 / callValue;
    	
    	//ExponentialDistribution exp = new ExponentialDistribution(lambda);
    	
    	NormalDistribution exp = new NormalDistribution(2, 1);
    	int sampleSize = 100;
    	double[] values = new double[sampleSize];
    	double total = 0;
    	for (int i = 0; i < sampleSize; i++) {
    		values[i] = exp.sample();
    		total += values[i];
    	}
    	
    	double mean = total / sampleSize;
    	double meanSquare = 0;
    	
		for (int i = 0; i < sampleSize; i++) {
			meanSquare += Math.pow(values[i] - mean, 2);
			System.out.println("values[" + i + "] = " + values[i]);
		}
    	
		meanSquare = meanSquare / sampleSize;
		double stdDev = Math.sqrt(meanSquare);
		
    	System.out.println("mean = " + mean + " stdDev = " + stdDev);    
    }
}
