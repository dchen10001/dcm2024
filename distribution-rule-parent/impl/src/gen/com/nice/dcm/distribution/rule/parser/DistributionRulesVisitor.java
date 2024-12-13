// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.rule.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DistributionRulesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DistributionRulesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#routingRuleSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutingRuleSet(DistributionRulesParser.RoutingRuleSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#routingWaitingRuleGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutingWaitingRuleGroup(DistributionRulesParser.RoutingWaitingRuleGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#routingRuleGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutingRuleGroup(DistributionRulesParser.RoutingRuleGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#routingRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutingRule(DistributionRulesParser.RoutingRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#ruleAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleAction(DistributionRulesParser.RuleActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#orSkills}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrSkills(DistributionRulesParser.OrSkillsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#skillOrSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkillOrSet(DistributionRulesParser.SkillOrSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#skillSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkillSet(DistributionRulesParser.SkillSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#skill}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkill(DistributionRulesParser.SkillContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#levelCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelCondition(DistributionRulesParser.LevelConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#binaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperator(DistributionRulesParser.BinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#sqlOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlOperator(DistributionRulesParser.SqlOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#entity_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity_identifier(DistributionRulesParser.Entity_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#priority}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriority(DistributionRulesParser.PriorityContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#waitRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaitRule(DistributionRulesParser.WaitRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#queue_status}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueue_status(DistributionRulesParser.Queue_statusContext ctx);
}