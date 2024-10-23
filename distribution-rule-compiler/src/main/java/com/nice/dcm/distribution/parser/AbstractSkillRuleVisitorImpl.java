package com.nice.dcm.distribution.parser;

import java.util.List;

import com.nice.dcm.distribution.parser.DistributionRulesParser.LevelConditionContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.Queue_statusContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillOrSetContext;
import com.nice.dcm.distribution.parser.DistributionRulesParser.SkillSetContext;
import com.nice.dcm.distribution.parser.rule.ConditionRule;
import com.nice.dcm.distribution.parser.rule.OidRule;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule;
import com.nice.dcm.distribution.parser.rule.SkillLevelCondition;
import com.nice.dcm.distribution.parser.rule.SkillRule;
import com.nice.dcm.distribution.parser.rule.SkillSetRule;
import com.nice.dcm.distribution.parser.rule.QueueStatusRule.QueueStatus;

public abstract class AbstractSkillRuleVisitorImpl extends BaseRuleVistorImpl {
    protected AbstractSkillRuleVisitorImpl() {
        super();
    }
    
    /**
     * return a single skill or a skill set with multiple skills with or without condition.
     */
    @Override
    public SkillSetRule visitSkillOrSet(SkillOrSetContext ctx) {
        SkillSetContext skillSet = ctx.skillSet();
        if (skillSet != null) {
            return visitSkillSet(skillSet);
        } else {
            SkillLevelCondition skillLevelCondition = getSkillLevelCondition(ctx.skill());
            return new SkillSetRule(skillLevelCondition);
        }
    }
    
    /**
     * return a skill set with multiple skills with or without condition.
     * the relationship between multiple skills is AND.
     */
    @Override
    public SkillSetRule visitSkillSet(SkillSetContext ctx) {
        List<SkillContext> skills = ctx.skill();
        List<SkillLevelCondition> skillLevelConditions = skills.stream().map(this::getSkillLevelCondition).toList();
        return new SkillSetRule(skillLevelConditions);
    } 
    
    /**
     * return a single skill with or without condition.
     * if condition is null, a skill in any level will match it.
     */
    @Override
    public SkillRule visitSkill(SkillContext ctx) {
        OidRule oidRule = visitEntity_identifier(ctx.entity_identifier());
        
        LevelConditionContext levelCtx = ctx.levelCondition();
        if (levelCtx == null) {
            return new SkillRule(oidRule.getOid());
        }
        ConditionRule conditionRule = visitLevelCondition(levelCtx);
        return new SkillRule(oidRule.getOid(), conditionRule.getCondition());
    }
    
    /**
     * Visit a queue status node
     * 
     * @param ctx
     * @return
     */
    @Override
    public QueueStatusRule visitQueue_status(Queue_statusContext ctx) {
        if (ctx.LEAST_BUSY() != null) {
            return new QueueStatusRule(QueueStatus.LEAST_BUSY);
        }
        return null;
    }
    
    protected SkillLevelCondition getSkillLevelCondition(SkillContext ctx) {
        SkillRule node = visitSkill(ctx);
        return node.getSkillLevelCondition();
    }
}
