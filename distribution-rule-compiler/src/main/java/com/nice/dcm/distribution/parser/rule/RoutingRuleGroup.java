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
public class RoutingRuleGroup implements Node {
    
	private long waitAfterSeconds;
    private final  List<RoutingRule> rules;

	public RoutingRuleGroup(List<RoutingRule> rules) {
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
}
