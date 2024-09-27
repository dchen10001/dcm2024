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
    
    public SkillSetRule(Collection<String> skillOids) {
        this.skillSetKey = new ComparableOidSet(skillOids);
    }
    
    public SkillSetRule(String skillOid) {
        this.skillSetKey = new ComparableOidSet(skillOid);
    }
    
    @Override   
    public NodeType getNodeType() {
        return NodeType.SKILLSETRULE;
    }
}
