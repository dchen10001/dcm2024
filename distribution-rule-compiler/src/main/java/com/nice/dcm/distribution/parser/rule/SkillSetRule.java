package com.nice.dcm.distribution.parser.rule;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
public class SkillSetRule implements Node {
    private final Set<String> skillOids;
    @Override
    public NodeType getNodeType() {
        return NodeType.SKILLSETRULE;
    }
}
