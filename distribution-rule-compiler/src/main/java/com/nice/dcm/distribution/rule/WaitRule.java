package com.nice.dcm.distribution.rule;

public class WaitRule implements Rule {
	private final int waitFor;
	
	public WaitRule(int waitFor) {
		super();
		this.waitFor = waitFor;
	}

	@Override
	public RuleType getRuleType() {
		return RuleType.WAITING;
	}

	public int getWaitFor() {
		return waitFor;
	}
}
