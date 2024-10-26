package com.nice.dcm.distribution.parser;

import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.DistributionRulesParser.BinaryOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Entity_identifierContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.OrderContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Queue_statusContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleActionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SqlOperatorContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.parser.node.BinaryCondition;
import com.nice.dcm.distribution.parser.node.BinaryOperator;
import com.nice.dcm.distribution.parser.node.Condition;
import com.nice.dcm.distribution.parser.node.Node;
import com.nice.dcm.distribution.parser.node.SqlCondition;
import com.nice.dcm.distribution.parser.node.SqlOperator;
import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.ActionRule.ActionType;
import com.nice.dcm.distribution.parser.rule.BinaryOperatorRule;
import com.nice.dcm.distribution.parser.rule.ConditionRule;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule.QueueStatus;
import com.nice.dcm.distribution.parser.rule.SqlOperatorRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

public abstract class BaseRuleVistorImpl extends BaseVistorImpl {
    protected BaseRuleVistorImpl() {
    	super();
    }

    public ConditionRule visitLevelCondition(LevelConditionContext ctx) {
        BinaryOperatorContext binaryOperator = ctx.binaryOperator();
        List<TerminalNode> numbers = ctx.NUMBER();

        Condition condition = toCondition(binaryOperator, numbers);
        if (condition == null) {
            SqlOperatorContext sqlOperator = ctx.sqlOperator();
            condition = toCondition(sqlOperator, numbers);
        }
        return new ConditionRule(condition);
    }

    @Override
    public BinaryOperatorRule visitBinaryOperator(BinaryOperatorContext ctx) {
        BinaryOperator operator = toBinaryOperator(ctx);
        return new BinaryOperatorRule(operator);       
    }

    @Override
    public SqlOperatorRule visitSqlOperator(SqlOperatorContext ctx) {
        SqlOperator operator = toSqlOperator(ctx);
        return new SqlOperatorRule(operator);
    }

    /**
     * Visit an entity identifier node
     * 
     * @param ctx
     * @return
     */
    @Override
    public OidRule visitEntity_identifier(Entity_identifierContext ctx) {
        TerminalNode oidNode = ctx.NUMBER();
        if (oidNode == null) {
            oidNode = ctx.UUID_OR_HEXA();
        }
        return new OidRule(oidNode.getText());
    }

    /**
     * Visit a rule action node
     * 
     * @param ctx
     * @return
     */
    @Override
    public ActionRule visitRuleAction(RuleActionContext ctx) {
        if (ctx.QUEUE_TO() != null) {
            return new ActionRule(ActionType.QUEUE_TO);
        }
        return null;
    }

    /**
     * Visit a wait node
     * 
     * @param ctx
     * @return
     */
    @Override
    public WaitRule visitWaitRule(WaitRuleContext ctx) {
        return new WaitRule(toNumber(ctx.NUMBER()));
    }

    /**
     * Visit an order node
     * 
     * @param ctx
     * @return
     */
    @Override
    public OrderRule visitOrder(OrderContext ctx) {
        return new OrderRule(toNumber(ctx.NUMBER()));
    }
 
    private Condition toCondition(BinaryOperatorContext binaryOperator, List<TerminalNode> numbers) {
        if (binaryOperator == null) {
            return null;
        }
        BinaryOperatorRule binaryOperatorRule = visitBinaryOperator(binaryOperator);
        int level = this.toNumber(numbers.get(0));
        return new BinaryCondition(binaryOperatorRule.getOperator(), level);
    }

    private Condition toCondition(SqlOperatorContext sqlOperator, List<TerminalNode> numbers) {
        if (sqlOperator == null) {
            return null;
        }
        SqlOperatorRule sqlOperatorRule = visitSqlOperator(sqlOperator);
        int lowerLevel = this.toNumber(numbers.get(0));
        int upperLevel = this.toNumber(numbers.get(1));
        return new SqlCondition(sqlOperatorRule.getOperator(), lowerLevel, upperLevel);
    }    
}
