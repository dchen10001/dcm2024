package com.nice.dcm.simulation.distribution.parser.vistor;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesVisitor;
import com.nice.dcm.simulation.distribution.node.Node;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

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
    
    default SqlOperator toSqlOperator(SqlOperatorContext ctx) {
        if(ctx.IN() != null) {
            return SqlOperator.IN;
        } else if(ctx.NOT_IN() != null) {
            return SqlOperator.NOT_IN;         
        } else {        
            Token token = ctx.getStart();
            throw new ParseCancellationException("line " + 
                    token.getLine() + ":" + token.getCharPositionInLine() 
                    + ". Invalid operator: " + ctx.getText());
        }
    }  
    
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
            return BinaryOperator.GREATER_THAN_OR_EQUAL;            
        } else {
            Token token = ctx.getStart();
            throw new ParseCancellationException("lvisitRoutingRuleSetine " + token.getLine() + ":" + token.getCharPositionInLine()
                    + ". Invalid operator: " + ctx.getText());
        } 
    } 	
}
