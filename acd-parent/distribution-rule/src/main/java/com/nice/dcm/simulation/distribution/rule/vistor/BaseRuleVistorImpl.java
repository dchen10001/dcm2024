package com.nice.dcm.simulation.distribution.rule.vistor;

import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.PriorityContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Queue_statusContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.simulation.distribution.node.ActionNodeImpl;
import com.nice.dcm.simulation.distribution.node.BinaryOperatorNodeImpl;
import com.nice.dcm.simulation.distribution.node.ConditionNodeImpl;
import com.nice.dcm.simulation.distribution.node.EntityIdentifierNodeImpl;
import com.nice.dcm.simulation.distribution.node.Node;
import com.nice.dcm.simulation.distribution.node.PriorityNodeImpl;
import com.nice.dcm.simulation.distribution.node.QueueStatusNodeImpl;
import com.nice.dcm.simulation.distribution.node.SqlOperatorNodeImpl;
import com.nice.dcm.simulation.distribution.node.WaitNodeImpl;
import com.nice.dcm.simulation.distribution.node.ActionNodeImpl.ActionType;
import com.nice.dcm.simulation.distribution.node.QueueStatusNodeImpl.QueueStatus;
import com.nice.dcm.simulation.distribution.rule.Condition;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryCondition;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlCondition;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

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
    
	/**************************************************************************
	 * The following methods are used to convert the context to the corresponding
	 * operator or condition. all methods are independent and can be moved to other classes.
	 *************************************************************************/
    /**
     * Visit a queue status node
     * 
     * @param ctx
     * @return
     */
    @Override
    public QueueStatusNodeImpl visitQueue_status(Queue_statusContext ctx) {
        if (ctx.LEAST_BUSY() != null) {
            return new QueueStatusNodeImpl(QueueStatus.LEAST_BUSY);
        }
        return null;
    }
    
    /**
     * Visit an entity identifier node
     * 
     * @param ctx
     * @return
     */
    @Override
    public EntityIdentifierNodeImpl visitEntity_identifier(Entity_identifierContext ctx) {
        TerminalNode oidNode = ctx.NUMBER();
        if (oidNode == null) {
            oidNode = ctx.UUID_OR_HEXA();
        }
        return new EntityIdentifierNodeImpl(oidNode.getText());
    }
    
    /**
     * Visit a rule action node
     * 
     * @param ctx
     * @return
     */
    @Override
    public ActionNodeImpl visitRuleAction(RuleActionContext ctx) {
        if (ctx.QUEUE_TO() != null) {
            return new ActionNodeImpl(ActionType.QUEUE_TO);
        }
        return null;
    }
    
    /**
     * Visit an order node
     * 
     * @param ctx
     * @return
     */
    @Override
    public PriorityNodeImpl visitPriority(PriorityContext ctx) {
        return new PriorityNodeImpl(toNumber(ctx.NUMBER()));
    }
    
    /**
     * Visit a wait node
     * 
     * @param ctx
     * @return
     */
    @Override
    public WaitNodeImpl visitWaitRule(WaitRuleContext ctx) {
        return new WaitNodeImpl(toNumber(ctx.NUMBER()));
    }
    
	/**************************************************************************
	 * The following methods are used to convert the context to the corresponding
	 * operator or condition.
	 *************************************************************************/
    
	/**
	 * Convert the binary operator context to binary operator node.
	 * 
	 * @param ctx
	 * @return
	 */
    @Override
    public BinaryOperatorNodeImpl visitBinaryOperator(BinaryOperatorContext ctx) {
        BinaryOperator operator = toBinaryOperator(ctx);
        return new BinaryOperatorNodeImpl(operator);       
    }

	/**
	 * Convert the sql operator context to sql operator node.
	 * 
	 * @param ctx
	 * @return
	 */
    @Override
    public SqlOperatorNodeImpl visitSqlOperator(SqlOperatorContext ctx) {
        SqlOperator operator = toSqlOperator(ctx);
        return new SqlOperatorNodeImpl(operator);
    }
    
	/**
	 * Convert the level condition context to condition node.
	 * 
	 * @param ctx
	 * @return
	 */
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
    
    private BinaryCondition toCondition(BinaryOperatorContext binaryOperator, List<TerminalNode> numbers) {
        if (binaryOperator == null) {
            return null;
        }
        BinaryOperatorNodeImpl binaryOperatorNode = visitBinaryOperator(binaryOperator);
        int level = this.toNumber(numbers.get(0));
        return new BinaryCondition(binaryOperatorNode.getOperator(), level);
    }

    private SqlCondition toCondition(SqlOperatorContext sqlOperator, List<TerminalNode> numbers) {
        if (sqlOperator == null) {
            return null;
        }
        SqlOperatorNodeImpl sqlOperatorNode = visitSqlOperator(sqlOperator);
        int lowerLevel = this.toNumber(numbers.get(0));
        int upperLevel = this.toNumber(numbers.get(1));
        return new SqlCondition(sqlOperatorNode.getOperator(), lowerLevel, upperLevel);
    }    
}
