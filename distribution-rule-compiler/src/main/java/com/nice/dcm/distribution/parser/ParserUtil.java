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

    
    public <T> T vistorWait(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	WaitRuleContext waitRule = parser.waitRule();
    	
    	return waitRule.accept(vistor);    
    }
    
    public <T> T vistorOid(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.entity_identifier().accept(vistor);    
    }
    
    public <T> T vistorOrder(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.order().accept(vistor);    
    }
    
    public <T> T vistorSkill(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.skill().accept(vistor);    
    }
    
    public <T> T vistorAndSkills(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.andSkills().accept(vistor);    
    }
    
    public <T> T vistorRuleAction(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.ruleAction().accept(vistor);    
    }
    
    public <T> T vistorRoutingRule(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingRule().accept(vistor);    
    }
    
    public <T> T vistorRoutingRuleGroup(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingRuleGroup().accept(vistor);    
    }
    
    public <T> T vistorRoutingRuleSet(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
    	return parser.routingRuleSet().accept(vistor);    
    }
}
