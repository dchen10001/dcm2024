package com.nice.dcm.distribution.parser;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.OrderContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RulesContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.rule.OidRule;
import com.nice.dcm.distribution.rule.OrderRule;
import com.nice.dcm.distribution.rule.Rule;
import com.nice.dcm.distribution.rule.WaitRule;

public class SkillRuleVisitor implements DistributionRulesVisitor<Rule> {

	@Override
	public Rule visit(ParseTree tree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitChildren(RuleNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitRules(RulesContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitRule(RuleContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitRuleAction(RuleActionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitSkills(SkillsContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitSkill(SkillContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule visitEntity_identifier(Entity_identifierContext ctx) {
		TerminalNode oidNode = ctx.NUMBER();
		if(oidNode == null) {
			oidNode = ctx.UUID_OR_HEXA();			
		}
		return new OidRule(oidNode.getText());
	}

	@Override
	public Rule visitOrder(OrderContext ctx) {
		return new OrderRule(toNumber(ctx.NUMBER()));
	}

	@Override
	public Rule visitWaitRule(WaitRuleContext ctx) {
		return new WaitRule(toNumber(ctx.NUMBER()));
	}

	private int toNumber(TerminalNode node) {
		try {
			return Integer.parseInt(node.getText());
		} catch(Exception e) {
			return 0;
		}
	}
}
