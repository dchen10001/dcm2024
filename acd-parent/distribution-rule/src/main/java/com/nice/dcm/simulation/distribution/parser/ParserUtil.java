package com.nice.dcm.simulation.distribution.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.nice.dcm.distribution.parser.DistributionRulesLexer;
import com.nice.dcm.distribution.parser.DistributionRulesParser;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.simulation.distribution.listener.ThrowingErrorListener;
import com.nice.dcm.simulation.distribution.node.ActionNodeImpl;
import com.nice.dcm.simulation.distribution.node.AndSkillsNodeImpl;
import com.nice.dcm.simulation.distribution.node.BinaryOperatorNodeImpl;
import com.nice.dcm.simulation.distribution.node.ConditionNodeImpl;
import com.nice.dcm.simulation.distribution.node.EntityIdentifierNodeImpl;
import com.nice.dcm.simulation.distribution.node.PriorityNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingRuleGroupNodeImpl;
import com.nice.dcm.simulation.distribution.node.RoutingRuleSetNodeImpl;
import com.nice.dcm.simulation.distribution.node.SkillNodeImpl;
import com.nice.dcm.simulation.distribution.node.SkillSetNodeImpl;
import com.nice.dcm.simulation.distribution.node.SqlOperatorNodeImpl;
import com.nice.dcm.simulation.distribution.node.WaitNodeImpl;
import com.nice.dcm.simulation.distribution.parser.vistor.SkillRuleVisitorImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSet;

public class ParserUtil {
	
    private ThrowingErrorListener errorListener = new ThrowingErrorListener();

    public DistributionRulesParser parserScript(String script) {
    	DistributionRulesLexer tokenSource = new DistributionRulesLexer(CharStreams.fromString(script));
    	tokenSource.removeErrorListeners();
    	tokenSource.addErrorListener(errorListener);
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);
        
        DistributionRulesParser parser = new DistributionRulesParser(tokens);
        parser.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);
        return parser;
    }

    public WaitNodeImpl visitWait(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	WaitRuleContext waitRule = parser.waitRule();
    	return (WaitNodeImpl)waitRule.accept(vistor);    
    }
    
    public EntityIdentifierNodeImpl visitOid(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (EntityIdentifierNodeImpl)parser.entity_identifier().accept(vistor);    
    }
    
    public PriorityNodeImpl visitOrder(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (PriorityNodeImpl)parser.priority().accept(vistor);    
    }
    
    public SkillNodeImpl visitSkill(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (SkillNodeImpl)parser.skill().accept(vistor);    
    }
    
    public AndSkillsNodeImpl visitAndSkills(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (AndSkillsNodeImpl)parser.andSkills().accept(vistor);    
    }
    
    public SkillSetNodeImpl visitSkillSet(String script, SkillRuleVisitorImpl vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (SkillSetNodeImpl)parser.skillSet().accept(vistor);    
    }
    
    public SkillSetNodeImpl visitSkillOrSet(String script, SkillRuleVisitorImpl vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (SkillSetNodeImpl)parser.skillOrSet().accept(vistor);    
    }
    
    public ActionNodeImpl visitRuleAction(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (ActionNodeImpl)parser.ruleAction().accept(vistor);    
    }
    
    public RoutingNodeImpl visitRoutingRule(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingNodeImpl)parser.routingRule().accept(vistor);    
    }
    
    public RoutingRuleGroupNodeImpl visitRoutingRuleGroup(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingRuleGroupNodeImpl)parser.routingRuleGroup().accept(vistor);    
    }
    
    public RoutingRuleGroupNodeImpl visitRoutingWaitRuleGroup(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingRuleGroupNodeImpl)parser.routingWaitingRuleGroup().accept(vistor);    
    }
    
    public RoutingRuleSet visitRoutingRuleSet(String script, SkillRuleVisitorImpl vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	RoutingRuleSetNodeImpl ruleSetNode = (RoutingRuleSetNodeImpl)parser.routingRuleSet().accept(vistor);    
    	return ruleSetNode.getRuleSet();
    }
    
    public SqlOperatorNodeImpl visitSqlOperator(String script, SkillRuleVisitorImpl vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (SqlOperatorNodeImpl)parser.sqlOperator().accept(vistor);  
    }
    
    public BinaryOperatorNodeImpl visitBinaryOperator(String script, SkillRuleVisitorImpl vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (BinaryOperatorNodeImpl)parser.binaryOperator().accept(vistor);  
    }
    
    public ConditionNodeImpl visitLevelCondition(String script, SkillRuleVisitorImpl vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (ConditionNodeImpl)parser.levelCondition().accept(vistor);
    }
}
