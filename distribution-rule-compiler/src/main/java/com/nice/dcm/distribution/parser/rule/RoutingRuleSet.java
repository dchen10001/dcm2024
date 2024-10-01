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
    private final  List<RoutingRuleGroup> ruleGroups;

    public RoutingRuleSet(List<RoutingRuleGroup> ruleGroups) {
    	super();
    	this.ruleGroups = ruleGroups;
    }

	@Override
	public NodeType getNodeType() {
		return NodeType.ROUTINGRULESET;
	}
}
