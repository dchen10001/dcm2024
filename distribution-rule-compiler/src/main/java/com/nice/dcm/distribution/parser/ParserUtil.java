package com.nice.dcm.distribution.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;

import com.nice.dcm.distribution.listener.ThrowingErrorListener;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RuleContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.RulesContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.WaitRuleContext;

public class ParserUtil {
    private ThrowingErrorListener errorListener = new ThrowingErrorListener();
    
//    public RulesContext parserRulesContext(String script) {
//        DistributionRulesParser parser = parserScript(script);
//        return parser.rules();
//    }
//    
    public RuleContext parserRuleContext(String script) {
        DistributionRulesParser parser = parserScript(script);
        return parser.rule_();
    }
    
    protected DistributionRulesParser parserScript(String script) {
        TokenSource tokenSource = new DistributionRulesLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);
        DistributionRulesParser parser = new DistributionRulesParser(tokens);
        parser.addErrorListener(errorListener);
        return parser;
    }
    
    public <T> T vistorRules(String script, DistributionRulesVisitor<T> vistor) {
    	DistributionRulesParser parser = parserScript(script);
        return  parser.rules().accept(vistor);    
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
}
