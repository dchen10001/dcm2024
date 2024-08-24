package com.nice.dcm.distribution.rule;

public class OidRule implements Rule {
	private final String oid;
	
	
	public OidRule(String oid) {
		super();
		this.oid = oid;
	}


	public String getOid() {
		return oid;
	}

	@Override
	public RuleType getRuleType() {
		return RuleType.OID;
	}

}
