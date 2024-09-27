package com.nice.dcm.distribution.parser.rule;

import java.util.List;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class RoutingRuleSet implements Node {
	//order by waitAfterSeconds
    private final  Set<RoutingRuleGroup> ruleGroups;
    
    private  List<RoutingRuleGroup> routingRuleGroups;
    
    public RoutingRuleSet(Set<RoutingRuleGroup> ruleGroups) {
    	super();
    	this.ruleGroups = ruleGroups;
    }

	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULESET;
	}
	
	public List<RoutingRuleGroup> getRoutingRuleGroups() {
		if(routingRuleGroups == null) {
			routingRuleGroups = this.ruleGroups.stream().toList();
		}
		return routingRuleGroups;
	}	
}
