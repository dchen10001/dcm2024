package com.nice.dcm.distribution.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.nice.dcm.distribution.listener.ThrowingErrorListener;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;
import com.nice.dcm.distribution.parser.rule.ActionRule;
import com.nice.dcm.distribution.parser.rule.AndSkillsRule;
import com.nice.dcm.distribution.parser.rule.BinaryOperatorRule;
import com.nice.dcm.distribution.parser.rule.ConditionRule;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.OrderRule;
import com.nice.dcm.distribution.parser.rule.RoutingRule;
import com.nice.dcm.distribution.parser.rule.RoutingRuleGroup;
import com.nice.dcm.distribution.parser.rule.RoutingRuleSet;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.SkillSetRule;
import com.nice.dcm.distribution.parser.rule.SqlOperatorRule;
import com.nice.dcm.distribution.parser.rule.WaitRule;

public class ParserUtil {
    private ThrowingErrorListener errorListener = new ThrowingErrorListener();

    protected DistributionRulesParser parserScript(String script) {
    	DistributionRulesLexer tokenSource = new DistributionRulesLexer(CharStreams.fromString(script));
    	tokenSource.removeErrorListeners();
    	tokenSource.addErrorListener(errorListener);
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);
        DistributionRulesParser parser = new DistributionRulesParser(tokens);
        parser.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);
        return parser;
    }

    
    public WaitRule visitWait(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	WaitRuleContext waitRule = parser.waitRule();
    	return (WaitRule)waitRule.accept(vistor);    
    }
    
    public OidRule visitOid(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (OidRule)parser.entity_identifier().accept(vistor);    
    }
    
    public OrderRule visitOrder(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (OrderRule)parser.order().accept(vistor);    
    }
    
    public SkillRule visitSkill(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (SkillRule)parser.skill().accept(vistor);    
    }
    
    public AndSkillsRule visitAndSkills(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (AndSkillsRule)parser.andSkills().accept(vistor);    
    }
    
    public SkillSetRule visitSkillSet(String script, SkillRuleVisitor vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (SkillSetRule)parser.skillSet().accept(vistor);    
    }
    
    public SkillSetRule visitSkillOrSet(String script, SkillRuleVisitor vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (SkillSetRule)parser.skillOrSet().accept(vistor);    
    }
    
    public ActionRule visitRuleAction(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (ActionRule)parser.ruleAction().accept(vistor);    
    }
    
    public RoutingRule visitRoutingRule(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingRule)parser.routingRule().accept(vistor);    
    }
    
    public RoutingRuleGroup visitRoutingRuleGroup(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingRuleGroup)parser.routingRuleGroup().accept(vistor);    
    }
    
    public RoutingRuleGroup visitRoutingWaitRuleGroup(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingRuleGroup)parser.routingWaitingRuleGroup().accept(vistor);    
    }
    
    public RoutingRuleSet visitRoutingRuleSet(String script, SkillRuleVisitor vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return (RoutingRuleSet)parser.routingRuleSet().accept(vistor);    
    }
    
    public SqlOperatorRule visitSqlOperator(String script, SkillRuleVisitor vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (SqlOperatorRule)parser.sqlOperator().accept(vistor);  
    }
    
    public BinaryOperatorRule visitBinaryOperator(String script, SkillRuleVisitor vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (BinaryOperatorRule)parser.binaryOperator().accept(vistor);  
    }
    
    public ConditionRule visitLevelCondition(String script, SkillRuleVisitor vistor) {
        DistributionRulesParser parser = parserScript(script);
        return (ConditionRule)parser.levelCondition().accept(vistor);
    }
}
