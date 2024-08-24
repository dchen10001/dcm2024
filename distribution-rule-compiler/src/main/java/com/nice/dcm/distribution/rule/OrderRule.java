package com.nice.dcm.distribution.rule;

public class OrderRule implements Rule {
	private final int priority;
	
	public OrderRule(int priority) {
		super();
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public RuleType getRuleType() {
		return RuleType.ORDER;
	}

}
