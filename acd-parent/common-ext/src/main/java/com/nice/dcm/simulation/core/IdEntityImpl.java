package com.nice.dcm.simulation.core;

public class IdEntityImpl implements IdEntity {
	private final long id;
	
	public IdEntityImpl() {
		this.id = SequenceGenerator.generateId(this.getClass());
	}
	
	@Override
	public long getId() {
		return id;
	}
}
