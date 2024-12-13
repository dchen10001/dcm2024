// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.rule.parser;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, LESS_THAN=11, LESS_THAN_EQUAL=12, EQUAL=13, NOT_EQUAL=14, GREATER_THAN=15, 
		GREATER_THAN_EQUAL=16, NE1=17, NE2=18, IN=19, NOT_IN=20, LEAST_BUSY_OF=21, 
		QUEUE_TO=22, NUMBER=23, UUID_OR_HEXA=24, WHITESPACE=25;
	public static final int
		RULE_routingRuleSet = 0, RULE_routingWaitingRuleGroup = 1, RULE_routingRuleGroup = 2, 
		RULE_routingRule = 3, RULE_ruleAction = 4, RULE_orSkills = 5, RULE_skillOrSet = 6, 
		RULE_skillSet = 7, RULE_skill = 8, RULE_levelCondition = 9, RULE_binaryOperator = 10, 
		RULE_sqlOperator = 11, RULE_entity_identifier = 12, RULE_priority = 13, 
		RULE_waitRule = 14, RULE_queue_status = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"routingRuleSet", "routingWaitingRuleGroup", "routingRuleGroup", "routingRule", 
			"ruleAction", "orSkills", "skillOrSet", "skillSet", "skill", "levelCondition", 
			"binaryOperator", "sqlOperator", "entity_identifier", "priority", "waitRule", 
			"queue_status"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'('", "','", "')'", "'@S:'", "'level'", "'..'", "'with'", 
			"'priority'", "'wait'", "'<'", "'<='", "'='", null, "'>'", "'>='", "'!='", 
			"'<>'", "'in'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "LESS_THAN", 
			"LESS_THAN_EQUAL", "EQUAL", "NOT_EQUAL", "GREATER_THAN", "GREATER_THAN_EQUAL", 
			"NE1", "NE2", "IN", "NOT_IN", "LEAST_BUSY_OF", "QUEUE_TO", "NUMBER", 
			"UUID_OR_HEXA", "WHITESPACE"
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
			setState(32);
			routingRuleGroup();
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(33);
				routingWaitingRuleGroup();
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
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
			setState(41);
			waitRule();
			setState(42);
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
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44);
				routingRule();
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUEUE_TO );
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
		public OrSkillsContext orSkills() {
			return getRuleContext(OrSkillsContext.class,0);
		}
		public PriorityContext priority() {
			return getRuleContext(PriorityContext.class,0);
		}
		public Queue_statusContext queue_status() {
			return getRuleContext(Queue_statusContext.class,0);
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
			setState(49);
			ruleAction();
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEAST_BUSY_OF) {
				{
				setState(50);
				queue_status();
				}
			}

			setState(53);
			orSkills();
			setState(54);
			priority();
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(QUEUE_TO);
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
	public static class OrSkillsContext extends ParserRuleContext {
		public List<SkillOrSetContext> skillOrSet() {
			return getRuleContexts(SkillOrSetContext.class);
		}
		public SkillOrSetContext skillOrSet(int i) {
			return getRuleContext(SkillOrSetContext.class,i);
		}
		public OrSkillsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orSkills; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterOrSkills(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitOrSkills(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitOrSkills(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrSkillsContext orSkills() throws RecognitionException {
		OrSkillsContext _localctx = new OrSkillsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_orSkills);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			skillOrSet();
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(59);
				match(T__0);
				setState(60);
				skillOrSet();
				}
				}
				setState(65);
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
	public static class SkillOrSetContext extends ParserRuleContext {
		public SkillContext skill() {
			return getRuleContext(SkillContext.class,0);
		}
		public SkillSetContext skillSet() {
			return getRuleContext(SkillSetContext.class,0);
		}
		public SkillOrSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skillOrSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterSkillOrSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitSkillOrSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitSkillOrSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkillOrSetContext skillOrSet() throws RecognitionException {
		SkillOrSetContext _localctx = new SkillOrSetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_skillOrSet);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				skill();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				skillSet();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class SkillSetContext extends ParserRuleContext {
		public List<SkillContext> skill() {
			return getRuleContexts(SkillContext.class);
		}
		public SkillContext skill(int i) {
			return getRuleContext(SkillContext.class,i);
		}
		public SkillSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skillSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterSkillSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitSkillSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitSkillSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkillSetContext skillSet() throws RecognitionException {
		SkillSetContext _localctx = new SkillSetContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_skillSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__1);
			setState(71);
			skill();
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(72);
				match(T__2);
				setState(73);
				skill();
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(78);
			match(T__3);
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
		public LevelConditionContext levelCondition() {
			return getRuleContext(LevelConditionContext.class,0);
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
		enterRule(_localctx, 16, RULE_skill);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__4);
			setState(81);
			entity_identifier();
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(82);
				levelCondition();
				}
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
	public static class LevelConditionContext extends ParserRuleContext {
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(DistributionRulesParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(DistributionRulesParser.NUMBER, i);
		}
		public SqlOperatorContext sqlOperator() {
			return getRuleContext(SqlOperatorContext.class,0);
		}
		public LevelConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_levelCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterLevelCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitLevelCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitLevelCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LevelConditionContext levelCondition() throws RecognitionException {
		LevelConditionContext _localctx = new LevelConditionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_levelCondition);
		try {
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(T__5);
				setState(86);
				binaryOperator();
				setState(87);
				match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(T__5);
				setState(90);
				sqlOperator();
				setState(91);
				match(NUMBER);
				setState(92);
				match(T__6);
				setState(93);
				match(NUMBER);
				}
				break;
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
	public static class BinaryOperatorContext extends ParserRuleContext {
		public TerminalNode LESS_THAN() { return getToken(DistributionRulesParser.LESS_THAN, 0); }
		public TerminalNode LESS_THAN_EQUAL() { return getToken(DistributionRulesParser.LESS_THAN_EQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(DistributionRulesParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(DistributionRulesParser.NOT_EQUAL, 0); }
		public TerminalNode GREATER_THAN() { return getToken(DistributionRulesParser.GREATER_THAN, 0); }
		public TerminalNode GREATER_THAN_EQUAL() { return getToken(DistributionRulesParser.GREATER_THAN_EQUAL, 0); }
		public BinaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterBinaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitBinaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorContext binaryOperator() throws RecognitionException {
		BinaryOperatorContext _localctx = new BinaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_binaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 129024L) != 0)) ) {
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
	public static class SqlOperatorContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(DistributionRulesParser.IN, 0); }
		public TerminalNode NOT_IN() { return getToken(DistributionRulesParser.NOT_IN, 0); }
		public SqlOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterSqlOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitSqlOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitSqlOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlOperatorContext sqlOperator() throws RecognitionException {
		SqlOperatorContext _localctx = new SqlOperatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sqlOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==IN || _la==NOT_IN) ) {
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
		enterRule(_localctx, 24, RULE_entity_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
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
	public static class PriorityContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(DistributionRulesParser.NUMBER, 0); }
		public PriorityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_priority; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterPriority(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitPriority(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitPriority(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PriorityContext priority() throws RecognitionException {
		PriorityContext _localctx = new PriorityContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_priority);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__7);
			setState(104);
			match(T__8);
			setState(105);
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
		enterRule(_localctx, 28, RULE_waitRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(T__9);
			setState(108);
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
	public static class Queue_statusContext extends ParserRuleContext {
		public TerminalNode LEAST_BUSY_OF() { return getToken(DistributionRulesParser.LEAST_BUSY_OF, 0); }
		public Queue_statusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queue_status; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).enterQueue_status(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DistributionRulesListener ) ((DistributionRulesListener)listener).exitQueue_status(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DistributionRulesVisitor ) return ((DistributionRulesVisitor<? extends T>)visitor).visitQueue_status(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Queue_statusContext queue_status() throws RecognitionException {
		Queue_statusContext _localctx = new Queue_statusContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_queue_status);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(LEAST_BUSY_OF);
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
		"\u0004\u0001\u0019q\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0005\u0000#\b\u0000\n\u0000\f\u0000&\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0004\u0002.\b\u0002\u000b\u0002\f\u0002/\u0001\u0003\u0001\u0003\u0003"+
		"\u00034\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005>\b\u0005\n\u0005"+
		"\f\u0005A\t\u0005\u0001\u0006\u0001\u0006\u0003\u0006E\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007K\b\u0007\u000b\u0007"+
		"\f\u0007L\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\bT\b\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t`\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0000\u0000\u0010\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		"\u0000\u0003\u0001\u0000\u000b\u0010\u0001\u0000\u0013\u0014\u0001\u0000"+
		"\u0017\u0018h\u0000 \u0001\u0000\u0000\u0000\u0002)\u0001\u0000\u0000"+
		"\u0000\u0004-\u0001\u0000\u0000\u0000\u00061\u0001\u0000\u0000\u0000\b"+
		"8\u0001\u0000\u0000\u0000\n:\u0001\u0000\u0000\u0000\fD\u0001\u0000\u0000"+
		"\u0000\u000eF\u0001\u0000\u0000\u0000\u0010P\u0001\u0000\u0000\u0000\u0012"+
		"_\u0001\u0000\u0000\u0000\u0014a\u0001\u0000\u0000\u0000\u0016c\u0001"+
		"\u0000\u0000\u0000\u0018e\u0001\u0000\u0000\u0000\u001ag\u0001\u0000\u0000"+
		"\u0000\u001ck\u0001\u0000\u0000\u0000\u001en\u0001\u0000\u0000\u0000 "+
		"$\u0003\u0004\u0002\u0000!#\u0003\u0002\u0001\u0000\"!\u0001\u0000\u0000"+
		"\u0000#&\u0001\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000"+
		"\u0000\u0000%\'\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000\'(\u0005"+
		"\u0000\u0000\u0001(\u0001\u0001\u0000\u0000\u0000)*\u0003\u001c\u000e"+
		"\u0000*+\u0003\u0004\u0002\u0000+\u0003\u0001\u0000\u0000\u0000,.\u0003"+
		"\u0006\u0003\u0000-,\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000"+
		"/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000\u0005\u0001\u0000"+
		"\u0000\u000013\u0003\b\u0004\u000024\u0003\u001e\u000f\u000032\u0001\u0000"+
		"\u0000\u000034\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000056\u0003"+
		"\n\u0005\u000067\u0003\u001a\r\u00007\u0007\u0001\u0000\u0000\u000089"+
		"\u0005\u0016\u0000\u00009\t\u0001\u0000\u0000\u0000:?\u0003\f\u0006\u0000"+
		";<\u0005\u0001\u0000\u0000<>\u0003\f\u0006\u0000=;\u0001\u0000\u0000\u0000"+
		">A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000"+
		"\u0000@\u000b\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BE\u0003"+
		"\u0010\b\u0000CE\u0003\u000e\u0007\u0000DB\u0001\u0000\u0000\u0000DC\u0001"+
		"\u0000\u0000\u0000E\r\u0001\u0000\u0000\u0000FG\u0005\u0002\u0000\u0000"+
		"GJ\u0003\u0010\b\u0000HI\u0005\u0003\u0000\u0000IK\u0003\u0010\b\u0000"+
		"JH\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000"+
		"\u0000LM\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0005\u0004"+
		"\u0000\u0000O\u000f\u0001\u0000\u0000\u0000PQ\u0005\u0005\u0000\u0000"+
		"QS\u0003\u0018\f\u0000RT\u0003\u0012\t\u0000SR\u0001\u0000\u0000\u0000"+
		"ST\u0001\u0000\u0000\u0000T\u0011\u0001\u0000\u0000\u0000UV\u0005\u0006"+
		"\u0000\u0000VW\u0003\u0014\n\u0000WX\u0005\u0017\u0000\u0000X`\u0001\u0000"+
		"\u0000\u0000YZ\u0005\u0006\u0000\u0000Z[\u0003\u0016\u000b\u0000[\\\u0005"+
		"\u0017\u0000\u0000\\]\u0005\u0007\u0000\u0000]^\u0005\u0017\u0000\u0000"+
		"^`\u0001\u0000\u0000\u0000_U\u0001\u0000\u0000\u0000_Y\u0001\u0000\u0000"+
		"\u0000`\u0013\u0001\u0000\u0000\u0000ab\u0007\u0000\u0000\u0000b\u0015"+
		"\u0001\u0000\u0000\u0000cd\u0007\u0001\u0000\u0000d\u0017\u0001\u0000"+
		"\u0000\u0000ef\u0007\u0002\u0000\u0000f\u0019\u0001\u0000\u0000\u0000"+
		"gh\u0005\b\u0000\u0000hi\u0005\t\u0000\u0000ij\u0005\u0017\u0000\u0000"+
		"j\u001b\u0001\u0000\u0000\u0000kl\u0005\n\u0000\u0000lm\u0005\u0017\u0000"+
		"\u0000m\u001d\u0001\u0000\u0000\u0000no\u0005\u0015\u0000\u0000o\u001f"+
		"\u0001\u0000\u0000\u0000\b$/3?DLS_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}