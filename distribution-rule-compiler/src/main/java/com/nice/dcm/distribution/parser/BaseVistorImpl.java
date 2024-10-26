package com.nice.dcm.distribution.parser;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.nice.dcm.distribution.parser.node.Node;

public abstract class BaseVistorImpl implements SkillRuleVistor {
	protected BaseVistorImpl() {

	}
	
    @Override
    public Node visit(ParseTree tree) {
        return null;
    }

    @Override
    public Node visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Node visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Node visitErrorNode(ErrorNode node) {
        return null;
    }
}
