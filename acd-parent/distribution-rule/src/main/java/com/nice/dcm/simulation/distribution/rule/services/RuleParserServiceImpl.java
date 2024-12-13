package com.nice.dcm.simulation.distribution.rule.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nice.dcm.simulation.distribution.parser.ParserUtil;
import com.nice.dcm.simulation.distribution.parser.vistor.SkillRuleVisitorImpl;
import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.RoutingGroupRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRule;
import com.nice.dcm.simulation.distribution.rule.RoutingRuleSet;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.SkillQueueSelector;
import com.nice.dcm.simulation.distribution.rule.action.QueueToAction;
import com.nice.dcm.simulation.distribution.rule.action.QueueToActionImpl;
import com.nice.dcm.simulation.distribution.rule.action.RoutingGroupAction;
import com.nice.dcm.simulation.distribution.rule.action.RoutingGroupActionImpl;
import com.nice.dcm.simulation.distribution.rule.action.RoutingSetAction;
import com.nice.dcm.simulation.distribution.rule.action.RoutingSetActionImpl;
import com.nice.dcm.simulation.distribution.rule.operator.SkillQueueSelectorImpl;
import com.nice.dcm.simulation.distribution.service.RuleParserService;
import com.nice.dcm.simulation.distribution.storage.ChannelSelectorStorage;

public class RuleParserServiceImpl implements RuleParserService {
	private static final Logger logger = LoggerFactory.getLogger(RuleParserServiceImpl.class);
	
	private final ParserUtil parserUtil = new ParserUtil();
	
	private final Map<Integer, ChannelSelectorStorageImpl> channelStorages = new HashMap<>();
	
	private Set<String> skills;
	
	public RuleParserServiceImpl() {

	}
	
	public RuleParserServiceImpl(Set<String> skills) {
		this.setSkills(skills);
	}
	
	@Override
	public RoutingSetAction parserRule(int channelId, String contactTypeOid, String rule) {
		logger.debug("Parse rule: {}", rule);
		SkillRuleVisitorImpl vistor = new SkillRuleVisitorImpl(getSkills());
		
		RoutingRuleSet ruleSet = parserUtil.visitRoutingRuleSet(rule, vistor);
		logger.info("ChannelId: {}, ContactTypeOid: {}, RuleNode: {}", channelId, contactTypeOid, ruleSet);
		if (!vistor.getNoExistSkills().isEmpty()) {
			logger.warn("The skills {} are not exist. ChannelId: {}, ContactTypeOid: {} ", vistor.getNoExistSkills(), channelId, contactTypeOid);
		}
		return toRoutingSetAction(channelId, ruleSet);
	}
	
	@Override
	public ChannelSelectorStorage getChannelStorage(int channelId) {
		return channelStorages.get(channelId);
	}
	
	protected RoutingSetAction toRoutingSetAction(int channelId, RoutingRuleSet ruleSet) {
		RoutingGroupAction defaultAction = toRoutingGroupAction(channelId, ruleSet.getGroupRule());
		List<RoutingGroupAction> actions = ruleSet.getGroupRules().stream().map(r -> toRoutingGroupAction(channelId, r)).toList();
		return new RoutingSetActionImpl(defaultAction, actions);
	}
	
	private RoutingGroupAction toRoutingGroupAction(int channelId, RoutingGroupRule groupRule) {
		long waitAfterSeconds = groupRule.getWaitAfterSeconds();
		List<QueueToAction> actions = groupRule.getRules().stream().map(r -> toQueueToAction(channelId, r)).toList();
		return new RoutingGroupActionImpl(waitAfterSeconds, actions);
	}
	
	private QueueToAction toQueueToAction(int channelId, RoutingRule rule) {
		boolean leastBusy = rule.isLeastBusyOf();
		Set<SkillQueueSelector> selectors = rule.getSkills().stream().map(s -> createOrGetSkillQueueSelector(channelId, s, leastBusy)).collect(Collectors.toSet());
        return new QueueToActionImpl(selectors, rule.getPriority(), leastBusy);
	}
	
	protected SkillQueueSelector createOrGetSkillQueueSelector(int channelId, AndSkillLevelConditions skill, boolean requiredWaitingTime) {
		logger.debug("Create or get skill queue selector. ChannelId: {}, skill: {}, requiredWaitingTime: {}", channelId, skill, requiredWaitingTime);
		ChannelSelectorStorageImpl channelStorage = channelStorages.computeIfAbsent(channelId, k -> new ChannelSelectorStorageImpl(channelId));
		return channelStorage.addSelector(skill, requiredWaitingTime);
	}

	private Set<String> getSkills() {
		if(this.skills == null) {
			return Set.of();
		}
        return skills;
	}
	
	public void setSkills(Set<String> skills) {
		if (skills == null) {
			this.skills = Set.of();
		} else {
			this.skills = skills;
		}
	}
	
	class ChannelSelectorStorageImpl implements ChannelSelectorStorage {
		private final int channelId;
		private final Map<Collection<SkillLevelCondition>, SkillQueueSelector> skillQueueSelectors = new HashMap<>();

		private final Map<Integer, SkillQueueSelector> skillQueueSelectorsById = new HashMap<>();

		public ChannelSelectorStorageImpl(int channelId) {
			this.channelId = channelId;
		}

		public SkillQueueSelector addSelector(AndSkillLevelConditions skill, boolean requiredWaitingTime) {
			Collection<SkillLevelCondition> conditions = skill.getSkillLevelConditions();
			SkillQueueSelector selector = skillQueueSelectors.computeIfAbsent(conditions,
					k -> SkillQueueSelectorImpl.createSkillQueueSelector(channelId, conditions, requiredWaitingTime));
			if (!skillQueueSelectorsById.containsKey(selector.getId())) {
				skillQueueSelectorsById.put(selector.getId(), selector);
			}
			return selector;
		}

		@Override
		public List<SkillQueueSelector> getSelectors() {
			return skillQueueSelectors.values().stream().toList();
		}
		
		@Override
		public SkillQueueSelector findSelector(Collection<SkillLevelCondition> conditions) {
			return skillQueueSelectors.get(conditions);
		}
		
		@Override
		public SkillQueueSelector findSelector(int id) {
			return skillQueueSelectorsById.get(id);
		}
		
		public int getChannelId() {
			return channelId;
		}
	}
}
