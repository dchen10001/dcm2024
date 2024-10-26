package com.nice.dcm.distribution.parser.engine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Agent {
	private final String oid;
	private final List<AgentSkill> skills;
	private final Map<String, Integer> skillToLevelMap;
		
	public Agent(String oid, List<AgentSkill> skills) {
		this.oid = oid;
		this.skills = skills;
		this.skillToLevelMap = skills.stream().collect(Collectors.toMap(AgentSkill::getOid, AgentSkill::getLevel));
	}
	
	public int getSkillLevel(String skillOid) {
		if(skillToLevelMap.containsKey(skillOid)) {
            return skillToLevelMap.get(skillOid);
        }
		return -1;
	}
}
