package com.nice.dcm.simulator.sequence.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nice.dcm.simulator.sequence.spi.IntGenerator;

public class SequenceGenerator implements IntGenerator {

	@Override
	public Collection<Integer> generate(int numOfSeq, int intervalLength) {
		List<Integer> sequences = new ArrayList<>();
		if(numOfSeq == 0) {
			return sequences;
		}
		
		double gap = intervalLength / (double)numOfSeq;
		for(int i =0; i < numOfSeq; i++) {
			int seed = (int)Math.round(i * gap);
			sequences.add(seed);
		}
		return sequences;
	}

}
