package com.nice.dcm.simulation.distribution.rule.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;

class SkillSelectorImplTest {
	@Test
	void testConstructor() {
		SkillSelectorImpl skillSelector = new SkillSelectorImpl("skillOid");
        assertEquals("skillOid", skillSelector.getSkillOid());
        assertNull(skillSelector.getCondition());
        
        SqlSkillLevelConditionImpl condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
        skillSelector = new SkillSelectorImpl("skillOid", condition);
        assertEquals("skillOid", skillSelector.getSkillOid());
        assertSame(condition, skillSelector.getCondition());
    
        Exception e = assertThrows(NullPointerException.class, () -> new SkillSelectorImpl(null));
        assertEquals("skillOid is marked non-null but is null", e.getMessage());
	}
	
	@Test
	void testEvaluate() {
		SkillSelectorImpl skillSelector = new SkillSelectorImpl("skillOid");
		assertTrue(skillSelector.evaluate("skillOid", 1));
		assertTrue(skillSelector.evaluate("skillOid", 2));
		assertFalse(skillSelector.evaluate("skillOid2", 1));
		
		SqlSkillLevelConditionImpl condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		skillSelector = new SkillSelectorImpl("skillOid", condition);
		assertTrue(skillSelector.evaluate("skillOid", 1));
		assertTrue(skillSelector.evaluate("skillOid", 2));
		assertFalse(skillSelector.evaluate("skillOid", 3));
	}
	
	@Test
	void testCompareTo() {
		SkillSelectorImpl skillSelector = new SkillSelectorImpl("skillOid");
		SkillSelectorImpl skillSelector2 = new SkillSelectorImpl("skillOid");
		assertEquals(0, skillSelector.compareTo(skillSelector2));
		
		SkillSelector other = new SkillSelector() {
			@Override
			public int compareTo(SkillSelector o) {
				return 0;
			}

			@Override
			public String getSkillOid() {
				return null;
			}

			@Override
			public SkillLevelCondition getCondition() {
				return null;
			}
		};
		
		assertEquals(1, skillSelector.compareTo(other));
		
		assertTrue(skillSelector.compareTo(null) > 0);

		SkillSelectorImpl skillSelector1 = new SkillSelectorImpl("skillOid1");
		assertTrue(skillSelector.compareTo(skillSelector1) < 0);

		SqlSkillLevelConditionImpl condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		SkillSelectorImpl skillSelectorCondition = new SkillSelectorImpl("skillOid", condition);
		assertTrue(skillSelector.compareTo(skillSelectorCondition) < 0);
		assertTrue(skillSelectorCondition.compareTo(skillSelector) > 0);
		
		skillSelectorCondition = new SkillSelectorImpl("skillOid1", condition);
		assertTrue(skillSelector.compareTo(skillSelectorCondition) < 0);
		assertTrue(skillSelectorCondition.compareTo(skillSelector) > 0);
	}
}
