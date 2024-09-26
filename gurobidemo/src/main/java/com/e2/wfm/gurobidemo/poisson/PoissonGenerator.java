package com.e2.wfm.gurobidemo.poisson;

import java.util.Random;

public class PoissonGenerator {
    private final Random random;
    public PoissonGenerator(long seed) {
        super();
        this.random = new Random(seed);
    }

    /**
     * Generate a random number that follows a Poisson distribution
     * lambda is the average number of events in a time period
     * @return
     */
    public int nextPoission(double lambda) {
        double l = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;
        do {
            k++;
            p *= random.nextDouble();
        } while (p > l);
        return k - 1;
    }
    
    public int[] genetateArrival(double lambda, int size) {
        int[] arrivals = new int[size];
        arrivals[0] = nextPoission(lambda);
        for (int i = 1; i < size; i++) {
            int arrival = nextPoission(lambda);
            System.out.println("Arrival: " + arrival);
            arrivals[i] = arrivals[i - 1] + arrival;
        }
        return arrivals;
    }
    
    public static void main(String[] args) {
        PoissonGenerator poissonGenerator = new PoissonGenerator(123456);
        double lambda = 2; // average number of events in a time period
        int[] arrivals = poissonGenerator.genetateArrival(lambda, 10);
        lambda = 0.2; // average number of events in a time period
        int[] arrivals1 = poissonGenerator.genetateArrival(lambda, 10);
        
        lambda = 1; // average number of events in a time period
        int[] arrivals2 = poissonGenerator.genetateArrival(lambda, 10);
        lambda = 2; // average number of events in a time period
        int[] arrivals3 = poissonGenerator.genetateArrival(lambda, 10);
        lambda = 3; // average number of events in a time period
        int[] arrivals4 = poissonGenerator.genetateArrival(lambda, 10);
        lambda = 4; // average number of events in a time period
        int[] arrivals5 = poissonGenerator.genetateArrival(lambda, 10);
        lambda = 5; // average number of events in a time period
        int[] arrivals6 = poissonGenerator.genetateArrival(0.1, 10);
    }
    
    public static int rand(double lambda) {
        Random random = new Random();
        double u = random.nextDouble();
        int x = 0;
        double cdf = 0;
        while (u >= cdf) {
            x++;
            double v = Math.exp(-lambda * x);
            cdf = 1 - v;
        }
        return x;
    }
    
    public static double posibility(int n, double lambda, double t) {
        double lambdaT = lambda * t;
        double v = Math.pow(lambdaT, n);
        double l = Math.exp(-lambdaT);
        
        int sum = 1;
        for(int i=1; i<=n; i++){
            sum = sum * i;
        }  
        
        return 100 * v * l / sum;
    }

    public static int exponential(double lambda, double u ) {
        //double u = Math.random();
        int x = 0;
        double cdf = 0;
        while (cdf < u) {
            double v = Math.exp(-lambda * x);
            v = lambda * v;
            cdf += v;
            x++;
        }
        return x;
    }
}
