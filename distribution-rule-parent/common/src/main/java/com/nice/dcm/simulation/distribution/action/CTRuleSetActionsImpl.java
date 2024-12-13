package com.nice.dcm.simulation.distribution.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class CTRuleSetActionsImpl implements CTRuleSetActions {
	
	private final Map<String, Set<String>> ctInvalidSkills;

	private final List<SkillQueueSelector> skillQueueSelectors;

	private final Map<String, QueueToGroupSetAction> queueToGroupSetActions;
	
	private final Map<String, String> ctErrorMap;

	public CTRuleSetActionsImpl(@NonNull Map<String, QueueToGroupSetAction> queueToGroupSetActions,
			@NonNull List<SkillQueueSelector> skillQueueSelectors, 
			Map<String, Set<String>> ctInvalidSkills, 
			Map<String, String> ctErrorMap) {
		this.queueToGroupSetActions = queueToGroupSetActions;
		this.skillQueueSelectors = skillQueueSelectors;
		this.ctInvalidSkills = ctInvalidSkills == null ? Map.of() : ctInvalidSkills;
		this.ctErrorMap = ctErrorMap == null ? Map.of() : ctErrorMap;
	}
	
	@Override
	public QueueToGroupSetAction getQueueToGroupSetAction(String contactTypeOid) {
		return queueToGroupSetActions.get(contactTypeOid);
	}

	public List<String> getContactTypes() {
		return List.copyOf(queueToGroupSetActions.keySet());
	}
	
	public List<String> getErrorContactTypes() {
		return List.copyOf(ctErrorMap.keySet());
	}
	
	public List<String> getInvalidSkillsContactTypes() {
		return List.copyOf(ctInvalidSkills.keySet());
	}
	
	@Override
	public Set<String> getInvalidSkills(String contactTypeOid) {
		return ctInvalidSkills.get(contactTypeOid);
	}

	@Override
	public String getCTError(String contactTypeOid) {
		return ctErrorMap.get(contactTypeOid);
	}
}
