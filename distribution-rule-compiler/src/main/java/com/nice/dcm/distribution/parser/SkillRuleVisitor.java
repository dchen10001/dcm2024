package com.nice.dcm.distribution.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.AndSkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.OrderContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingRuleSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RoutingWaitingRuleGroupContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.ActionRule.ActionType;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.Node;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.RoutingRule;
import com.nice.dcm.distribution.parser.rule.RoutingRuleGroup;
import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

public class SkillRuleVisitor implements DistributionRulesVisitor<Node> {

	@Override
	public Node visit(ParseTree tree) {
		return null;
	}

	@Override
	public Node visitChildren(RuleNode node) {
		return null;
	}

	@Override
	public Node visitTerminal(TerminalNode node) {
		return null;
	}

	@Override
	public Node visitErrorNode(ErrorNode node) {
		return null;
	}

	@Override
	public Node visitRoutingRuleSet(RoutingRuleSetContext ctx) {
		Set<RoutingRuleGroup> ruleGroups = new TreeSet<>();
		//add first rule group
		ruleGroups.add((RoutingRuleGroup)visitRoutingRuleGroup(ctx.routingRuleGroup()));
		
		for(RoutingWaitingRuleGroupContext r : ctx.routingWaitingRuleGroup()) {
			ruleGroups.add((RoutingRuleGroup)visitRoutingWaitingRuleGroup(r));
		}
		return new RoutingRuleSet(ruleGroups);
	}


	@Override
	public Node visitRoutingWaitingRuleGroup(RoutingWaitingRuleGroupContext ctx) {
		WaitRule waitRule = (WaitRule)visitWaitRule(ctx.waitRule());
		RoutingRuleGroup rule = (RoutingRuleGroup)visitRoutingRuleGroup(ctx.routingRuleGroup());
		rule.setWaitAfterSeconds(waitRule.getWaitFor());
		return rule;
	}
	
	@Override
	public Node visitRoutingRuleGroup(RoutingRuleGroupContext ctx) {
		Map<Set<String>, Integer> duplicatedCheck = new HashMap<>();
		Set<RoutingRule> rules = new TreeSet<>();
		
		for(RoutingRuleContext r : ctx.routingRule()) {
			RoutingRule rule = (RoutingRule)visitRoutingRule(r);
			
			Integer lastPri = duplicatedCheck.put(rule.getSkills(), rule.getPriority());
			if(lastPri == null) {
				rules.add(rule);
			}
			
			if(lastPri != null && lastPri.intValue() !=  rule.getPriority()) {
				Token token = r.getStart();
				throw new ParseCancellationException("line " + 
						token.getLine() + ":" + token.getCharPositionInLine() 
						+ ". Duplicated rule with different priority. One is " 
						+ lastPri + " and one is " + rule.getPriority());
			}
		}
		return new RoutingRuleGroup(rules);
	}

	@Override
	public Node visitRoutingRule(RoutingRuleContext ctx) {
		ActionRule action = (ActionRule)visitRuleAction(ctx.ruleAction());
		AndSkillsRule skill = (AndSkillsRule)visitAndSkills(ctx.andSkills());
		OrderRule order = (OrderRule)visitOrder(ctx.order());
		Set<String> skillOids = skill.getSkills()
			.stream()
			.map(SkillRule::getSkillOid)
			.collect(Collectors.toCollection(() -> new TreeSet<>()));
		return new RoutingRule(action, skillOids, order.getPriority());
	}
	
	@Override
	public Node visitRuleAction(RuleActionContext ctx) {
		String action = ctx.getText();
		if("queue to".equals(action)) {
			return new ActionRule(ActionType.QUEUE_TO);
		}
		return null;
	}
	
	@Override
	public Node visitSkill(SkillContext ctx) {
		OidRule oidRule = (OidRule)visitEntity_identifier(ctx.entity_identifier());
		return new SkillRule(oidRule.getOid());
	}

	@Override
	public Node visitEntity_identifier(Entity_identifierContext ctx) {
		TerminalNode oidNode = ctx.NUMBER();
		if(oidNode == null) {
			oidNode = ctx.UUID_OR_HEXA();			
		}
		return new OidRule(oidNode.getText());
	}

	@Override
	public Node visitWaitRule(WaitRuleContext ctx) {
		return new WaitRule(toNumber(ctx.NUMBER()));
	}
	
	@Override
	public Node visitOrder(OrderContext ctx) {
		return new OrderRule(toNumber(ctx.NUMBER()));
	}

	private int toNumber(TerminalNode node) {
		return Integer.parseInt(node.getText());
	}

	@Override
	public Node visitAndSkills(AndSkillsContext ctx) {
		Set<SkillRule> skills = ctx.skill().stream().map(s -> (SkillRule)visitSkill(s)).collect(Collectors.toSet());
		return new AndSkillsRule(skills);
	}
}
