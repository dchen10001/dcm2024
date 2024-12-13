package com.nice.dcm.sample.generation;

public enum GeneratorType {
	RANDOM("Random"), SEQUENCE("Sequence"), POISSION("Poission");
	
	private String name;

	GeneratorType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static GeneratorType fromName(String name) {
		for (GeneratorType type : values()) {
			if (type.getName().equalsIgnoreCase(name)) {
				return type;
			}
		}
		return null;
	}
}
