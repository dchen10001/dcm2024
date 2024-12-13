package com.nice.dcm.simulation.distribution.action;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;

class SkillSelectorTest {
	@Test
	void testEvaluate() {
		SkillSelector selector = new SkillSelectorImpl("oid1", new SkillLevelConditionImpl(1));
		assertTrue(selector.evaluate("oid1", 1));
		assertFalse(selector.evaluate("oid1", 2));
		assertFalse(selector.evaluate("oid2", 1));
		
		selector = new SkillSelectorImpl("oid1", null);
		assertTrue(selector.evaluate("oid1", 1));
		assertTrue(selector.evaluate("oid1", 2));
		assertFalse(selector.evaluate("oid2", 1));
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
