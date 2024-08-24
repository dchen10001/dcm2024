package com.e2.wfm.simulation.output;

import java.util.List;

import com.e2.wfm.simulation.input.SimulationParameter;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SimulationResult (
			@JsonProperty("problemId") String problemId, 
			@JsonProperty("version") String version,
			@JsonProperty("simulationParameter") SimulationParameter simulationParameter,
			@JsonProperty("metrics") List<CTMetric> metrics) {
	
}
