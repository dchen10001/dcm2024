package com.nice.dcm.distribution.rule.parser.node;

import com.nice.dcm.simulation.distribution.rule.SkillLevelCondition;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Node implementation for CONDITION node condition: condition to be evaluated
 * Condition: condition to be evaluated, it is either a binary condition or 
 * a sql condition.
 * @see Condition
 *
 */

@Getter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ToString
public class SkillLevelConditionNodeImpl implements Node {
	private final SkillLevelCondition condition;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.SKILL_LEVEL_CONDITION;
	}
}
