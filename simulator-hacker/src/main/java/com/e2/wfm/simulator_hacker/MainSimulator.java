package com.e2.wfm.simulator_hacker;

import java.util.Map;
import java.util.function.BiFunction;

import org.nice.simulation.Simulator;

import com.nice.dcm.scheduler.core.simulator.GoodEnoughParameters;
import com.nice.dcm.scheduler.simulator.ValidationSimulator;
import com.nice.dcm.scheduler.simulator.exception.ValidatorSimulationException;
import com.nice.dcm.scheduler.simulator.input.SimulatorInput;
import com.nice.saas.dcm.simulator.access.actions.distribute.CtScripts;
import com.nice.saas.dcm.simulator.access.actions.distribute.DistributionRuleRunnerProcessor;
import com.nice.saas.dcm.simulator.access.adaptable.AdaptableContext;
import com.nice.saas.dcm.simulator.access.adaptable.AdaptableRegistry;
import com.nice.saas.dcm.simulator.access.adaptable.AdaptableSimulatorRunner;

public class MainSimulator {

	public static void main(String[] args) {
		
		GoodEnoughParameters goodEnoughParameters = null;
		SimulatorInput simulatorInput = null;
        AdaptableContext context = new AdaptableSimulatorRunner().getContext();
        ValidationSimulator validationSimulator = new ValidationSimulator(
                context,
                simulatorInput,
                goodEnoughParameters);
        
      
        try {
            Simulator.simulateFromInputStream(context,
                    ValidationSimulator.class.getClassLoader().getResourceAsStream(ValidationSimulator.MODEL_PATH_WITH_DISTRIBUTION_RULES));
        } catch (Exception e) {
            throw new ValidatorSimulationException("ValidationSimulator Adapter In Mapper error", e);
        }
	}

}
