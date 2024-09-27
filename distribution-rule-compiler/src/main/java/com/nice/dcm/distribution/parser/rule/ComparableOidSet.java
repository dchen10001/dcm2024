package com.nice.dcm.distribution.parser.rule;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ComparableOidSet {
    private final Set<String> oids;
    
    public ComparableOidSet(Collection<String> oids) {
        this.oids = new TreeSet<>(oids);
    }
    
    public ComparableOidSet(String oid) {
        this.oids = new TreeSet<>();
        this.oids.add(oid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oids);
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
        return this.oids.equals(other.oids);
    }
}
