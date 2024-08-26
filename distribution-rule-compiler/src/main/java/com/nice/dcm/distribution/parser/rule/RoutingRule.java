package com.nice.dcm.distribution.parser.rule;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRule implements Node, Comparable<RoutingRule> {
	private final ActionRule action;
	
	//skill oids, AND condition
	private final Set<String> skills;
	private final int priority;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULE;
	}

	@Override
	public int compareTo(RoutingRule o) {
		return priority - o.priority;
	}
	
	public boolean containsAll(Set<String> empSkills) {
		return skills.containsAll(empSkills);
	}
}