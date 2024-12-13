package com.nice.dcm.simulation.core.exception;

public class RangeOverlapException extends RuntimeException {
	private static final long serialVersionUID = 6398206664611190622L;
	
	private final long upperBound1;
	
	private final long lowerBound1;
	
	private final long upperBound2;
	
	private final long lowerBound2;

	public RangeOverlapException(String message, long lowerBound1, long upperBound1, long lowerBound2, long upperBound2) {
		super(message);
		this.upperBound1 = upperBound1;
		this.lowerBound1 = lowerBound1;
		this.upperBound2 = upperBound2;
		this.lowerBound2 = lowerBound2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getUpperBound1() {
		return upperBound1;
	}

	public long getLowerBound1() {
		return lowerBound1;
	}

	public long getUpperBound2() {
		return upperBound2;
	}

	public long getLowerBound2() {
		return lowerBound2;
	}
}
