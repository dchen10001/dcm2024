package com.nice.dcm.simulation.distribution.rule.vistor;

import java.util.List;

import com.nice.dcm.distribution.parser.DistributionRulesParser.AndSkillsContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillOrSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillSetContext;
import com.nice.dcm.simulation.distribution.node.AndSkillsNodeImpl;
import com.nice.dcm.simulation.distribution.node.ConditionNodeImpl;
import com.nice.dcm.simulation.distribution.node.EntityIdentifierNodeImpl;
import com.nice.dcm.simulation.distribution.node.SkillNodeImpl;
import com.nice.dcm.simulation.distribution.node.SkillSetNodeImpl;
import com.nice.dcm.simulation.distribution.rule.AndSkillLevelConditions;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;

public abstract class AbstractRuleVistorImpl extends BaseRuleVistorImpl {

	protected AbstractRuleVistorImpl() {
		super();
	}

    /**
     * Get a AND skill set rule. The rule language is AND but the actual
     * implementation is OR.(This need to be confirmed)
     * 
     * @param token
     * @return
     */
    @Override
    public AndSkillsNodeImpl visitAndSkills(AndSkillsContext ctx) {
        List<SkillOrSetContext> skills = ctx.skillOrSet();
        List<AndSkillLevelConditions> skillSet = skills.stream().map(this::getAndSkillLevelConditions).toList();
        return new AndSkillsNodeImpl(skillSet);
    }
    
    protected AndSkillLevelConditions getAndSkillLevelConditions(SkillOrSetContext ctx) {
    	SkillSetNodeImpl node = visitSkillOrSet(ctx);
        return node.getAndSkillLevelConditions();
    }  
    
    /**
     * return a single skill or a skill set with multiple skills with or without condition.
     */
    @Override
    public SkillSetNodeImpl visitSkillOrSet(SkillOrSetContext ctx) {
        SkillSetContext skillSet = ctx.skillSet();
        if (skillSet != null) {
            return visitSkillSet(skillSet);
        } else {
            SkillLevelCondition skillLevelCondition = getSkillLevelCondition(ctx.skill());
            return new SkillSetNodeImpl(skillLevelCondition);
        }
    }
    
    /**
     * return a skill set with multiple skills with or without condition.
     * the relationship between multiple skills is AND.
     */
    @Override
    public SkillSetNodeImpl visitSkillSet(SkillSetContext ctx) {
        List<SkillContext> skills = ctx.skill();
        List<SkillLevelCondition> skillLevelConditions = skills.stream().map(this::getSkillLevelCondition).toList();
        return new SkillSetNodeImpl(skillLevelConditions);
    } 
    
	/**
	 * return a single skill with or without condition. if condition is null, a
	 * skill in any level will match it.
	 */
	@Override
	public SkillNodeImpl visitSkill(SkillContext ctx) {
		EntityIdentifierNodeImpl oidNode = visitEntity_identifier(ctx.entity_identifier());

		LevelConditionContext levelCtx = ctx.levelCondition();
		if (levelCtx == null) {
			return new SkillNodeImpl(oidNode.getEntityIdentifier());
		}
		ConditionNodeImpl conditionNode = visitLevelCondition(levelCtx);
		return new SkillNodeImpl(oidNode.getEntityIdentifier(), conditionNode.getCondition());
	}

	protected SkillLevelCondition getSkillLevelCondition(SkillContext ctx) {
		SkillNodeImpl node = visitSkill(ctx);
		return node.getSkillLevelCondition();
	}
}
