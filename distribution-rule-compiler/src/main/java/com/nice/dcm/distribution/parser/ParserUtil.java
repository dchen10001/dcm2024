package com.nice.dcm.distribution.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.nice.dcm.distribution.listener.ThrowingErrorListener;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;

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

    
    public <T> T visitWait(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	WaitRuleContext waitRule = parser.waitRule();
    	
    	return waitRule.accept(vistor);    
    }
    
    public <T> T visitOid(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.entity_identifier().accept(vistor);    
    }
    
    public <T> T visitOrder(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.order().accept(vistor);    
    }
    
    public <T> T visitSkill(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.skill().accept(vistor);    
    }
    
    public <T> T visitAndSkills(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.andSkills().accept(vistor);    
    }
    
    public <T> T visitSkillSet(String script, DistributionRulesVisitor<T> vistor) {
        DistributionRulesParser parser = parserScript(script);
        return parser.skillSet().accept(vistor);    
    }
    
    public <T> T visitSkillOrSet(String script, DistributionRulesVisitor<T> vistor) {
        DistributionRulesParser parser = parserScript(script);
        return parser.skillOrSet().accept(vistor);    
    }
    
    public <T> T visitRuleAction(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.ruleAction().accept(vistor);    
    }
    
    public <T> T visitRoutingRule(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingRule().accept(vistor);    
    }
    
    public <T> T visitRoutingRuleGroup(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingRuleGroup().accept(vistor);    
    }
    
    public <T> T visitRoutingWaitRuleGroup(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingWaitingRuleGroup().accept(vistor);    
    }
    
    public <T> T visitRoutingRuleSet(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingRuleSet().accept(vistor);    
    }
}
