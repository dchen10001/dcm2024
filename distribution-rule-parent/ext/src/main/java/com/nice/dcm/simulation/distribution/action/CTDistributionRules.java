package com.nice.dcm.simulation.distribution.action;

import java.util.Set;

public interface CTDistributionRules {
	Set<String> getSkills();
	
	Set<String> getContactTypes();
	
	String getRuleScript(String contactTypeOid);
}
