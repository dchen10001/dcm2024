package com.nice.dcm.distribution.parser.engine;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nice.dcm.distribution.parser.ParserCache;

public class SkillRuleRuntimeVisitorImplTest {
	@Test
	void routingRuleSetSimpleTest() {
		String script = """
		                queue to @S: a2 with priority 3
						queue to @S: a1 with priority 3
					    wait 20
					    queue to @S: a4 with priority 2
					    wait 10
					    queue to @S: a3 with priority 1    
		            """;
		
		ParserCache parserCache = ParserCache.getInstance();
		String contactTypeOid = "contact1";
		parserCache.addParser(contactTypeOid, script);
		
		ACDRoutingServiceImpl service = new ACDRoutingServiceImpl(List.of());
		
		SkillRuleRuntimeVisitorImpl visitor = new SkillRuleRuntimeVisitorImpl(contactTypeOid, 10, 10, service);
		String agentOid = visitor.getAgentOid();
		Assertions.assertNull(agentOid);
		
		
		List<Agent> agents = List.of(
				new Agent("a1", List.of(new AgentSkill("a1", 1), new AgentSkill("a2", 1))), 
				new Agent("a2", List.of(new AgentSkill("a3", 1))), 
				new Agent("a3", List.of(new AgentSkill("a4", 1))), 
				new Agent("a4", List.of(new AgentSkill("a5", 1))));
		
		service = new ACDRoutingServiceImpl(agents);
		visitor = new SkillRuleRuntimeVisitorImpl(contactTypeOid, 10, 0, service);
		agentOid = visitor.getAgentOid();
		Assertions.assertEquals("a1", agentOid);

		visitor = new SkillRuleRuntimeVisitorImpl(contactTypeOid, 10, 20, service);
		agentOid = visitor.getAgentOid();
		Assertions.assertEquals("a1", agentOid);

		visitor = new SkillRuleRuntimeVisitorImpl(contactTypeOid, 10, 30, service);
		agentOid = visitor.getAgentOid();
		Assertions.assertEquals("a1", agentOid);
	}
	
	@Test
	void routingRuleSetTest() {
		String script = """
		                queue to @S: a2 level < 2 with priority 2
						queue to @S: a1 level < 2 with priority 1
					    wait 20
					    queue to @S: a4 with priority 4
					    wait 10
					    queue to @S: a3 with priority 3    
		            """;
		
		ParserCache parserCache = ParserCache.getInstance();
		String contactTypeOid = "contact1";
		parserCache.addParser(contactTypeOid, script);
		
		List<Agent> agents = List.of(
				new Agent("a1", List.of(new AgentSkill("a1", 4), new AgentSkill("a2", 3))), 
				new Agent("a2", List.of(new AgentSkill("a1", 1))), 
				new Agent("a3", List.of(new AgentSkill("a4", 1))), 
				new Agent("a4", List.of(new AgentSkill("a5", 1))));
		
		ACDRoutingServiceImpl service = new ACDRoutingServiceImpl(agents);
		
		SkillRuleRuntimeVisitorImpl visitor = new SkillRuleRuntimeVisitorImpl(contactTypeOid, 10, 10, service);
		String agentOid = visitor.getAgentOid();
		Assertions.assertNull(agentOid);
	}
}
