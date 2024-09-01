package com.nice.dcm.simulator.sequence.spi;

import java.util.Collection;

public interface IntGenerator {
	Collection<Integer> generate(int numOfSeq, int intervalLength);
}
