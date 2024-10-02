package com.nice.dcm.distribution.parser.rule;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class SkillSetRule implements Node {
    private final ComparableOidSet skillSetKey;
    
    public SkillSetRule(Collection<SkillLevelCondition> skillLevelConditions) {
        this.skillSetKey = new ComparableOidSet(skillLevelConditions);
    }
    
    public SkillSetRule(SkillLevelCondition skillLevelCondition) {
        this.skillSetKey = new ComparableOidSet(skillLevelCondition);
    }
    
    @Override   
    public NodeType getNodeType() {
        return NodeType.SKILLSETRULE;
    }
}
