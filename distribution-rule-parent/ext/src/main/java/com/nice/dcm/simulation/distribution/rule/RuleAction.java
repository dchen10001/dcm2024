package com.nice.dcm.simulation.distribution.rule;

/**
 * RuleAction enum defines Rule's Action type. 
 * 
 * DCM distribution rule only supports Queue to Action.
 * 
 * In future, it may supports Route To, or others.
 * 
 * @author David Chen
 *  
 */
public enum RuleAction {
	QUEUE_TO;
}
