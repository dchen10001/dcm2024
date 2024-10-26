package com.nice.dcm.distribution.parser.engine;

import java.util.List;
import java.util.Set;

import com.nice.dcm.distribution.parser.rule.ComparableOidSet;
import com.nice.dcm.distribution.parser.rule.SkillLevelCondition;

public class ACDRoutingServiceImpl implements ACDRoutingService {
	private List<Agent> agents;
	
	public ACDRoutingServiceImpl(List<Agent> agents) {
		this.agents = agents;
	}
	
	@Override
	public String findAgent(Set<ComparableOidSet> skills) {
		for (Agent agent : agents) {
			if (match(agent, skills)) {
				return agent.getOid();
			}
		}
		return null;
	}

	@Override
	public String findAgent(ComparableOidSet skill) {
		for (Agent agent : agents) {
			if (match(agent, skill)) {
				return agent.getOid();
			}
		}
		return null;
	}

	@Override
	public ComparableOidSet getLeastBusyQueue(Set<ComparableOidSet> skills, long arrivalTime, long waitTime) {
		if(skills.size() < 2) {
			throw new IllegalArgumentException("skills should have at least 2 elements");
		}
		//return the first for test purpose.
		return skills.iterator().next();
	}
	
	private boolean match(Agent agent, Set<ComparableOidSet> skills) {
		for(ComparableOidSet skill : skills) {
            if (match(agent, skill) ) {
                return true;
            }
        }
		return false;
	}
	
	private boolean match(Agent agent, ComparableOidSet skill) {
		for (SkillLevelCondition skillLevelCondition : skill.getSkillLevelConditions()) {
			String skillOid = skillLevelCondition.getSkillOid();
			int level = agent.getSkillLevel(skillOid);
			if (level > 0 && skillLevelCondition.evaluate(skillOid, level)) {
				return true;
			}
		}
		return false;
	}
}
