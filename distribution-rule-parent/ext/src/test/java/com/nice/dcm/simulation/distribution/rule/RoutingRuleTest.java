package com.nice.dcm.simulation.distribution.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class RoutingRuleTest {

	@Test
	void testEvaluate() {
		RoutingRuleImpl rule = new RoutingRuleImpl(List.of(new SkillSetSelectorImpl("oid1", 1), new SkillSetSelectorImpl("oid2", 2)));
		Map<String, Integer> skillToLevels = Map.of("oid1", 1, "oid2", 2);
		
		assertTrue(rule.evaluate(skillToLevels));
		skillToLevels = Map.of("oid1", 1, "oid3", 2);
		assertTrue(rule.evaluate(skillToLevels));
		
		skillToLevels = Map.of("oid1", 2, "oid2", 2);
		assertTrue(rule.evaluate(skillToLevels));
		
		skillToLevels = Map.of("oid1", 2, "oid2", 3);
		assertFalse(rule.evaluate(skillToLevels));

		assertFalse(rule.evaluate(null));
		assertFalse(rule.evaluate(Map.of()));
	}
	
	class RoutingRuleImpl implements RoutingRule {
		List<SkillSetSelector> selectors;
		public RoutingRuleImpl(Collection<SkillSetSelector> selectors) {
			super();
			this.selectors = new ArrayList<>(selectors);
		}

		@Override
		public RuleAction getAction() {
			return null;
		}

		@Override
		public QueueStatus getQueueStatus() {
			return null;
		}

		@Override
		public List<SkillSetSelector> getSelectors() {
			return this.selectors;
		}

		@Override
		public int getPriority() {
			return 0;
		}

		@Override
		public boolean isLeastBusyOf() {
			return false;
		}
	}
	
	class SkillSetSelectorImpl implements SkillSetSelector {
		private String oid;
		private int level;
		public SkillSetSelectorImpl(String oid, int level) {
			super();
			this.oid = oid;
			this.level = level;
		}

		@Override
		public boolean evaluate(Map<String, Integer> skillToLevels) {
			return skillToLevels.containsKey(oid) && skillToLevels.get(oid) == level;
		}

		@Override
		public List<SkillSelector> getSkillSelectors() {
			return null;
		}
	}
}
