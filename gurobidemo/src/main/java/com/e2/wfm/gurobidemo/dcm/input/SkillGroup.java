package com.e2.wfm.gurobidemo.dcm.input;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkillGroup extends Entity {
	private Set<Employee> employees;
	private Set<ContactType> contactTypes;

	public SkillGroup(long id, ContactType contactType) {
		super(id);
		this.contactTypes = new HashSet<>();
		this.contactTypes.add(contactType);
		this.employees = new HashSet<>();
	}
	
	public SkillGroup(long id, List<ContactType> contactTypes) {
		super(id);
		this.contactTypes = new HashSet<>();
		this.contactTypes.addAll(contactTypes);
		this.employees = new HashSet<>();
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public Collection<ContactType> getContactTypes() {
		return contactTypes;
	}

	public void addContactType(ContactType contactType) {
		this.contactTypes.add(contactType);
	}
}
