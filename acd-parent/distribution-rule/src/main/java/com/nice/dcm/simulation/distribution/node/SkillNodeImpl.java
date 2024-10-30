package com.nice.dcm.simulation.distribution.node;

import com.nice.dcm.simulation.distribution.rule.Condition;
import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;
import com.nice.dcm.simulation.distribution.rule.operator.SkillLevelConditionImpl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class SkillNodeImpl implements Node {

	private final SkillLevelCondition skillLevelCondition;
	
	public SkillNodeImpl(String skillOid) {
		this(skillOid, null);
	}
	
    public SkillNodeImpl(String skillOid, Condition condition) {
        this.skillLevelCondition = new SkillLevelConditionImpl(skillOid, condition);
    }
    
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILLRULE;
	}
	
	public boolean evaluate(String skillOid, int left) {
		return skillLevelCondition.evaluate(skillOid, left);
	}
}
