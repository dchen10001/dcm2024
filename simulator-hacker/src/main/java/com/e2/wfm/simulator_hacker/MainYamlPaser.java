package com.e2.wfm.simulator_hacker;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.nice.simulation.infrastructure.distribution.MultiDistribution;
import org.nice.simulation.infrastructure.event.Event;
import org.nice.simulation.reader.ModelReader;
import org.nice.simulation.registry.Registry;

import com.google.common.collect.Range;
import com.nice.dcm.scheduler.simulator.AgentContactTypeMatchDetails;
import com.nice.dcm.scheduler.simulator.ValidationSimulationComparator;
import com.nice.dcm.scheduler.simulator.ValidationSimulator;
import com.nice.dcm.scheduler.simulator.analyze.metrics.MaxIntervalQueueSize;
import com.nice.dcm.scheduler.simulator.distribution.AgentSimulationInput;
import com.nice.dcm.scheduler.simulator.distribution.ContactSimulationInput;
import com.nice.dcm.scheduler.simulator.distribution.ForecastEntry;
import com.nice.dcm.scheduler.simulator.exception.ValidatorSimulationException;
import com.nice.dcm.scheduler.simulator.input.ShiftRangesWithSkills;
import com.nice.saas.dcm.simulator.access.actions.AbortContainerActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.EWTSupportActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.InvalidateEWTCacheActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.MoveAgentToRelevantAvailableQueuesProcessor;
import com.nice.saas.dcm.simulator.access.actions.NotifySkillQueueProcessor;
import com.nice.saas.dcm.simulator.access.actions.PopulateAvailableAgentsProcessor;
import com.nice.saas.dcm.simulator.access.actions.RecordMatchActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.RecordTimeOnForAllSkillsProcessor;
import com.nice.saas.dcm.simulator.access.actions.RegisterNewSubQueueActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.RemoveAvailableAgentsProcessor;
import com.nice.saas.dcm.simulator.access.actions.UpdateEWTActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.UpdateRunTimeMetricActionProcessor;
import com.nice.saas.dcm.simulator.access.actions.distribute.DistributionRuleRunnerProcessor;
import com.nice.saas.dcm.simulator.access.actions.distribute.RoutingRuleActionProviderImpl;
import com.nice.saas.dcm.simulator.access.actions.distribute.ScriptManagerImpl;
import com.nice.saas.dcm.simulator.access.adaptable.AdaptableContext;
import com.nice.saas.dcm.simulator.access.adaptable.AdaptableRegistry;
import com.nice.saas.dcm.simulator.access.adaptable.AdaptableSimulatorRunner;

public class MainYamlPaser {

