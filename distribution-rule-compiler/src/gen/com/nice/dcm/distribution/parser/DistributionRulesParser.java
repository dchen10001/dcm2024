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
		T__0=1, T__1=2, T__2=3, T__3=4, LEAST_BUSY=5, HIGHER_RANKING=6, QUEUE_TO=7, 
		ROUTE_TO=8, NUMBER=9, UUID_OR_HEXA=10, WHITESPACE=11;
	public static final int
		RULE_routingRuleSet = 0, RULE_routingWaitingRuleGroup = 1, RULE_routingRuleGroup = 2, 
		RULE_routingRule = 3, RULE_ruleAction = 4, RULE_andSkills = 5, RULE_skill = 6, 
		RULE_entity_identifier = 7, RULE_order = 8, RULE_waitRule = 9, RULE_agent_status = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"routingRuleSet", "routingWaitingRuleGroup", "routingRuleGroup", "routingRule", 
			"ruleAction", "andSkills", "skill", "entity_identifier", "order", "waitRule", 
			"agent_status"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'@S:'", "'with priority'", "'wait'", "'least busy of'", 
			"'higher ranking of'", "'queue to'", "'route to'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "LEAST_BUSY", "HIGHER_RANKING", "QUEUE_TO", 
			"ROUTE_TO", "NUMBER", "UUID_OR_HEXA", "WHITESPACE"
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
		public RoutingRuleGroupContext routingRuleGroup() {
			return getRuleContext(RoutingRuleGroupContext.class,0);
		}
		public TerminalNode EOF() { return getToken(DistributionRulesParser.EOF, 0); }
		public List<RoutingWaitingRuleGroupContext> routingWaitingRuleGroup() {
			return getRuleContexts(RoutingWaitingRuleGroupContext.class);
		}
		public RoutingWaitingRuleGroupContext routingWaitingRuleGroup(int i) {
			return getRuleContext(RoutingWaitingRuleGroupContext.class,i);
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
			setState(22);
			routingRuleGroup();
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(23);
				routingWaitingRuleGroup();
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
			match(EOF);
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
	public static class RoutingWaitingRuleGroupContext extends ParserRuleContext {
		public WaitRuleContext waitRule() {
			return getRuleContext(WaitRuleContext.class,0);
		}
		public RoutingRuleGroupContext routingRuleGroup() {
			return getRuleContext(RoutingRuleGroupContext.class,0);
		}
		public RoutingWaitingRuleGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routingWaitingRuleGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterRoutingWaitingRuleGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitRoutingWaitingRuleGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitRoutingWaitingRuleGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoutingWaitingRuleGroupContext routingWaitingRuleGroup() throws RecognitionException {
		RoutingWaitingRuleGroupContext _localctx = new RoutingWaitingRuleGroupContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_routingWaitingRuleGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			waitRule();
			setState(32);
			routingRuleGroup();
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
		enterRule(_localctx, 4, RULE_routingRuleGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				routingRule();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUEUE_TO || _la==ROUTE_TO );
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
		public Agent_statusContext agent_status() {
			return getRuleContext(Agent_statusContext.class,0);
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
		enterRule(_localctx, 6, RULE_routingRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			ruleAction();
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEAST_BUSY || _la==HIGHER_RANKING) {
				{
				setState(40);
				agent_status();
				}
			}

			setState(43);
			andSkills();
			setState(44);
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
		public TerminalNode QUEUE_TO() { return getToken(DistributionRulesParser.QUEUE_TO, 0); }
		public TerminalNode ROUTE_TO() { return getToken(DistributionRulesParser.ROUTE_TO, 0); }
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
		enterRule(_localctx, 8, RULE_ruleAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_la = _input.LA(1);
			if ( !(_la==QUEUE_TO || _la==ROUTE_TO) ) {
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
		enterRule(_localctx, 10, RULE_andSkills);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			skill();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(49);
				match(T__0);
				setState(50);
				skill();
				}
				}
				setState(55);
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
		enterRule(_localctx, 12, RULE_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__1);
			setState(57);
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
		enterRule(_localctx, 14, RULE_entity_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
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
		enterRule(_localctx, 16, RULE_order);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__2);
			setState(62);
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
		enterRule(_localctx, 18, RULE_waitRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__3);
			setState(65);
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
	public static class Agent_statusContext extends ParserRuleContext {
		public TerminalNode LEAST_BUSY() { return getToken(DistributionRulesParser.LEAST_BUSY, 0); }
		public TerminalNode HIGHER_RANKING() { return getToken(DistributionRulesParser.HIGHER_RANKING, 0); }
		public Agent_statusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agent_status; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterAgent_status(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitAgent_status(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitAgent_status(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Agent_statusContext agent_status() throws RecognitionException {
		Agent_statusContext _localctx = new Agent_statusContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_agent_status);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !(_la==LEAST_BUSY || _la==HIGHER_RANKING) ) {
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

	public static final String _serializedATN =
		"\u0004\u0001\u000bF\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000\u0005"+
		"\u0000\u0019\b\u0000\n\u0000\f\u0000\u001c\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0004\u0002$\b\u0002"+
		"\u000b\u0002\f\u0002%\u0001\u0003\u0001\u0003\u0003\u0003*\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u00054\b\u0005\n\u0005\f\u00057\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0000\u0000\u000b"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0000\u0003\u0001"+
		"\u0000\u0007\b\u0001\u0000\t\n\u0001\u0000\u0005\u0006>\u0000\u0016\u0001"+
		"\u0000\u0000\u0000\u0002\u001f\u0001\u0000\u0000\u0000\u0004#\u0001\u0000"+
		"\u0000\u0000\u0006\'\u0001\u0000\u0000\u0000\b.\u0001\u0000\u0000\u0000"+
		"\n0\u0001\u0000\u0000\u0000\f8\u0001\u0000\u0000\u0000\u000e;\u0001\u0000"+
		"\u0000\u0000\u0010=\u0001\u0000\u0000\u0000\u0012@\u0001\u0000\u0000\u0000"+
		"\u0014C\u0001\u0000\u0000\u0000\u0016\u001a\u0003\u0004\u0002\u0000\u0017"+
		"\u0019\u0003\u0002\u0001\u0000\u0018\u0017\u0001\u0000\u0000\u0000\u0019"+
		"\u001c\u0001\u0000\u0000\u0000\u001a\u0018\u0001\u0000\u0000\u0000\u001a"+
		"\u001b\u0001\u0000\u0000\u0000\u001b\u001d\u0001\u0000\u0000\u0000\u001c"+
		"\u001a\u0001\u0000\u0000\u0000\u001d\u001e\u0005\u0000\u0000\u0001\u001e"+
		"\u0001\u0001\u0000\u0000\u0000\u001f \u0003\u0012\t\u0000 !\u0003\u0004"+
		"\u0002\u0000!\u0003\u0001\u0000\u0000\u0000\"$\u0003\u0006\u0003\u0000"+
		"#\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000"+
		"\u0000%&\u0001\u0000\u0000\u0000&\u0005\u0001\u0000\u0000\u0000\')\u0003"+
		"\b\u0004\u0000(*\u0003\u0014\n\u0000)(\u0001\u0000\u0000\u0000)*\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0003\n\u0005\u0000,-\u0003"+
		"\u0010\b\u0000-\u0007\u0001\u0000\u0000\u0000./\u0007\u0000\u0000\u0000"+
		"/\t\u0001\u0000\u0000\u000005\u0003\f\u0006\u000012\u0005\u0001\u0000"+
		"\u000024\u0003\f\u0006\u000031\u0001\u0000\u0000\u000047\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006\u000b\u0001"+
		"\u0000\u0000\u000075\u0001\u0000\u0000\u000089\u0005\u0002\u0000\u0000"+
		"9:\u0003\u000e\u0007\u0000:\r\u0001\u0000\u0000\u0000;<\u0007\u0001\u0000"+
		"\u0000<\u000f\u0001\u0000\u0000\u0000=>\u0005\u0003\u0000\u0000>?\u0005"+
		"\t\u0000\u0000?\u0011\u0001\u0000\u0000\u0000@A\u0005\u0004\u0000\u0000"+
		"AB\u0005\t\u0000\u0000B\u0013\u0001\u0000\u0000\u0000CD\u0007\u0002\u0000"+
		"\u0000D\u0015\u0001\u0000\u0000\u0000\u0004\u001a%)5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}