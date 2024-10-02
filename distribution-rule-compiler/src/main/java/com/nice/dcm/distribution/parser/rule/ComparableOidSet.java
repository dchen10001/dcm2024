package com.nice.dcm.distribution.parser.rule;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ComparableOidSet {
    private final Set<SkillLevelCondition> skillLevelConditions;
    
    public ComparableOidSet(Collection<SkillLevelCondition> skillLevelConditions) {
        this.skillLevelConditions = new TreeSet<>(skillLevelConditions);
    }
    
    public ComparableOidSet(SkillLevelCondition skillLevelCondition) {
        this.skillLevelConditions = new TreeSet<>();
        this.skillLevelConditions.add(skillLevelCondition);
    }
    
    public ComparableOidSet(String skillOid) {
        this.skillLevelConditions = new TreeSet<>();
        this.skillLevelConditions.add(new SkillLevelCondition(skillOid));
    }
    
    public ComparableOidSet(List<String> skillOids) {
        this.skillLevelConditions = new TreeSet<>();
        for (String skillOid : skillOids) {
            this.skillLevelConditions.add(new SkillLevelCondition(skillOid));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillLevelConditions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComparableOidSet other = (ComparableOidSet) obj;
        return this.skillLevelConditions.equals(other.skillLevelConditions);
    }
}
