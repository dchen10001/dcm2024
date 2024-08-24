package com.nice.dcm.distribution.rule;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SkillRuleTest {
    @Test
    void ruleTest() {
        SkillRuleImpl r1 = new SkillRuleImpl("oid1", 1);
        Assertions.assertEquals("oid1", r1.getSkillOid());
        Assertions.assertEquals(1, r1.getPriority());
        Assertions.assertEquals(0l, r1.getWaitAfterSeconds());
        
        Assertions.assertFalse(r1.apply(null, 0));
        Assertions.assertFalse(r1.apply(Set.of("oid4", "oid5"), 0));
        Assertions.assertTrue(r1.apply(Set.of("oid1", "oid5"), 10));
        
        
        SkillRuleImpl r2 = new SkillRuleImpl("oid2", 2, 5);
        Assertions.assertEquals(5l, r2.getWaitAfterSeconds());
        
        Assertions.assertFalse(r2.apply(null, 0));
        
        Assertions.assertFalse(r2.apply(Set.of("oid4", "oid5"), 6));
        
        Assertions.assertFalse(r2.apply(Set.of("oid2", "oid5"), 1));
        
        Assertions.assertTrue(r2.apply(Set.of("oid2", "oid5"), 5));
        Assertions.assertTrue(r2.apply(Set.of("oid2", "oid5"), 15));
        
        SkillRule r3 = new SkillRuleImpl("oid3", 3, 20);

        AndSkillsRuleImpl r4 = new AndSkillsRuleImpl(Set.of("oid4", "oid5"), 4);
        
        Assertions.assertEquals(Set.of("oid4", "oid5"), r4.getSkillOids());
        Assertions.assertEquals(4, r4.getPriority());
        Assertions.assertEquals(0l, r4.getWaitAfterSeconds());
        
        Assertions.assertFalse(r4.apply(null, 0));
        Assertions.assertFalse(r4.apply(Set.of("oid2", "oid5"), 1));
        
        Assertions.assertTrue(r4.apply(Set.of("oid4", "oid5"), 0));
        Assertions.assertTrue(r4.apply(Set.of("oid4", "oid5", "oid6"), 0));
        
        AndSkillsRuleImpl r5 = new AndSkillsRuleImpl(Set.of("oid3", "oid5"), 5, 20);
        
        Assertions.assertEquals(Set.of("oid3", "oid5"), r5.getSkillOids());
        Assertions.assertEquals(5, r5.getPriority());
        Assertions.assertEquals(20l, r5.getWaitAfterSeconds());
        
        Assertions.assertFalse(r5.apply(null, 0));
        Assertions.assertFalse(r5.apply(Set.of("oid2", "oid5"), 1));
        
        Assertions.assertFalse(r5.apply(Set.of("oid3", "oid5"), 0));
        Assertions.assertFalse(r5.apply(Set.of("oid3", "oid5", "oid6"), 0));
        
        Assertions.assertTrue(r5.apply(Set.of("oid3", "oid5"), 20));
        Assertions.assertTrue(r5.apply(Set.of("oid3", "oid5", "oid6"), 20));
        
        Assertions.assertTrue(r5.apply(Set.of("oid3", "oid5"), 21));
        Assertions.assertTrue(r5.apply(Set.of("oid3", "oid5", "oid6"), 21));
        
        TreeSet<SkillRule> ruleSet = new TreeSet<>();
        
        ruleSet.add(r5);
        ruleSet.add(r4);
        ruleSet.add(r3);
        ruleSet.add(r2);
        ruleSet.add(r1);
        int order = 1;
        for(SkillRule rule : ruleSet) {
            Assertions.assertEquals(order++, rule.getPriority());
        }
    }
}
