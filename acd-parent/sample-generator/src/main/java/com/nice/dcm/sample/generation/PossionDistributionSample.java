package com.nice.dcm.sample.generation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.statistics.distribution.ContinuousDistribution.Sampler;
import org.apache.commons.statistics.distribution.ExponentialDistribution;

public class PossionDistributionSample {

	public static void main(String[] args) {
		double sum = 0;
		int m = 10;
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		for(int i = 0; i < m; i++) {
			double[] samples = generator(10, 15);
			if (samples.length < min)
				min = samples.length;
			if (samples.length > max)
				max = samples.length;
			
			sum += samples.length;
		}
		double avg = sum/m;
		System.out.println("Iteration: " + m + " Average: " + avg + " Min: " + min + " Max: " + max);
	}
	
	public static double[] generator(double arrival_rate_per_interval, int interval_mins) {
		double arrival_rate_per_minute = arrival_rate_per_interval / interval_mins; // call per seconds
	
		
		UniformRandomProvider randomGenerator = UniformRandomProviderFactory.createKiss();
		
        ExponentialDistribution exponentialDistribution = ExponentialDistribution.of(1.0/arrival_rate_per_minute);
        
        Sampler sampler = exponentialDistribution.createSampler(randomGenerator);
        
        int size = (int) arrival_rate_per_interval + 100;
        
        double current_sum = 0d;
        
        double[] inter_arrival_times = new double[size];
        int i = 0;

        while (current_sum < interval_mins) {
        	double new_interval = sampler.sample();
        	new_interval = round(new_interval, 2);
        	current_sum += new_interval;
			if (current_sum >= interval_mins) {
				break;
			}
        	inter_arrival_times[i++] = current_sum;
        }
        
        double[] dest_arr = new double[i];
        System.arraycopy(inter_arrival_times, 0, dest_arr, 0, i);
        //System.out.println("Size: " + dest_arr.length);
        return dest_arr;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
