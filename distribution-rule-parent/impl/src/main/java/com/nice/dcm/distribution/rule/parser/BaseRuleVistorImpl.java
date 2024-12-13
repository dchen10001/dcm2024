package com.nice.dcm.distribution.rule.parser;

import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.PriorityContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.rule.parser.node.ActionNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.BinaryOperatorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.EntityIdentifierNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.Node;
import com.nice.dcm.distribution.rule.parser.node.PriorityNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.QueueStatusNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillLevelConditionNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SqlOperatorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.WaitNodeImpl;
import com.nice.dcm.simulation.distribution.rule.QueueStatus;
import com.nice.dcm.simulation.distribution.rule.RuleAction;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.BinarySkillLevelConditionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlSkillLevelConditionImpl;

/**
 * BaseRuleVistorImpl class is the base class for all the RuleVistor
 * It contains the common functionality that can be used by all the RuleVistor
 * 
 * @see RuleVistor
 */
public abstract class BaseRuleVistorImpl implements RuleVistor {
	protected static final Logger logger = LoggerFactory.getLogger(BaseRuleVistorImpl.class);
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
	 * operator or condition. 
	 * all methods are independent and can be moved to other classes.
	 *************************************************************************/
    /**
     * Visit a queue status node
     * 
     * @param ctx
     * @return
     */
    @Override
    public QueueStatusNodeImpl visitQueue_status(DistributionRulesParser.Queue_statusContext ctx) {
		if (ctx != null && ctx.LEAST_BUSY_OF() != null) {
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
    public EntityIdentifierNodeImpl visitEntity_identifier(DistributionRulesParser.Entity_identifierContext ctx) {
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
    	return new ActionNodeImpl(RuleAction.QUEUE_TO);
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
    	return new WaitNodeImpl(toLong(ctx.NUMBER()));
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
    public SkillLevelConditionNodeImpl visitLevelCondition(LevelConditionContext ctx) {
    	SkillLevelCondition condition = ctx.binaryOperator() != null ?
    			toSkillLevelCondition(ctx.binaryOperator(), ctx.NUMBER()) : toSkillLevelCondition(ctx.sqlOperator(), ctx.NUMBER());
    	return new SkillLevelConditionNodeImpl(condition);
    }
    
	private SkillLevelCondition toSkillLevelCondition(BinaryOperatorContext binaryOperator, List<TerminalNode> numbers) {
        BinaryOperatorNodeImpl binaryOperatorNode = visitBinaryOperator(binaryOperator);
        int level = this.toNumber(numbers.get(0));
        return new BinarySkillLevelConditionImpl(binaryOperatorNode.getOperator(), level);
	}
	
	private SkillLevelCondition toSkillLevelCondition(SqlOperatorContext sqlOperator, List<TerminalNode> numbers) {
		SqlOperatorNodeImpl binaryOperatorNode = visitSqlOperator(sqlOperator);
        int lowerLevel = this.toNumber(numbers.get(0));
        int upperLevel = this.toNumber(numbers.get(1));
		return new SqlSkillLevelConditionImpl(binaryOperatorNode.getOperator(), lowerLevel, upperLevel);
	}
}
