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
	 * Visit a parse tree produced by {@link DistributionRulesParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(DistributionRulesParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule(DistributionRulesParser.RuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link DistributionRulesParser#skills}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkills(DistributionRulesParser.SkillsContext ctx);
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
}