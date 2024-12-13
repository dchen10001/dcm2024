package com.nice.dcm.simulation.distribution.rule;

/**
 * The Enum QueueStatus is used to define the status of the skill queue
 * to be selected by the rule.
 *
 * DCM distribution rule only supports "least busy of", which means least busy of EWT skill queue.
 * @author David Chen
 */
public enum QueueStatus {
	LEAST_BUSY;
}
