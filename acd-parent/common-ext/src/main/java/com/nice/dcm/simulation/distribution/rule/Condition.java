package com.nice.dcm.simulation.distribution.rule;

/**
 * interface of condition for binary condition or sql condition.
 */
public interface Condition {
	/**
	 * evaluate if the left value is satisfy the condition
	 * @param left
	 * @return true if the left value satisfy the condition
	 */
	boolean evaluate(int left);
}
