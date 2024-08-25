package com.nice.dcm.distribution.parser.rule;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RoutingRuleGroup implements Node , Comparable<RoutingRuleGroup> {
    
	private long waitAfterSeconds;
    
	//order by priority
    private final  Set<RoutingRule> rules;

	public RoutingRuleGroup(Set<RoutingRule> rules) {
		super();
		this.rules = rules;
		this.waitAfterSeconds = 0;
	}
	
	public List<RoutingRule> getRoutingRules() {
		return rules.stream().toList();
	}
	
    @Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULEGROUP;
	}

	public void setWaitAfterSeconds(long waitAfterSeconds) {
		this.waitAfterSeconds = waitAfterSeconds;
	}

	public boolean isWaitEnough(long waitTime) {
		return waitTime >= waitAfterSeconds;
	}
	
	public boolean containsAll(Set<String> empSkills) {
		for(RoutingRule rule : rules) {
			if(rule.containsAll(empSkills)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(RoutingRuleGroup o) {
		long r = (waitAfterSeconds - o.waitAfterSeconds);
		if(r > 0) {
			return 1;
		} else if (r < 0) {
			return -1;
		}
		return 0;
	}
}
