// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.parser;
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
	 * Visit a parse tree produced by {@link DistributionRulesParser#andSkills}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndSkills(DistributionRulesParser.AndSkillsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#skill}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkill(DistributionRulesParser.SkillContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#entity_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity_identifier(DistributionRulesParser.Entity_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder(DistributionRulesParser.OrderContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#waitRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaitRule(DistributionRulesParser.WaitRuleContext ctx);
}