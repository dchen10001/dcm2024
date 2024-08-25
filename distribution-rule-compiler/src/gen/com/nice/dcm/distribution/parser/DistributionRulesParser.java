// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class DistributionRulesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUMBER=6, UUID_OR_HEXA=7, WHITESPACE=8;
	public static final int
		RULE_routingRuleSet = 0, RULE_routingRuleGroup = 1, RULE_routingRule = 2, 
		RULE_ruleAction = 3, RULE_andSkills = 4, RULE_skill = 5, RULE_entity_identifier = 6, 
		RULE_order = 7, RULE_waitRule = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"routingRuleSet", "routingRuleGroup", "routingRule", "ruleAction", "andSkills", 
			"skill", "entity_identifier", "order", "waitRule"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'queue to'", "'and'", "'@S:'", "'with priority'", "'wait'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "NUMBER", "UUID_OR_HEXA", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DistributionRules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DistributionRulesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoutingRuleSetContext extends ParserRuleContext {
		public List<RoutingRuleGroupContext> routingRuleGroup() {
			return getRuleContexts(RoutingRuleGroupContext.class);
		}
		public RoutingRuleGroupContext routingRuleGroup(int i) {
			return getRuleContext(RoutingRuleGroupContext.class,i);
		}
		public List<WaitRuleContext> waitRule() {
			return getRuleContexts(WaitRuleContext.class);
		}
		public WaitRuleContext waitRule(int i) {
			return getRuleContext(WaitRuleContext.class,i);
		}
		public RoutingRuleSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routingRuleSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterRoutingRuleSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitRoutingRuleSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitRoutingRuleSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoutingRuleSetContext routingRuleSet() throws RecognitionException {
		RoutingRuleSetContext _localctx = new RoutingRuleSetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_routingRuleSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			routingRuleGroup();
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(19);
				waitRule();
				setState(20);
				routingRuleGroup();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoutingRuleGroupContext extends ParserRuleContext {
		public List<RoutingRuleContext> routingRule() {
			return getRuleContexts(RoutingRuleContext.class);
		}
		public RoutingRuleContext routingRule(int i) {
			return getRuleContext(RoutingRuleContext.class,i);
		}
		public RoutingRuleGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routingRuleGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterRoutingRuleGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitRoutingRuleGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitRoutingRuleGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoutingRuleGroupContext routingRuleGroup() throws RecognitionException {
		RoutingRuleGroupContext _localctx = new RoutingRuleGroupContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_routingRuleGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				routingRule();
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoutingRuleContext extends ParserRuleContext {
		public RuleActionContext ruleAction() {
			return getRuleContext(RuleActionContext.class,0);
		}
		public AndSkillsContext andSkills() {
			return getRuleContext(AndSkillsContext.class,0);
		}
		public OrderContext order() {
			return getRuleContext(OrderContext.class,0);
		}
		public RoutingRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routingRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterRoutingRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitRoutingRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitRoutingRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoutingRuleContext routingRule() throws RecognitionException {
		RoutingRuleContext _localctx = new RoutingRuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_routingRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			ruleAction();
			setState(33);
			andSkills();
			setState(34);
			order();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActionContext extends ParserRuleContext {
		public RuleActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterRuleAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitRuleAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitRuleAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActionContext ruleAction() throws RecognitionException {
		RuleActionContext _localctx = new RuleActionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ruleAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AndSkillsContext extends ParserRuleContext {
		public List<SkillContext> skill() {
			return getRuleContexts(SkillContext.class);
		}
		public SkillContext skill(int i) {
			return getRuleContext(SkillContext.class,i);
		}
		public AndSkillsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andSkills; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterAndSkills(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitAndSkills(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitAndSkills(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndSkillsContext andSkills() throws RecognitionException {
		AndSkillsContext _localctx = new AndSkillsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_andSkills);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			skill();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(39);
				match(T__1);
				setState(40);
				skill();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SkillContext extends ParserRuleContext {
		public Entity_identifierContext entity_identifier() {
			return getRuleContext(Entity_identifierContext.class,0);
		}
		public SkillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skill; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterSkill(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitSkill(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitSkill(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkillContext skill() throws RecognitionException {
		SkillContext _localctx = new SkillContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__2);
			setState(47);
			entity_identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Entity_identifierContext extends ParserRuleContext {
		public TerminalNode UUID_OR_HEXA() { return getToken(DistributionRulesParser.UUID_OR_HEXA, 0); }
		public TerminalNode NUMBER() { return getToken(DistributionRulesParser.NUMBER, 0); }
		public Entity_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterEntity_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitEntity_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitEntity_identifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Entity_identifierContext entity_identifier() throws RecognitionException {
		Entity_identifierContext _localctx = new Entity_identifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_entity_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_la = _input.LA(1);
			if ( !(_la==NUMBER || _la==UUID_OR_HEXA) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(DistributionRulesParser.NUMBER, 0); }
		public OrderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitOrder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitOrder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderContext order() throws RecognitionException {
		OrderContext _localctx = new OrderContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_order);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(T__3);
			setState(52);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WaitRuleContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(DistributionRulesParser.NUMBER, 0); }
		public WaitRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_waitRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterWaitRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitWaitRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitWaitRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WaitRuleContext waitRule() throws RecognitionException {
		WaitRuleContext _localctx = new WaitRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_waitRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__4);
			setState(55);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\b:\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"\u0017\b\u0000\n\u0000\f\u0000\u001a\t\u0000\u0001\u0001\u0004\u0001\u001d"+
		"\b\u0001\u000b\u0001\f\u0001\u001e\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004*\b\u0004\n\u0004\f\u0004-\t\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0000\u0000\t\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0000\u0001\u0001\u0000\u0006\u00073\u0000\u0012\u0001"+
		"\u0000\u0000\u0000\u0002\u001c\u0001\u0000\u0000\u0000\u0004 \u0001\u0000"+
		"\u0000\u0000\u0006$\u0001\u0000\u0000\u0000\b&\u0001\u0000\u0000\u0000"+
		"\n.\u0001\u0000\u0000\u0000\f1\u0001\u0000\u0000\u0000\u000e3\u0001\u0000"+
		"\u0000\u0000\u00106\u0001\u0000\u0000\u0000\u0012\u0018\u0003\u0002\u0001"+
		"\u0000\u0013\u0014\u0003\u0010\b\u0000\u0014\u0015\u0003\u0002\u0001\u0000"+
		"\u0015\u0017\u0001\u0000\u0000\u0000\u0016\u0013\u0001\u0000\u0000\u0000"+
		"\u0017\u001a\u0001\u0000\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000"+
		"\u0018\u0019\u0001\u0000\u0000\u0000\u0019\u0001\u0001\u0000\u0000\u0000"+
		"\u001a\u0018\u0001\u0000\u0000\u0000\u001b\u001d\u0003\u0004\u0002\u0000"+
		"\u001c\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000"+
		"\u001e\u001c\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000"+
		"\u001f\u0003\u0001\u0000\u0000\u0000 !\u0003\u0006\u0003\u0000!\"\u0003"+
		"\b\u0004\u0000\"#\u0003\u000e\u0007\u0000#\u0005\u0001\u0000\u0000\u0000"+
		"$%\u0005\u0001\u0000\u0000%\u0007\u0001\u0000\u0000\u0000&+\u0003\n\u0005"+
		"\u0000\'(\u0005\u0002\u0000\u0000(*\u0003\n\u0005\u0000)\'\u0001\u0000"+
		"\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001"+
		"\u0000\u0000\u0000,\t\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000"+
		"./\u0005\u0003\u0000\u0000/0\u0003\f\u0006\u00000\u000b\u0001\u0000\u0000"+
		"\u000012\u0007\u0000\u0000\u00002\r\u0001\u0000\u0000\u000034\u0005\u0004"+
		"\u0000\u000045\u0005\u0006\u0000\u00005\u000f\u0001\u0000\u0000\u0000"+
		"67\u0005\u0005\u0000\u000078\u0005\u0006\u0000\u00008\u0011\u0001\u0000"+
		"\u0000\u0000\u0003\u0018\u001e+";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}