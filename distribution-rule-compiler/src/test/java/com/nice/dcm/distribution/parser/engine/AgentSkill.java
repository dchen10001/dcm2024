package com.nice.dcm.distribution.parser.engine;

import lombok.Getter;

@Getter
public class AgentSkill {
	private String oid;
	private int level;
	
	public AgentSkill(String oid, int level) {
		this.oid = oid;
		this.level = level;
	}
}
