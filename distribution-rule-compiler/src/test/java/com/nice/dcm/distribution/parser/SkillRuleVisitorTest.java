package com.nice.dcm.distribution.parser;

import org.junit.jupiter.api.Test;

import com.nice.dcm.distribution.rule.Rule;

class SkillRuleVisitorTest {
	ParserUtil util = new ParserUtil();
	SkillRuleVisitor visitor = new SkillRuleVisitor();

	@Test
	void waitingRuleTest() {
		String script = "wait 1";
	
		Rule rule = util.vistorWait(script, visitor);
	}
	
	@Test
	void oidRuleTest() {
		String script = "100a923";
		
		Rule rule = util.vistorOid(script, visitor);
	}
}
