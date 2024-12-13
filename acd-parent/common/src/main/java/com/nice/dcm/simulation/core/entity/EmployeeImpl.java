package com.nice.dcm.simulation.core.entity;

import java.util.List;
import java.util.Set;

import com.nice.dcm.simulation.core.IdEntityImpl;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeImpl extends IdEntityImpl implements Employee {

	private final String oid;
	
	private final Set<SkillLevel> skills;
	
	public EmployeeImpl(String oid) {
		this(oid, Set.of());
	}
	
	public EmployeeImpl(String oid, Set<SkillLevel> skills) {
		super();
		this.oid = oid;
		this.skills = skills;
	}
	
	@Override
	public String getOid() {
		return oid;
	}

	@Override
	public Set<SkillLevel> getCurrentSkills(long currentTime) {
		return null;
	}

	@Override
	public List<Contact> getHandlingContacts() {
		return null;
	}
}
