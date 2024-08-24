package com.e2.wfm.simulation.input;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class DistributionScript {
	@JsonProperty("distributionScript")
	private final String script;
	
	@JsonProperty("name")
	private final String name;
	
	public DistributionScript(String name, String script) {
		this.script = script;
		this.name = name;
	}
}
