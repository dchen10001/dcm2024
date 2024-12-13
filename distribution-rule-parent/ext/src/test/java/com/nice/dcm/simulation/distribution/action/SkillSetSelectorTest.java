package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

class SkillSetSelectorTest {
	@Test
	void testEvaluate() {
		List<SkillSelector> selectors = List.of(new SkillSelectorImpl("oid1", new SkillLevelConditionImpl(1)), new SkillSelectorImpl("oid2", null));
		
		SkillSetSelector selector = new SkillSetSelectorImpl(selectors);
		assertTrue(selector.evaluate(Map.of("oid1", 1, "oid2", 2)));
		assertTrue(selector.evaluate(Map.of("oid1", 1, "oid2", 3)));
		assertTrue(selector.evaluate(Map.of("oid1", 1, "oid2", 2, "oid3", 3)));
		assertFalse(selector.evaluate(Map.of("oid1", 2, "oid2", 3)));
		assertFalse(selector.evaluate(Map.of("oid1", 2, "oid3", 3)));
		
		assertFalse(selector.evaluate(Map.of("oid4", 2, "oid3", 3)));
		assertFalse(selector.evaluate(Map.of()));
		assertFalse(selector.evaluate(null));
	}
	
	
	class SkillSetSelectorImpl implements SkillSetSelector {
		private List<SkillSelector> selectors;

		public SkillSetSelectorImpl(List<SkillSelector> selectors) {
			this.selectors = selectors;
		}

		@Override
		public List<SkillSelector> getSkillSelectors() {
			return selectors;
		}
	}
	
	class SkillSelectorImpl implements SkillSelector {
        private String skillOid;
        private SkillLevelCondition condition;
        
		public SkillSelectorImpl(String skillOid, SkillLevelCondition condition) {
			this.skillOid = skillOid;
			this.condition = condition;
		}
		
		@Override
		public int compareTo(SkillSelector o) {
			return 0;
		}

		@Override
		public String getSkillOid() {
			return skillOid;
		}

		@Override
		public SkillLevelCondition getCondition() {
			return condition;
		}
		
	}
	
	class SkillLevelConditionImpl implements SkillLevelCondition {
		private int level;

		public SkillLevelConditionImpl(int level) {
			this.level = level;
		}
		
		@Override
		public boolean evaluate(int level) {
			return this.level == level;
		}

		@Override
		public int compareTo(SkillLevelCondition o) {
			return 0;
		}
	}	
}
