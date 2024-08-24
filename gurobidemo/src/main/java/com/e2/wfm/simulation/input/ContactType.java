package com.e2.wfm.simulation.input;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class ContactType extends IdName {
	private final List<ContactTypeInterval> intervals;
	
	@JsonIgnore
	private final DistributionScript defaultDistributionScript;
	
	@Builder.Default
	private final List<DistributionScriptByPeriod> distributionScriptsByPeriods = List.of();;

	private final ServiceTargetsByPeriod defaultServiceTarget;
	
	@Builder.Default
	private final List<ServiceTargetsByPeriod> serviceTargetsByPeriods = List.of();

	@JsonCreator
	public ContactType(@NonNull @JsonProperty("oid") String oid, 
			@JsonProperty("id") long id, 
			@JsonProperty("name") String name, 
			@JsonProperty("intervals") List<ContactTypeInterval> intervals, 
			@JsonProperty("defaultDistributionName") String namedefaultDistributionName,
			@NonNull @JsonProperty("defaultDistributionScript") String defaultDistributionScript, 
			@JsonProperty("distributionScriptsByPeriods") List<DistributionScriptByPeriod> distributionScriptsByPeriods,
			@NonNull @JsonProperty("defaultServiceTarget") ServiceTargetsByPeriod defaultServiceTarget,
			@JsonProperty("serviceTargetsByPeriods") List<ServiceTargetsByPeriod> serviceTargetsByPeriods) {
		super(oid, id, name);
		this.intervals = intervals;
		this.defaultDistributionScript = new DistributionScript(namedefaultDistributionName, defaultDistributionScript);
		this.distributionScriptsByPeriods = distributionScriptsByPeriods == null ? List.of() : distributionScriptsByPeriods;
		
		this.defaultServiceTarget = defaultServiceTarget;
		this.serviceTargetsByPeriods = serviceTargetsByPeriods == null ? List.of() : serviceTargetsByPeriods;
	}
	
	public String getDefaultDistributionName() {
		return defaultDistributionScript.getName();
	}
	
	public String getdefaultDistributionScript() {
		return defaultDistributionScript.getScript();
	}
}
