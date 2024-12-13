package com.nice.dcm.distribution.rule.parser;

import java.util.List;

import com.nice.dcm.distribution.rule.parser.node.ActionNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.BinaryOperatorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.EntityIdentifierNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.PriorityNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.RoutingRuleGroupNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.RoutingRuleNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillLevelConditionNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillOrSelectorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillSelectorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillSetSelectorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SqlOperatorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.WaitNodeImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;
import com.nice.dcm.simulation.distribution.rule.RuleAction;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;
import com.nice.dcm.simulation.distribution.rule.operator.BinaryOperator;
import com.nice.dcm.simulation.distribution.rule.operator.SqlOperator;

public class TestRuleParserHelper extends RuleParserHelper {
	/**********************************************************************
	 *     
	 *  functions of basic elements of the rule  
	 *     
	 **********************************************************************/	
    public long visitWaitFor(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	WaitNodeImpl node = (WaitNodeImpl)parser.waitRule().accept(dcmRuleVisitor);
    	return node.getWaitFor();
    }
    
    public int visitPriority(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	PriorityNodeImpl node = (PriorityNodeImpl)parser.priority().accept(dcmRuleVisitor);
    	return node.getPriority();
    }
    
	public String visitOid(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
		DistributionRulesParser parser = parserScript(script);
		EntityIdentifierNodeImpl node = (EntityIdentifierNodeImpl)parser.entity_identifier().accept(dcmRuleVisitor);
		return node.getEntityIdentifier();
	}
	
    public SqlOperator visitSqlOperator(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
        DistributionRulesParser parser = parserScript(script);
        SqlOperatorNodeImpl node = (SqlOperatorNodeImpl)parser.sqlOperator().accept(dcmRuleVisitor);  
        return node.getOperator();
    }
    
    public BinaryOperator visitBinaryOperator(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
        DistributionRulesParser parser = parserScript(script);
        BinaryOperatorNodeImpl node = (BinaryOperatorNodeImpl)parser.binaryOperator().accept(dcmRuleVisitor);  
        return node.getOperator();
    }
    
    public SkillLevelCondition visitLevelCondition(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
        DistributionRulesParser parser = parserScript(script);
        SkillLevelConditionNodeImpl node = (SkillLevelConditionNodeImpl)parser.levelCondition().accept(dcmRuleVisitor);
        return node.getCondition();
    }
	
    public RuleAction visitRuleAction(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	ActionNodeImpl node = (ActionNodeImpl)parser.ruleAction().accept(dcmRuleVisitor);
    	return node.getAction();
    }
    
	/**********************************************************************
	 *     
	 *  functions of skill selectors
	 *     
	 **********************************************************************/
    public SkillSelector visitSkill(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	SkillSelectorNodeImpl node = (SkillSelectorNodeImpl)parser.skill().accept(dcmRuleVisitor);
    	return node.getSkillSelector();
    }

    public SkillSetSelector visitSkillSet(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	SkillSetSelectorNodeImpl node = (SkillSetSelectorNodeImpl)parser.skillSet().accept(dcmRuleVisitor);
    	return node.getSkillSetSelector();
    }
    
    public SkillSetSelector visitSkillOrSet(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	SkillSetSelectorNodeImpl node = (SkillSetSelectorNodeImpl)parser.skillOrSet().accept(dcmRuleVisitor);
    	return node.getSkillSetSelector();
    }
    
    public List<SkillSetSelector> visitOrSkills(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	SkillOrSelectorNodeImpl node = (SkillOrSelectorNodeImpl)parser.orSkills().accept(dcmRuleVisitor);
    	return node.getSelectors();
    }
    
	/**********************************************************************
	 *     
	 *  functions of rule and rule group
	 *     
	 **********************************************************************/
    public RoutingRule visitRoutingRule(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	RoutingRuleNodeImpl node = (RoutingRuleNodeImpl)parser.routingRule().accept(dcmRuleVisitor);
    	return node.getRoutingRule();
    }
    
    public RoutingGroupRule visitRoutingRuleGroup(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	RoutingRuleGroupNodeImpl node = (RoutingRuleGroupNodeImpl)parser.routingRuleGroup().accept(dcmRuleVisitor);
    	return node.getRoutingGroupRule();
    }
    
    public RoutingGroupRule visitRoutingWaitRuleGroup(String script, DCMRuleVisitorImpl dcmRuleVisitor) {
    	DistributionRulesParser parser = parserScript(script);
    	RoutingRuleGroupNodeImpl node = (RoutingRuleGroupNodeImpl)parser.routingWaitingRuleGroup().accept(dcmRuleVisitor);
    	return node.getRoutingGroupRule();
    }
}
