package com.nice.dcm.simulator.sequence.service;

import java.util.Collection;
import java.util.TreeSet;

public class Possion {
	private long seed;
	private final double m;
	
	public Possion() {
		this.seed = System.currentTimeMillis();;
		this.m = Math.pow(2, 31);
	}
	
	public double p_rand(double lamda) {
		double p = 1;
		double u;
		double c = Math.exp(-lamda);
		do {
			u = randomU();
			p *= u;
			//if(p >= c) {
			//	n++;
			//}
		} while (p >= c);
		return u;
	}
	
	private double randomU() {
		seed = (long) ((32719 * seed) % (m-1));
		return seed / (m -1);
	}
	
	public Collection<Long> generate(int numOfData, int intervalSeconds) {
		Collection<Long> set = new TreeSet<>();
		double lamda = 1d * numOfData / intervalSeconds;
		for(int i = 0; i < numOfData; i++) {
			double u = p_rand(lamda) * intervalSeconds;
			long v = Math.round(u);
			set.add(v);
		}
		return set;
	}
	
	public static void main(String[] args) { 
		Possion possion = new Possion();
		Collection<Long> dataSet = possion.generate(20, 40);
		for(Long l : dataSet) {
			System.out.println(l);
		}
	}
}
