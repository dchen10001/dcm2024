package com.nice.dcm.distribution.parser.rule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class SkillRule implements Node {
	private final SkillLevelCondition skillLevelCondition;

	public SkillRule(String skillOid) {
	    this(skillOid, null);
	}
	
    public SkillRule(String skillOid, Condition condition) {
        this.skillLevelCondition = new SkillLevelCondition(skillOid, condition);
    }
    
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLRULE;
	}

}
