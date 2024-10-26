package com.nice.dcm.distribution.parser.engine;

import java.util.List;
import java.util.Set;

import com.nice.dcm.distribution.parser.AbstractSkillRuleVisitorImpl;
import com.nice.dcm.distribution.parser.DistributionRulesParser;
import com.nice.dcm.distribution.parser.DistributionRulesParser.AndSkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillOrSetContext;
import com.nice.dcm.distribution.parser.ParserCache;
import com.nice.dcm.distribution.parser.ParserCache.ParserWrapper;
import com.nice.dcm.distribution.parser.engine.result.AgentOidNode;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.ComparableOidSet;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.SkillSetRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

public class SkillRuleRuntimeVisitorImpl extends AbstractSkillRuleVisitorImpl {

	private final String contactTypeOid;
	private final int arrivalTime;
	private final int waitTime;
	private final ACDRoutingService service;

	public SkillRuleRuntimeVisitorImpl(String contactTypeOid, int arrivalTime, int waitTime, ACDRoutingService service) {
		super();
		this.contactTypeOid = contactTypeOid;
		this.waitTime = waitTime;
		this.arrivalTime = arrivalTime;
		this.service = service;
	}

	public String getAgentOid() {
		ParserWrapper parserWrapper = getParser();
		try {
			AgentOidNode rule = (AgentOidNode) parserWrapper.getParser().routingRuleSet().accept(this);
			return rule.getAgentOid();
		} finally {
			releaseParser(parserWrapper);
		}
	}

	private void releaseParser(ParserWrapper parserWrapper) {
		ParserCache.getInstance().releaseParser(parserWrapper);
	}
	
	private ParserWrapper getParser() {
		return ParserCache.getInstance().getParser(contactTypeOid);
	}

	@Override
	public AgentOidNode visitRoutingRuleSet(RoutingRuleSetContext ctx) {
		AgentOidNode agentOidNode = visitRoutingRuleGroup(ctx.routingRuleGroup());
		
		if (agentOidNode.hasAgentOid()) {
			return agentOidNode;
		}
		
		int totalTime = 0;
		for (RoutingWaitingRuleGroupContext r : ctx.routingWaitingRuleGroup()) {
			WaitRule waitRule = visitWaitRule(r.waitRule());
			totalTime += waitRule.getWaitFor();
			if (totalTime > waitTime) {
				break;
			}
			AgentOidNode node = visitRoutingWaitingRuleGroup(r);
			if (node.getPriority() < agentOidNode.getPriority()) {
				agentOidNode = node;
			}
		}
		return agentOidNode;
	}

	@Override
	public AgentOidNode visitRoutingRuleGroup(RoutingRuleGroupContext ctx) {
		List<RoutingRuleContext> rules = ctx.routingRule();
		AgentOidNode agentOidNode = null;
		for (RoutingRuleContext rule : rules) {
			AgentOidNode node = visitRoutingRule(rule);
			if (agentOidNode == null || node.getPriority() < agentOidNode.getPriority()) {
				agentOidNode = node;
			}
		}
		return agentOidNode;
	}

	@Override
	public AgentOidNode visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
		return visitRoutingRuleGroup(ctx.routingRuleGroup());
	}

	@Override
	public AgentOidNode visitRoutingRule(RoutingRuleContext ctx) {
        Set<ComparableOidSet> skills = visitAndSkills(ctx.andSkills()).getSkills();
        String agentOid = null;
        
        if (ctx.queue_status() != null) {
        	ComparableOidSet skill = this.service.getLeastBusyQueue(skills, arrivalTime, waitTime);
        	agentOid = this.service.findAgent(skill);
		} else {
			agentOid = this.service.findAgent(skills);
		}
		if (agentOid == null) {
			return new AgentOidNode();
		}
		
        OrderRule order = visitOrder(ctx.order());
		return new AgentOidNode(agentOid, order.getPriority());
	}

    @Override
    public AndSkillsRule visitAndSkills(AndSkillsContext ctx) {
        List<SkillOrSetContext> skills = ctx.skillOrSet();
        List<ComparableOidSet> skillSet = skills.stream().map(this::getOidSet).toList();
        return new AndSkillsRule(skillSet);
    }     
    
    protected ComparableOidSet getOidSet(SkillOrSetContext ctx) {
        SkillSetRule node = visitSkillOrSet(ctx);
        return node.getSkillSetKey();
    }        
}