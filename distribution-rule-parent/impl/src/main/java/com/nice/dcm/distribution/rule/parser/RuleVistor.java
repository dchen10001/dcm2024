package com.nice.dcm.distribution.rule.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.distribution.rule.parser.node.Node;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

/**
 * Visitor for visiting the distribution rules.
 * 
 * @param <Node> the type of the node.
 */
public interface RuleVistor extends DistributionRulesVisitor<Node> {
    /**
     * parse a number from a terminal node
     * 
     * @param node
     * @return
     */
	default int toNumber(TerminalNode node) {
		return Integer.parseInt(node.getText());
	}
	
	default long toLong(TerminalNode node) {
		return Long.parseLong(node.getText());
	}
	
	/**
	 * Convert the SqlOperatorContext to SqlOperator.
	 * 
	 * @param ctx the SqlOperatorContext
	 * @return the SqlOperator
	 * @throws ParseCancellationException if the operator is invalid
	 */
	default SqlOperator toSqlOperator(SqlOperatorContext ctx) {
        if(ctx.NOT_IN() != null) {
        	return SqlOperator.NOT_IN;         
		} else if (ctx.IN() != null) {
		}
        return SqlOperator.IN;
	}
	
	/**
	 * Convert the BinaryOperatorContext to BinaryOperator.
	 * @param ctx
	 * @return the BinaryOperator
	 * @throws ParseCancellationException if the operator is invalid
	 */
    default BinaryOperator toBinaryOperator(BinaryOperatorContext ctx) {
        if(ctx.LESS_THAN() != null) {
            return BinaryOperator.LESS_THAN;
        } else if(ctx.LESS_THAN_EQUAL() != null) {
            return BinaryOperator.LESS_THAN_OR_EQUAL;           
        } else if(ctx.EQUAL() != null) {
            return BinaryOperator.EQUAL;            
        } else if(ctx.NOT_EQUAL() != null) {
            return BinaryOperator.NOT_EQUAL;            
        } else if(ctx.GREATER_THAN() != null) {
            return BinaryOperator.GREATER_THAN;         
        } else if(ctx.GREATER_THAN_EQUAL() != null) {
        } 
        return BinaryOperator.GREATER_THAN_OR_EQUAL;            
    } 	
}
