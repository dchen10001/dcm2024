package com.nice.dcm.simulation.distribution.rule;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class RuleException extends Exception {
	private static final long serialVersionUID = 3168850798352544266L;

	public enum RuleExceptionType {
		NO_RULE_FOUND, INVALID_RULE, INVALID_RULESET, INVALID_RULE_GROUP
	}
	
	private RuleExceptionType type;
	
	private final Object[] args;
	
	public RuleException(RuleExceptionType type) {
		this(type, null);
	}
}
