package com.nice.dcm.simulation.distribution.rule;

import java.util.List;

/**
 * This interface represents a group of routing rules. 
 * It contains a list of routing rules and a wait time after which the next group of routing rules should be executed.
 * 
 * @see RoutingRule
 * 
 * @author David Chen
 */
public interface RoutingGroupRule {
	/**
	 * Returns the wait time after which the next group of routing rules should be
	 * executed.
	 * 
	 * @return the wait time in seconds
	 */
	long getWaitAfterSeconds();
	
	/**
     * Returns the list of routing rules.
     * 
     * @return the list of routing
     */
	List<RoutingRule> getRules();
}
