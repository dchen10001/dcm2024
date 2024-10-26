package com.nice.dcm.distribution.parser.engine;

import java.util.Set;

import com.nice.dcm.distribution.parser.rule.ComparableOidSet;

public interface ACDRoutingService {
	String findAgent(Set<ComparableOidSet> skills);
	String findAgent(ComparableOidSet skill);
	ComparableOidSet getLeastBusyQueue(Set<ComparableOidSet> skills, long arrivalTime, long waitTime);	
}
