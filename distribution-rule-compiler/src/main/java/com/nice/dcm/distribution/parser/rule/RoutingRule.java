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
	private static final String KEY_LEAST_BUSY = "least busy of";
	private static final String KEY_LHIGHER_RANKING = "higher ranking of";
	
	public enum Agent_Status {
		LEAST_BUSY, HIGHER_RANKING;
		
		public static Agent_Status findByValue(String value) {
			if(KEY_LEAST_BUSY.equals(value)) {
				return LEAST_BUSY;
			} else if(KEY_LHIGHER_RANKING.equals(value)){
				return HIGHER_RANKING;
			}
			return null;
		}
	}
	
	private final ActionRule action;
	
	//skill oids, AND condition
	private final Set<String> skills;
	private final int priority;
	private final Agent_Status status;
	
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
