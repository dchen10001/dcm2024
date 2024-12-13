package com.nice.dcm.simulation.distribution.rule.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;

class SkillSetSelectorImplTest {
	@Test
	void testConstructor() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
		
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", condition));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
		List<SkillSelector> selectors = skillSelector.getSkillSelectors();
		assertEquals(2, selectors.size());
		assertEquals("skillOid1", selectors.get(0).getSkillOid());
		assertNull(selectors.get(0).getCondition());
		
		assertEquals("skillOid2", selectors.get(1).getSkillOid());
		assertSame(condition, selectors.get(1).getCondition());
		
		Exception e = assertThrows(NullPointerException.class, () -> new SkillSetSelectorImpl((List<SkillSelector>)null));
		assertEquals("skillSelectors is marked non-null but is null", e.getMessage());
		
		List<SkillSelector> emptyList = List.of();
		e = assertThrows(IllegalArgumentException.class, 
				() -> new SkillSetSelectorImpl(emptyList));
		assertEquals("skillSelectors is empty", e.getMessage());
	}
	
	@Test
	void testConstructorSecond() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);

		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(new SkillSelectorImpl("skillOid2", condition));
		List<SkillSelector> selectors = skillSelector.getSkillSelectors();
		assertEquals(1, selectors.size());
		assertEquals("skillOid2", selectors.get(0).getSkillOid());
		assertSame(condition, selectors.get(0).getCondition());
		
		Exception e = assertThrows(NullPointerException.class, () -> new SkillSetSelectorImpl((SkillSelector)null));
		assertEquals("skillSelector is marked non-null but is null", e.getMessage());
	}
	
	@Test
	void testConstructorDuplicatedSelector() {
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"), new SkillSelectorImpl("skillOid1"),
				new SkillSelectorImpl("skillOid2", new BinarySkillLevelConditionImpl(BinaryOperator.LESS_THAN, 4)),
				new SkillSelectorImpl("skillOid2", new BinarySkillLevelConditionImpl(BinaryOperator.GREATER_THAN, 1)),
				new SkillSelectorImpl("skillOid2", new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2)),
				new SkillSelectorImpl("skillOid2", new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2)));
		
		SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
		assertEquals(4, skillSelector.getSkillSelectors().size());
	}
	
	@Test
	void testEvaluate() {
		SkillLevelCondition condition = new SqlSkillLevelConditionImpl(SqlOperator.IN, 1, 2);
        
		List<SkillSelector> skillSelectorsList = List.of(new SkillSelectorImpl("skillOid1"),
                new SkillSelectorImpl("skillOid2", condition));
        
        SkillSetSelectorImpl skillSelector = new SkillSetSelectorImpl(skillSelectorsList);
        
        Map<String, Integer> skillToLevels = Map.of("skillOid1", 1, "skillOid2", 2);
        assertTrue(skillSelector.evaluate(skillToLevels));
        
        skillToLevels = Map.of("skillOid1", 2, "skillOid2", 2);
        assertTrue(skillSelector.evaluate(skillToLevels));
        
        skillToLevels = Map.of("skillOid1", 1, "skillOid2", 3);
        assertFalse(skillSelector.evaluate(skillToLevels));
        
        skillToLevels = Map.of("skillOid1", 1, "skillOid3", 3);
        assertFalse(skillSelector.evaluate(skillToLevels));
        
        skillToLevels = Map.of("skillOid1", 1);
        assertFalse(skillSelector.evaluate(skillToLevels));
        
        skillToLevels = Map.of();
        assertFalse(skillSelector.evaluate(skillToLevels));
	}
}
