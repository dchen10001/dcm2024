package com.nice.dcm.simulation.distribution.action;

import java.util.Map;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class CTDistributionRulesImpl implements CTDistributionRules {
	
	private final Set<String> skills;
	private final Map<String, String> ruleScripts;

	public CTDistributionRulesImpl(@NonNull Set<String> skills, @NonNull Map<String, String> ruleScripts) {
		if (skills.isEmpty() || ruleScripts.isEmpty()) {
			throw new IllegalArgumentException("skills and ruleScripts must not be empty");
		}
		
		this.skills = skills;
		this.ruleScripts = ruleScripts;
	}
	
	@Override
	public Set<String> getContactTypes() {
		return ruleScripts.keySet();
	}

	@Override
	public String getRuleScript(String contactTypeOid) {
		return ruleScripts.get(contactTypeOid);
	}
}
