package com.nice.dcm.simulation.distribution.rule.vistor;

import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.simulation.distribution.operator.BinaryCondition;
import com.nice.dcm.simulation.distribution.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.operator.SqlCondition;
import com.nice.dcm.simulation.distribution.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.Condition;
import com.nice.dcm.simulation.distribution.rule.Node;
import com.nice.dcm.simulation.distribution.rule.node.BinaryOperatorNodeImpl;
import com.nice.dcm.simulation.distribution.rule.node.ConditionNodeImpl;
import com.nice.dcm.simulation.distribution.rule.node.SqlOperatorNodeImpl;

public abstract class BaseRuleVistorImpl implements RuleVistor {
	protected BaseRuleVistorImpl() {

	}
	
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
    public BinaryOperatorNodeImpl visitBinaryOperator(BinaryOperatorContext ctx) {
        BinaryOperator operator = toBinaryOperator(ctx);
        return new BinaryOperatorNodeImpl(operator);       
    }

    @Override
    public SqlOperatorNodeImpl visitSqlOperator(SqlOperatorContext ctx) {
        SqlOperator operator = toSqlOperator(ctx);
        return new SqlOperatorNodeImpl(operator);
    }
    
    @Override
    public ConditionNodeImpl visitLevelCondition(LevelConditionContext ctx) {
        BinaryOperatorContext binaryOperator = ctx.binaryOperator();
        List<TerminalNode> numbers = ctx.NUMBER();

        Condition condition = toCondition(binaryOperator, numbers);
        if (condition == null) {
            SqlOperatorContext sqlOperator = ctx.sqlOperator();
            condition = toCondition(sqlOperator, numbers);
        }
        return new ConditionNodeImpl(condition);
    }
    
    private Condition toCondition(BinaryOperatorContext binaryOperator, List<TerminalNode> numbers) {
        if (binaryOperator == null) {
            return null;
        }
        BinaryOperatorNodeImpl binaryOperatorNode = visitBinaryOperator(binaryOperator);
        int level = this.toNumber(numbers.get(0));
        return new BinaryCondition(binaryOperatorNode.getOperator(), level);
    }

    private Condition toCondition(SqlOperatorContext sqlOperator, List<TerminalNode> numbers) {
        if (sqlOperator == null) {
            return null;
        }
        SqlOperatorNodeImpl sqlOperatorNode = visitSqlOperator(sqlOperator);
        int lowerLevel = this.toNumber(numbers.get(0));
        int upperLevel = this.toNumber(numbers.get(1));
        return new SqlCondition(sqlOperatorNode.getOperator(), lowerLevel, upperLevel);
    }    
}
