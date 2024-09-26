package com.e2.wfm.gurobidemo.poisson;

import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ExponentialGenerator {
    public static void main(String[] argv) {
        double[] callVolumes = new double[] {1.5, 2.5, 3.5, 4.5};
        double lambda = 0; // average number of events in a time period
        for (double callVolume : callVolumes) {
            lambda += callVolume;
        }
        //sample size
        int size = (int)lambda;
        
        int totalSeconds = 900 * 4;
        //
        lambda = 0.2;
        size = 20;
        double[] arrivals = new double[size];

        ExponentialDistribution exp = new ExponentialDistribution(lambda);
        arrivals[0] = exp.sample();
        for (int i = 1; i < size; i++) {
            double del = exp.sample();
            arrivals[i] = del + arrivals[i - 1];
        }
        
        for (int i = 0; i < size; i++) {
            double seconds = arrivals[i] * totalSeconds;
            double minutes = seconds / 60;
            System.out.println("i = " + i + " |"  + arrivals[i] + " | seconds=  " + seconds + "  | minutes = " + minutes);
        }        
    }
}
