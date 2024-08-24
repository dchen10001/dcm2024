package com.e2.wfm.simulation.input;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class IdName {
	private String oid;
	private Long id;
	private String name;
    
    protected IdName(String oid, Long id, String name) {
		super();
		this.oid = oid;
		this.id = id;
		this.name = name;
	}
}
