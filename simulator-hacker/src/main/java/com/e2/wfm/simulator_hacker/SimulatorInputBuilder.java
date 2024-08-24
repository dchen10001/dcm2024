package com.e2.wfm.simulator_hacker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.nice.dcm.scheduler.core.ScheduleTimeFrame;
import com.nice.dcm.scheduler.core.ServiceTargets;
import com.nice.dcm.scheduler.core.candidate.ScheduleCandidate;
import com.nice.dcm.scheduler.core.common.time.WeekContext;
import com.nice.dcm.scheduler.core.contacttype.ContactType;
import com.nice.dcm.scheduler.core.contacttype.ContactTypeInterval;
import com.nice.dcm.scheduler.core.contacttype.DistributionRule;
import com.nice.dcm.scheduler.core.simulator.acd.ACDParameters;
import com.nice.dcm.scheduler.core.simulator.acd.SkillLevels;
import com.nice.dcm.scheduler.cxone.model.CXOneContactType;
import com.nice.dcm.scheduler.cxone.model.CXOneModel;
import com.nice.dcm.scheduler.cxone.model.Employee;
import com.nice.dcm.scheduler.cxone.model.EmployeeSkill;
import com.nice.dcm.scheduler.cxone.model.IdName;
import com.nice.dcm.scheduler.simulator.distribution.ForecastEntry;
import com.nice.dcm.scheduler.simulator.input.ASAParameters;
import com.nice.dcm.scheduler.simulator.input.ASAThresholdData;
import com.nice.dcm.scheduler.simulator.input.ServiceLevelParameters;
import com.nice.dcm.scheduler.simulator.input.ServiceLevelThresholdData;
import com.nice.dcm.scheduler.simulator.input.ShiftRangesWithSkills;
import com.nice.dcm.scheduler.simulator.input.SimulatorInput;
import com.nice.dcm.scheduler.simulator.input.ValidationSimulatorAdapterInMapper;
import com.nice.saas.dcm.simulator.access.actions.distribute.CtScript;
import com.nice.saas.dcm.simulator.access.actions.distribute.CtScripts;

public class SimulatorInputBuilder {
	
	public static SimulatorInput createSimulatorInput(CXOneModel model, WeekContext weekContext,
			Collection<ScheduleCandidate> scheduleCandidates)
	{
		Map<Long, String> skillIdToOidMap = model.getSkills().stream()
				.collect(Collectors.toMap(IdName::getId, IdName::getOid));

        Duration avgCTAbandonedTime = getAbandonedTime(
        		model.getScheduleParameters().getDefaultAbandonedTime(),
        		model.getScheduleParameters().getMinimalAbandonedTime(),
        		model.getScheduleParameters().getDefaultAbandonedTime());
        
		return createSimulatorInput(model.getScheduleTimeFrame(), 
				weekContext, 
				model.getContactTypes(),
				model.getEmployees(), 
				avgCTAbandonedTime,
				scheduleCandidates,
				skillIdToOidMap);
	}
	
	public static SimulatorInput createSimulatorInput(
			ScheduleTimeFrame requestedWindow, 
			WeekContext weekContext,
			List<CXOneContactType> cxoneContactTypes, 
			List<Employee> employees, 
			//Map<String, ASAThresholdData> ctIdToIntervalsASAThresholdDataMap, 
			Duration avgCTAbandonedTime,
			Collection<ScheduleCandidate> scheduleCandidates,
			Map<Long, String> skillIdToOidMap) {

		int intervalInSeconds = Math.toIntExact(requestedWindow.getIntervalLength().toSeconds());
		LocalDateTime scheduleStartTimeInUTC = requestedWindow.getStartDate().atStartOfDay(ZoneId.of("UTC"))
				.toLocalDateTime();
		LocalDateTime scheduleEndTimeInUTC = requestedWindow.getEndDate().plusDays(1).atStartOfDay(ZoneId.of("UTC"))
				.toLocalDateTime();

		Map<Long, Map<String, Integer>> employeesSkillOidToLevels = extractEmployeesSkillOidToLevel(employees,
				skillIdToOidMap);

		Map<Long, List<ShiftRangesWithSkills>> employeesSchedulesRanges = new ValidationSimulatorAdapterInMapper(
				employeesSkillOidToLevels, skillIdToOidMap).map(Map.of(weekContext, scheduleCandidates));

		ACDParameters acdParameters = getAcdParameters(null);

        Map<String, ServiceLevelThresholdData> ctIdToIntervalsServiceLevelThresholdDataMap = cxoneContactTypes.stream()
                .filter(ct -> ct.getServiceTargets().isServiceLevel())
                .collect(Collectors.toMap(
						IdName::getOid,
                		ct -> new ServiceLevelThresholdData(
                                new ServiceLevelParameters(ct.getServiceTargets().getServiceLevelPercentage(),
                                		ct.getServiceTargets().getServiceLevelDuration())
                        )));
        
        Map<String, ASAThresholdData> ctIdToIntervalsASAThresholdDataMap = cxoneContactTypes.stream()
                .filter(ct -> ct.getServiceTargets().isAvgSpeedOfAnswer())
                .collect(Collectors.toMap(
						IdName::getOid,
                        ct -> new ASAThresholdData(
                                new ASAParameters(ct.getServiceTargets().getAvgSpeedOfAnswerDuration())
                        )));
        
        List<ContactType> contactTypes = createCXOneContactTypes((int)requestedWindow.getIntervalLength().toSeconds(), cxoneContactTypes, avgCTAbandonedTime);
        
		return SimulatorInput.builder().acdParameters(acdParameters).employeesSchedulesRanges(employeesSchedulesRanges)
				.intervalInSeconds(intervalInSeconds).scheduleStartTimeInUTC(scheduleStartTimeInUTC)
				.scheduleEndTimeInUTC(scheduleEndTimeInUTC).employeesSkillOidToLevels(employeesSkillOidToLevels)
				.forecastEntryList(toForecastEntryList(contactTypes, intervalInSeconds))
				.ctOidToCtScripts(getCtOidToCtScripts(contactTypes))
				.ctIdToIntervalsServiceLevelThresholdDataMap(ctIdToIntervalsServiceLevelThresholdDataMap)
				.ctIdToIntervalsASAThresholdDataMap(ctIdToIntervalsASAThresholdDataMap).build();
	}