	public static void main(String[] args) {
		
		AdaptableContext context = new AdaptableSimulatorRunner().getContext();

		prepareContext(context);
		try {
			InputStream inputStream = ValidationSimulator.class.getClassLoader()
					.getResourceAsStream(ValidationSimulator.MODEL_PATH_WITH_DISTRIBUTION_RULES);
			ModelReader.readFromInputStream(context.getRegistry(), context.getEntityBuilder(), inputStream,
					context.getLoaderMap());

			Registry registry = context.getRegistry();
			
			Optional<Event<Object>> event = registry.eventQueue().pop();
			while (event.isPresent()) {
				event = registry.eventQueue().pop();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidatorSimulationException("ValidationSimulator Adapter In Mapper error", e);
		}
	}

	private static void prepareContext(AdaptableContext context) {
		 registerDistribution(context);
		 registerValues(context.getRegistry());
		 registerExternalAction(context);
		 registerComparator(context);
	}
	
	private static void registerDistribution(AdaptableContext context) {
		List<Map<String, Object>> agentSimulationInputList = getSimulationInputList();
		context.getRegistry().add("agentMultiDistribution", new MultiDistribution(agentSimulationInputList));

		List<Map<String, Object>> contactSimulationInputList = getForecaseList();
		context.getRegistry().add("contactMultiDistributionExternal",
				new MultiDistribution(contactSimulationInputList));
	}

	private static void registerComparator(AdaptableContext context) {
		Registry registry = context.getRegistry();
        registry.add("skillLevelComparator", new ValidationSimulationComparator(List.of("asc level", "asc skill")));
		registry.add("availableContactsComparator", new ValidationSimulationComparator(List.of("asc contact.priority", "asc entityId")));
		registry.add("availableAgentsComparator", new ValidationSimulationComparator(List.of("asc unavailabilitySinceLogin", "asc agent.skillInterval.skills[<queue:attribute:queueSkillId>].level")));
	}
	
	private static void registerExternalAction(AdaptableContext context) {
		Registry registry = context.getRegistry();
        registry.addExternalActionProcessor("populateAvailableAgents", new PopulateAvailableAgentsProcessor(registry));
        
        DistributionRuleRunnerProcessor distributionRuleRunnerProcessor =
        		new DistributionRuleRunnerProcessor(new ScriptManagerImpl(Collections.emptyMap()),
                        new RoutingRuleActionProviderImpl((AdaptableRegistry)registry), registry.eventQueue(),
                        LocalDateTime.now());
        		
        registry.addExternalActionProcessor("distributeContact", distributionRuleRunnerProcessor);
        registry.addExternalActionProcessor("registerNewSubQueueAction",
                new RegisterNewSubQueueActionProcessor(registry, context.getParseItManager()));
        registry.addExternalActionProcessor("moveAgentToRelevantAvailableQueues",
                new MoveAgentToRelevantAvailableQueuesProcessor(registry));
        
        registry.addExternalActionProcessor("populateAvailableAgents", new PopulateAvailableAgentsProcessor(registry));
        registry.addExternalActionProcessor("notifySkillQueue", new NotifySkillQueueProcessor((AdaptableRegistry)registry));
        registry.addExternalActionProcessor("removeAvailableAgents",
                new RemoveAvailableAgentsProcessor(registry));
        registry.addExternalActionProcessor("recordTimeOnForAllSkills",
                new RecordTimeOnForAllSkillsProcessor(registry));
        registry.addExternalActionProcessor("abort", new AbortContainerActionProcessor(registry));
        registry.addExternalActionProcessor("updateRunTimeMetricAction", new UpdateRunTimeMetricActionProcessor((AdaptableRegistry)registry));
       
        
        //registry.addFilterFunction("skills2ContactNames",
        //        new SkillLevelFilterFunction(registry, "availableContactsQueue", simulatorInput.getAcdParameters().getSkillLevels()));

        registry.addExternalActionProcessor("registerEWTAction", new EWTSupportActionProcessor((AdaptableRegistry)registry, false));
        
        registry.addExternalActionProcessor("updateEWT", new UpdateEWTActionProcessor((AdaptableRegistry)registry, false));
        registry.addExternalActionProcessor("invalidateEWTCache", new InvalidateEWTCacheActionProcessor((AdaptableRegistry)registry, false));
        registry.addExternalActionProcessor("recordMatch", new RecordMatchActionProcessor(registry, 900, AgentContactTypeMatchDetails::new));

        registry.addExternalActionProcessor("registerMaxIntervalQueueSize", new MaxIntervalQueueSize(registry));        
	}
	
    private static void registerValues(Registry registry) {
        registry.addValue("skillsFilterField", "agent.skillInterval.skillsListView");
        registry.addValue("intervalInMinutes", 900);
    }
    
	private static List<Map<String, Object>> getSimulationInputList() {
		Map<Long, List<ShiftRangesWithSkills>> employeesRangeWithSkillsList = new HashMap<>();
		employeesRangeWithSkillsList.put(1L, List.of(new ShiftRangesWithSkills(0, 100, Collections.emptyList())));
		employeesRangeWithSkillsList.put(2L, List.of(new ShiftRangesWithSkills(0, 100, Collections.emptyList())));
		employeesRangeWithSkillsList.put(3L, List.of(new ShiftRangesWithSkills(0, 100, Collections.emptyList())));
		employeesRangeWithSkillsList.put(4L, List.of(new ShiftRangesWithSkills(0, 100, Collections.emptyList())));

		long intervalLengthInSeconds = 900;
		Map<String, Integer> skillOidToLevels = new HashMap<>();

		Map<Long, Map<String, Integer>> employeesSkillOidToLevels = new HashMap<>();
		employeesSkillOidToLevels.put(1L, skillOidToLevels);
		employeesSkillOidToLevels.put(2L, skillOidToLevels);
		employeesSkillOidToLevels.put(3L, skillOidToLevels);
		employeesSkillOidToLevels.put(4L, skillOidToLevels);
		AgentSimulationInput agentSimulationInput = new AgentSimulationInput(intervalLengthInSeconds,
				employeesRangeWithSkillsList, employeesSkillOidToLevels);

		List<Map<String, Object>> agentSimulationInputList = agentSimulationInput.getSimulationInputList();
		return agentSimulationInputList;
	}

	private static List<Map<String, Object>> getForecaseList() {
		LocalDateTime scheduleStartTime = LocalDateTime.now();
		List<ForecastEntry> forecastEntryList = new ArrayList<>();
		forecastEntryList.add(ForecastEntry.builder()
				.contactTypeOid("")
				.startDateTimeUTC(scheduleStartTime)
				.intervalInSeconds(100)
				.offeredCalls(10)
				.build());
		Range<LocalDateTime> schedulePeriodInUTCRange = Range.closed(scheduleStartTime, scheduleStartTime.plusDays(1));

		ContactSimulationInput contactSimulationInput = new ContactSimulationInput(forecastEntryList, scheduleStartTime,
				schedulePeriodInUTCRange);
		return contactSimulationInput.getContactSimulationInputList();
	}
}
