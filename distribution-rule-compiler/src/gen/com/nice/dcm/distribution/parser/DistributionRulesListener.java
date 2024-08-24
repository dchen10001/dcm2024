// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DistributionRulesParser}.
 */
public interface DistributionRulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(DistributionRulesParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(DistributionRulesParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#rule}.
	 * @param ctx the parse tree
	 */
	void enterRule(DistributionRulesParser.RuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#rule}.
	 * @param ctx the parse tree
	 */
	void exitRule(DistributionRulesParser.RuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link DistributionRulesParser#skills}.
	 * @param ctx the parse tree
	 */
	void enterSkills(DistributionRulesParser.SkillsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DistributionRulesParser#skills}.
	 * @param ctx the parse tree
	 */
	void exitSkills(DistributionRulesParser.SkillsContext ctx);
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
}