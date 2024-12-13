// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.rule.parser;
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
	 * Enter a parse tree produced by {@link DistributionRulesParser#orSkills}.
	 * @param ctx the parse tree
	 */
	void enterOrSkills(DistributionRulesParser.OrSkillsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#orSkills}.
	 * @param ctx the parse tree
	 */
	void exitOrSkills(DistributionRulesParser.OrSkillsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#skillOrSet}.
	 * @param ctx the parse tree
	 */
	void enterSkillOrSet(DistributionRulesParser.SkillOrSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#skillOrSet}.
	 * @param ctx the parse tree
	 */
	void exitSkillOrSet(DistributionRulesParser.SkillOrSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#skillSet}.
	 * @param ctx the parse tree
	 */
	void enterSkillSet(DistributionRulesParser.SkillSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#skillSet}.
	 * @param ctx the parse tree
	 */
	void exitSkillSet(DistributionRulesParser.SkillSetContext ctx);
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
	 * Enter a parse tree produced by {@link DistributionRulesParser#levelCondition}.
	 * @param ctx the parse tree
	 */
	void enterLevelCondition(DistributionRulesParser.LevelConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#levelCondition}.
	 * @param ctx the parse tree
	 */
	void exitLevelCondition(DistributionRulesParser.LevelConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#binaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperator(DistributionRulesParser.BinaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#binaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperator(DistributionRulesParser.BinaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#sqlOperator}.
	 * @param ctx the parse tree
	 */
	void enterSqlOperator(DistributionRulesParser.SqlOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#sqlOperator}.
	 * @param ctx the parse tree
	 */
	void exitSqlOperator(DistributionRulesParser.SqlOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link DistributionRulesParser#priority}.
	 * @param ctx the parse tree
	 */
	void enterPriority(DistributionRulesParser.PriorityContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#priority}.
	 * @param ctx the parse tree
	 */
	void exitPriority(DistributionRulesParser.PriorityContext ctx);
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
	 * Enter a parse tree produced by {@link DistributionRulesParser#queue_status}.
	 * @param ctx the parse tree
	 */
	void enterQueue_status(DistributionRulesParser.Queue_statusContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#queue_status}.
	 * @param ctx the parse tree
	 */
	void exitQueue_status(DistributionRulesParser.Queue_statusContext ctx);
}