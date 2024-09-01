// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DistributionRulesParser}.
 */
public interface DistributionRulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#routingRuleSet}.
	 * @param ctx the parse tree
	 */
	void enterRoutingRuleSet(DistributionRulesParser.RoutingRuleSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#routingRuleSet}.
	 * @param ctx the parse tree
	 */
	void exitRoutingRuleSet(DistributionRulesParser.RoutingRuleSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#routingWaitingRuleGroup}.
	 * @param ctx the parse tree
	 */
	void enterRoutingWaitingRuleGroup(DistributionRulesParser.RoutingWaitingRuleGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#routingWaitingRuleGroup}.
	 * @param ctx the parse tree
	 */
	void exitRoutingWaitingRuleGroup(DistributionRulesParser.RoutingWaitingRuleGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#routingRuleGroup}.
	 * @param ctx the parse tree
	 */
	void enterRoutingRuleGroup(DistributionRulesParser.RoutingRuleGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#routingRuleGroup}.
	 * @param ctx the parse tree
	 */
	void exitRoutingRuleGroup(DistributionRulesParser.RoutingRuleGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#routingRule}.
	 * @param ctx the parse tree
	 */
	void enterRoutingRule(DistributionRulesParser.RoutingRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#routingRule}.
	 * @param ctx the parse tree
	 */
	void exitRoutingRule(DistributionRulesParser.RoutingRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#ruleAction}.
	 * @param ctx the parse tree
	 */
	void enterRuleAction(DistributionRulesParser.RuleActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#ruleAction}.
	 * @param ctx the parse tree
	 */
	void exitRuleAction(DistributionRulesParser.RuleActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#andSkills}.
	 * @param ctx the parse tree
	 */
	void enterAndSkills(DistributionRulesParser.AndSkillsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#andSkills}.
	 * @param ctx the parse tree
	 */
	void exitAndSkills(DistributionRulesParser.AndSkillsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#skill}.
	 * @param ctx the parse tree
	 */
	void enterSkill(DistributionRulesParser.SkillContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#skill}.
	 * @param ctx the parse tree
	 */
	void exitSkill(DistributionRulesParser.SkillContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#entity_identifier}.
	 * @param ctx the parse tree
	 */
	void enterEntity_identifier(DistributionRulesParser.Entity_identifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#entity_identifier}.
	 * @param ctx the parse tree
	 */
	void exitEntity_identifier(DistributionRulesParser.Entity_identifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(DistributionRulesParser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(DistributionRulesParser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#waitRule}.
	 * @param ctx the parse tree
	 */
	void enterWaitRule(DistributionRulesParser.WaitRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#waitRule}.
	 * @param ctx the parse tree
	 */
	void exitWaitRule(DistributionRulesParser.WaitRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#agent_status}.
	 * @param ctx the parse tree
	 */
	void enterAgent_status(DistributionRulesParser.Agent_statusContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#agent_status}.
	 * @param ctx the parse tree
	 */
	void exitAgent_status(DistributionRulesParser.Agent_statusContext ctx);
}