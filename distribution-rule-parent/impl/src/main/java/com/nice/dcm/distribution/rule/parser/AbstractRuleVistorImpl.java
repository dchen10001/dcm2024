package com.nice.dcm.distribution.rule.parser;

import java.util.List;

import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.OrSkillsContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.SkillOrSetContext;
import com.nice.dcm.distribution.rule.parser.DistributionRulesParser.SkillSetContext;
import com.nice.dcm.distribution.rule.parser.node.EntityIdentifierNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillLevelConditionNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillOrSelectorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillSelectorNodeImpl;
import com.nice.dcm.distribution.rule.parser.node.SkillSetSelectorNodeImpl;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.SkillSetSelector;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DistributionRulesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */

public abstract class AbstractRuleVistorImpl extends BaseRuleVistorImpl {
	
	protected AbstractRuleVistorImpl() {
		super();
	}
	
    /**
     * Get a Or skill set selector. The rule language is AND but the actual
     * implementation is OR.
     * 
     * @param token
     * @return
     */	
	@Override
	public SkillOrSelectorNodeImpl visitOrSkills(OrSkillsContext ctx) {
		List<SkillOrSetContext> skillSets = ctx.skillOrSet();
		List<SkillSetSelector> selectors = skillSets.stream().map(this::getSkillSetSelector).toList();
		return new SkillOrSelectorNodeImpl(selectors);
	}

	protected SkillSetSelector getSkillSetSelector(SkillOrSetContext ctx) {
		SkillSetSelectorNodeImpl node = visitSkillOrSet(ctx);
		return node.getSkillSetSelector();
	}
	
    /**
     * return a single skill or a skill set with multiple skills with or without condition.
     */	
	@Override
	public SkillSetSelectorNodeImpl visitSkillOrSet(SkillOrSetContext ctx) {
		ctx.skill();
		if (ctx.skillSet() != null) {
			return visitSkillSet(ctx.skillSet());
		} else {
			SkillSelector selector = getSkillLevelCondition(ctx.skill());
			return new SkillSetSelectorNodeImpl(selector);
		}
	}

    /**
     * return a skill set with multiple skills with or without condition.
     * the relationship between multiple skills is AND.
     */	
	@Override
	public SkillSetSelectorNodeImpl visitSkillSet(SkillSetContext ctx) {
		List<SkillContext> skills = ctx.skill();
		List<SkillSelector> skillSelectors = skills.stream().map(this::getSkillLevelCondition).toList();
		return new SkillSetSelectorNodeImpl(skillSelectors);
	}

	/**
	 * return a single skill with or without condition. if condition is null, a
	 * skill in any level will match it.
	 */	
	@Override
	public SkillSelectorNodeImpl visitSkill(SkillContext ctx) {
		EntityIdentifierNodeImpl oidNode = visitEntity_identifier(ctx.entity_identifier());
		LevelConditionContext levelCtx = ctx.levelCondition();
		if (levelCtx == null) {
			return new SkillSelectorNodeImpl(oidNode.getEntityIdentifier());
		}
		SkillLevelConditionNodeImpl conditionNode = super.visitLevelCondition(levelCtx);
		return new SkillSelectorNodeImpl(oidNode.getEntityIdentifier(), conditionNode.getCondition());
	}
	
	protected SkillSelector getSkillLevelCondition(SkillContext ctx) {
		SkillSelectorNodeImpl node = visitSkill(ctx);
		return node.getSkillSelector();
	}
}