    private static Duration getAbandonedTime(Duration inputAbandonedTime, Duration minimalAbandonedTime, Duration defaultAbandonedTime) {
        return inputAbandonedTime.toSeconds() < minimalAbandonedTime.toSeconds() ? defaultAbandonedTime : inputAbandonedTime;
    }
    
    private static List<ContactType> createCXOneContactTypes(int interval, List<CXOneContactType> cxOneContactTypes, Duration avgCTAbandonedTime) {
        List<ContactType> contactTypes = new ArrayList<>();
        cxOneContactTypes.forEach(ct -> {
            List<DistributionRule> distributionRulesToUse = CollectionUtils.isNotEmpty(ct.getDistributionRules()) ?
                    ct.getDistributionRules().stream().map(r -> createRule(r)).toList() :
                    List.of(DistributionRule.defaultDistributionRule("defaultRule", String.format("queue to @S:%s with priority 1", 
                    		ct.getOid())));

            contactTypes.add(new ContactType(
            		ct.getId(),
            		ct.getOid(),
            		ct.getName(),
                    distributionRulesToUse,
                    toSkillIntervals(ct.getIntervals(), interval),
                    avgCTAbandonedTime,
                    ct.getServiceTargets()));
        });
        return contactTypes;
    }
    
    private static DistributionRule createRule(com.nice.dcm.scheduler.cxone.model.DistributionRule rule) {
        return new DistributionRule(rule.getName(),
                rule.isDefaultRule(),
                rule.getStart(),
                rule.getEnd(),
                rule.getScript());
    }

    private static List<ContactTypeInterval> toSkillIntervals(List<com.nice.dcm.scheduler.cxone.model.ContactTypeInterval> cxOneSkillsIntervals, long intervalInSeconds) {
        return cxOneSkillsIntervals.stream().map(interval -> new ContactTypeInterval(
                interval.getStartDateTime(),
                interval.getStartDateTime().plusSeconds(intervalInSeconds),
                interval.getRequirement(),
                interval.getCallVolume(),
                interval.getAvgHandleTime(),
                ServiceTargets.EMPTY_SERVICE_TARGETS)).toList();
    }
    
	private static Map<Long, Map<String, Integer>> extractEmployeesSkillOidToLevel(List<Employee> employees,
			Map<Long, String> skillIdToOidMap) {
		return employees.stream().distinct().collect(
				Collectors.toMap(Employee::getId, emp -> emp.getSkills().stream().collect(Collectors
						.toMap(empSkill -> skillIdToOidMap.get(empSkill.getSkill()), EmployeeSkill::getLevel))));
	}

	private static ACDParameters getAcdParameters(SkillLevels skillLevelsDirection) {
		return ACDParameters.builder()
				.skillLevels(skillLevelsDirection == null ? SimulatorInput.DEFAULT_ACD_PARAMETERS.getSkillLevels()
						: skillLevelsDirection)
				.agentSelection(SimulatorInput.DEFAULT_ACD_PARAMETERS.getAgentSelection())
				.contactSelection(SimulatorInput.DEFAULT_ACD_PARAMETERS.getContactSelection())
				.mostAvailableAgent(SimulatorInput.DEFAULT_ACD_PARAMETERS.getMostAvailableAgent()).build();
	}

	protected static List<ForecastEntry> toForecastEntryList(List<ContactType> contactTypes, int intervalInSeconds) {
		return contactTypes.stream()
				.map(ct -> ct.intervals().stream().map(interval -> ForecastEntry.builder().contactTypeOid(ct.oid())
						.startDateTimeUTC(interval.startDateTime().toZonedDateTime().toLocalDateTime())
						.intervalInSeconds(intervalInSeconds).offeredCalls((int) interval.callVolume())
						.avgHandleTime((interval.avgHandleTime()) == null ? 0 : interval.avgHandleTime().toSeconds())
						.avgAbandonedTime(ct.avgCTAbandonedTime().toSeconds()).build()).toList())
				.toList().stream().flatMap(List::stream).toList();
	}
	
    protected static Map<String, CtScripts> getCtOidToCtScripts(List<ContactType> contactTypes) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        return contactTypes.stream()
                .collect(Collectors.toMap(ContactType::oid,
                        ct -> {
                            Optional<CtScript> optionalDefaultCtScript = ct.distributionRules().stream()
                                    .filter(DistributionRule::isDefaultRule)
                                    .map(rule -> new CtScript(rule.getScript()))
                                    .findAny();
                            List<CtScript> ctScripts = ct.distributionRules().stream()
                                    .filter(rule -> !rule.isDefaultRule())
                                    .map(rule -> new CtScript(atomicInteger.getAndIncrement() + "",
                                            rule.getStart(),
                                            rule.getEnd(),
                                            rule.getScript())).toList();
                            return new CtScripts(ctScripts, optionalDefaultCtScript.orElse(null));
                        }
                ));
    }
}
