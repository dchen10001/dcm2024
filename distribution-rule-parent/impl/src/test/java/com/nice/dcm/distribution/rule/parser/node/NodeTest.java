package com.nice.dcm.distribution.rule.parser.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.distribution.rule.QueueStatus;
import com.nice.dcm.simulation.distribution.rule.RuleAction;
import com.nice.dcm.simulation.distribution.rule.SkillSelector;
import com.nice.dcm.simulation.distribution.rule.operator.SkillSelectorImpl;

class NodeTest {
	@Test
	void testGetNodeType() {
		Node node = new ActionNodeImpl(RuleAction.QUEUE_TO);
		assertEquals(NodeType.ACTIONRULE, node.getNodeType());
		
		node = new BinaryOperatorNodeImpl("=");
		assertEquals(NodeType.BINARY_OPERATOR, node.getNodeType());
		
		node = new EntityIdentifierNodeImpl("entityIdentifier");
		assertEquals(NodeType.ENTITY_IDENTIFIER, node.getNodeType());
		
		node = new PriorityNodeImpl(1);
		assertEquals(NodeType.PRIORITY, node.getNodeType());
		
		node = new QueueStatusNodeImpl(QueueStatus.LEAST_BUSY);
		assertEquals(NodeType.QUEUE_STATUS, node.getNodeType());
		
		node = new RoutingRuleGroupNodeImpl(null);
		assertEquals(NodeType.ROUTINGRULEGROUP, node.getNodeType());
		
		node = new RoutingRuleNodeImpl(null);
		assertEquals(NodeType.ROUTINGRULE, node.getNodeType());
		
		node = new RoutingRuleSetNodeImpl(null);
		assertEquals(NodeType.ROUTINGRULESET, node.getNodeType());
		
		node = new SkillLevelConditionNodeImpl(null);
		assertEquals(NodeType.SKILL_LEVEL_CONDITION, node.getNodeType());
		
		node = new SkillOrSelectorNodeImpl(null);
		assertEquals(NodeType.SKILL_OR_SELECTOR, node.getNodeType());
		
		node = new SkillSelectorNodeImpl("oid1");
		assertEquals(NodeType.SKILL_SELECTOR, node.getNodeType());
		
		node = new SkillSetSelectorNodeImpl(new SkillSelectorImpl("oid1"));
		assertEquals(NodeType.SKILL_SET_SELECTOR, node.getNodeType());
		
		node = new SqlOperatorNodeImpl("in");
		assertEquals(NodeType.SQL_OPERATOR, node.getNodeType());
		
		node = new WaitNodeImpl(100);
		assertEquals(NodeType.WAITING, node.getNodeType());
	}
}
