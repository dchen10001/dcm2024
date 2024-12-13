package com.nice.dcm.simulation.distribution.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.distribution.rule.parser.DCMRuleVisitorImpl;
import com.nice.dcm.distribution.rule.parser.RuleParserHelper;
import com.nice.dcm.simulation.distribution.action.CTDistributionRules;
import com.nice.dcm.simulation.distribution.action.CTRuleSetActions;
import com.nice.dcm.simulation.distribution.action.CTRuleSetActionsImpl;
import com.nice.dcm.simulation.distribution.action.QueueToAction;
import com.nice.dcm.simulation.distribution.action.QueueToActionImpl;
import com.nice.dcm.simulation.distribution.action.QueueToGroupAction;
import com.nice.dcm.simulation.distribution.action.QueueToGroupActionImpl;
import com.nice.dcm.simulation.distribution.action.QueueToGroupSetAction;
import com.nice.dcm.simulation.distribution.action.QueueToGroupSetActionImpl;
import com.nice.dcm.simulation.distribution.action.SkillQueueSelector;
import com.nice.dcm.simulation.distribution.action.SkillQueueSelectorImpl;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSet;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

import lombok.Getter;
import lombok.NonNull;

public class RuleParserServiceImpl implements RuleParserService {
	private static final Logger logger = LoggerFactory.getLogger(RuleParserServiceImpl.class);

	private RuleParserHelper ruleParserHelper;


	@Override
	public CTRuleSetActions parserRules(@NonNull CTDistributionRules ctDistributionRules) {
		ParseData parseData = new ParseData(ctDistributionRules.getSkills());
		for (String contactTypeOid : ctDistributionRules.getContactTypes()) {
			parserRule(parseData, contactTypeOid, ctDistributionRules.getRuleScript(contactTypeOid));
		}
		return parseData.toCTRuleSetActions();
	}
	
	protected void parserRule(ParseData parseData, String contactTypeOid, String ruleScript) {
		if(parseData.containsContactType(contactTypeOid)) {
			logger.warn("The rule of contactType {} has been parsed. Return the cached result.", contactTypeOid);
			return;
		}
		
        logger.debug("Parse rule of contactType {}:\n {}", contactTypeOid, ruleScript);
        RuleParserHelper helper = getRuleParserHelper();
        try {
        	DCMRuleVisitorImpl vistor = dcmRuleVisitor(parseData.getSkills());
        
        	RoutingRuleSet routingRuleSet = helper.visitRoutingRuleSet(ruleScript, vistor);
        
        	logger.debug("ContactTypeOid: {}, RuleNode: {}", contactTypeOid, routingRuleSet);
        
			if (!vistor.getNoExistSkills().isEmpty()) {
				logger.warn("The skills {} are not exist. ContactTypeOid: {} ", vistor.getNoExistSkills(), contactTypeOid);
				parseData.addInvalidSkill(contactTypeOid, vistor.getNoExistSkills());
			}

			QueueToGroupSetAction queueToGroupSetAction = toQueueToGroupSetAction(parseData, routingRuleSet);
			parseData.addQueueToGroupSetAction(contactTypeOid, queueToGroupSetAction);
		} catch (ParseCancellationException e) {
			String parseError = e.getMessage();
			parseData.addError(contactTypeOid, parseError);
			logger.error("Parse rule of contactType {} failed.", contactTypeOid, e);
		}
	}

	private QueueToGroupSetAction toQueueToGroupSetAction(ParseData parseData, RoutingRuleSet routingRuleSet) {
		QueueToGroupAction queueToGroupAction = toQueueToGroupAction(parseData, routingRuleSet.getGroupRule());
		List<RoutingGroupRule> routingGroupRules = routingRuleSet.getGroupRules();
		if (routingGroupRules == null || routingGroupRules.isEmpty()) {
			return new QueueToGroupSetActionImpl(queueToGroupAction);
		}
		List<QueueToGroupAction> queueToGroupActions = routingGroupRules.stream().map(r -> toQueueToGroupAction(parseData, r)).toList();
		return new QueueToGroupSetActionImpl(queueToGroupAction, queueToGroupActions);
	}
	
	private QueueToGroupAction toQueueToGroupAction(ParseData parseData, RoutingGroupRule routingGroupRule) {
		long waitAfterSeconds = routingGroupRule.getWaitAfterSeconds();
		List<QueueToAction> actions = routingGroupRule.getRules().stream().map(r -> toRoutingRule(parseData, r)).toList();
		return new QueueToGroupActionImpl(actions, waitAfterSeconds);
	}
	
	private QueueToAction toRoutingRule(ParseData parseData, RoutingRule routingRule) {
		List<SkillQueueSelector> selectors = routingRule.getSelectors().stream().map(parseData::getSkillQueueSelector).toList();
		return new QueueToActionImpl(selectors, routingRule.getPriority(), routingRule.isLeastBusyOf());
	}



	private DCMRuleVisitorImpl dcmRuleVisitor(Set<String> skills) {
        return new DCMRuleVisitorImpl(skills);
	}
	
	private RuleParserHelper getRuleParserHelper() {
		if (ruleParserHelper == null) {
			ruleParserHelper = new RuleParserHelper();
		}
		return ruleParserHelper;
	}
	
	@Getter
	class ParseData {
		private final Map<SkillSetSelector, SkillQueueSelector> skillSetSelectorMap = new HashMap<>();
		
		// key -> contactTypeOid
		private final Map<String, QueueToGroupSetAction> queueToGroupSetActionMap = new HashMap<>();
		
		private final Map<String, String> ctErrorMap = new HashMap<>();
		
		private final Set<String> skills;

		// key -> contactTypeOid
		private Map<String, Set<String>> invalidSkills = new HashMap<>();
		
		ParseData(Set<String> skills) {
			this.skills = skills;
		}
		
		boolean containsContactType(String contactTypeOid) {
			return queueToGroupSetActionMap.containsKey(contactTypeOid);
		}
		
		void addQueueToGroupSetAction(String contactTypeOid, QueueToGroupSetAction queueToGroupSetAction) {
            queueToGroupSetActionMap.put(contactTypeOid, queueToGroupSetAction);
		}
		
		void addError(String contactTypeOid, String error) {
			ctErrorMap.put(contactTypeOid, error);
		}
		
		void addInvalidSkill(String contactTypeOid, Set<String> skills) {
			invalidSkills.put(contactTypeOid, skills);
		}
		
		SkillQueueSelector getSkillQueueSelector(SkillSetSelector skillSetSelector) {
			return skillSetSelectorMap.computeIfAbsent(skillSetSelector, SkillQueueSelectorImpl::new);
		}		
		
		CTRuleSetActions toCTRuleSetActions() {
			List<SkillQueueSelector> skillQueueSelectors = List.copyOf(skillSetSelectorMap.values());
			return new CTRuleSetActionsImpl(this.queueToGroupSetActionMap, skillQueueSelectors, 
					this.invalidSkills, this.ctErrorMap);
		}
	}	
}
