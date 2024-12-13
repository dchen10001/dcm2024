package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CTDistributionRulesImplTest {
	@Test
	void testCreateCTDistributionRulesImpl() {
		Set<String> skills = Set.of("skill1", "skill2");
		Map<String, String> ruleScripts = Map.of("oid1", "script1", "oid2", "script2");
		CTDistributionRules ctDistributionRules = new CTDistributionRulesImpl(skills, ruleScripts);
		assertSame(skills, ctDistributionRules.getSkills());
		assertEquals(ruleScripts.keySet(), ctDistributionRules.getContactTypes());
		for (String oid : ruleScripts.keySet()) {
			assertEquals(ruleScripts.get(oid), ctDistributionRules.getRuleScript(oid));
		}
		
		// Test exception
		Set<String> emptySkills = Set.of();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new CTDistributionRulesImpl(emptySkills, ruleScripts);
		});
		
		Map<String, String> emptyRuleScripts = Map.of();
		assertEquals("skills and ruleScripts must not be empty", exception.getMessage());
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new CTDistributionRulesImpl(skills, emptyRuleScripts);
		});
		assertEquals("skills and ruleScripts must not be empty", exception.getMessage());
		
		// Test null
		exception = assertThrows(NullPointerException.class, () -> {
			new CTDistributionRulesImpl(null, ruleScripts);
		});
		assertEquals("skills is marked non-null but is null", exception.getMessage());
		exception = assertThrows(NullPointerException.class, () -> {
			new CTDistributionRulesImpl(skills, null);
		});
		assertEquals("ruleScripts is marked non-null but is null", exception.getMessage());
	}
}
