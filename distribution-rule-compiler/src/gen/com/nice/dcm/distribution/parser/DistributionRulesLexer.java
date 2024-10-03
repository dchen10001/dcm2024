// Generated from DistributionRules.g4 by ANTLR 4.13.1
package com.nice.dcm.distribution.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DistributionRulesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		LESS_THAN=10, LESS_THAN_EQUAL=11, EQUAL=12, NOT_EQUAL=13, GREATER_THAN=14, 
		GREATER_THAN_EQUAL=15, IN=16, NOT_IN=17, LEAST_BUSY=18, QUEUE_TO=19, NUMBER=20, 
		UUID_OR_HEXA=21, WHITESPACE=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"LESS_THAN", "LESS_THAN_EQUAL", "EQUAL", "NOT_EQUAL", "GREATER_THAN", 
			"GREATER_THAN_EQUAL", "IN", "NOT_IN", "LEAST_BUSY", "QUEUE_TO", "NUMBER", 
			"UUID_OR_HEXA", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'('", "','", "')'", "'@S:'", "'level'", "'..'", "'with priority'", 
			"'wait'", "'<'", "'<='", "'='", null, "'>'", "'>='", "'in'", "'not in'", 
			"'least busy of'", "'queue to'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "LESS_THAN", 
			"LESS_THAN_EQUAL", "EQUAL", "NOT_EQUAL", "GREATER_THAN", "GREATER_THAN_EQUAL", 
			"IN", "NOT_IN", "LEAST_BUSY", "QUEUE_TO", "NUMBER", "UUID_OR_HEXA", "WHITESPACE"
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


	public DistributionRulesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DistributionRules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0016\u00a4\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\fc\b\f\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u008d\b\u0013\n\u0013\f\u0013\u0090\t\u0013\u0001\u0014"+
		"\u0004\u0014\u0093\b\u0014\u000b\u0014\f\u0014\u0094\u0001\u0014\u0003"+
		"\u0014\u0098\b\u0014\u0004\u0014\u009a\b\u0014\u000b\u0014\f\u0014\u009b"+
		"\u0001\u0015\u0004\u0015\u009f\b\u0015\u000b\u0015\f\u0015\u00a0\u0001"+
		"\u0015\u0001\u0015\u0000\u0000\u0016\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016\u0001\u0000\u0004\u0001\u000019\u0001\u0000"+
		"09\u0003\u000009AFaf\u0003\u0000\t\n\r\r  \u00a9\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0001-\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u0005"+
		"3\u0001\u0000\u0000\u0000\u00075\u0001\u0000\u0000\u0000\t7\u0001\u0000"+
		"\u0000\u0000\u000b;\u0001\u0000\u0000\u0000\rA\u0001\u0000\u0000\u0000"+
		"\u000fD\u0001\u0000\u0000\u0000\u0011R\u0001\u0000\u0000\u0000\u0013W"+
		"\u0001\u0000\u0000\u0000\u0015Y\u0001\u0000\u0000\u0000\u0017\\\u0001"+
		"\u0000\u0000\u0000\u0019b\u0001\u0000\u0000\u0000\u001bd\u0001\u0000\u0000"+
		"\u0000\u001df\u0001\u0000\u0000\u0000\u001fi\u0001\u0000\u0000\u0000!"+
		"l\u0001\u0000\u0000\u0000#s\u0001\u0000\u0000\u0000%\u0081\u0001\u0000"+
		"\u0000\u0000\'\u008a\u0001\u0000\u0000\u0000)\u0099\u0001\u0000\u0000"+
		"\u0000+\u009e\u0001\u0000\u0000\u0000-.\u0005a\u0000\u0000./\u0005n\u0000"+
		"\u0000/0\u0005d\u0000\u00000\u0002\u0001\u0000\u0000\u000012\u0005(\u0000"+
		"\u00002\u0004\u0001\u0000\u0000\u000034\u0005,\u0000\u00004\u0006\u0001"+
		"\u0000\u0000\u000056\u0005)\u0000\u00006\b\u0001\u0000\u0000\u000078\u0005"+
		"@\u0000\u000089\u0005S\u0000\u00009:\u0005:\u0000\u0000:\n\u0001\u0000"+
		"\u0000\u0000;<\u0005l\u0000\u0000<=\u0005e\u0000\u0000=>\u0005v\u0000"+
		"\u0000>?\u0005e\u0000\u0000?@\u0005l\u0000\u0000@\f\u0001\u0000\u0000"+
		"\u0000AB\u0005.\u0000\u0000BC\u0005.\u0000\u0000C\u000e\u0001\u0000\u0000"+
		"\u0000DE\u0005w\u0000\u0000EF\u0005i\u0000\u0000FG\u0005t\u0000\u0000"+
		"GH\u0005h\u0000\u0000HI\u0005 \u0000\u0000IJ\u0005p\u0000\u0000JK\u0005"+
		"r\u0000\u0000KL\u0005i\u0000\u0000LM\u0005o\u0000\u0000MN\u0005r\u0000"+
		"\u0000NO\u0005i\u0000\u0000OP\u0005t\u0000\u0000PQ\u0005y\u0000\u0000"+
		"Q\u0010\u0001\u0000\u0000\u0000RS\u0005w\u0000\u0000ST\u0005a\u0000\u0000"+
		"TU\u0005i\u0000\u0000UV\u0005t\u0000\u0000V\u0012\u0001\u0000\u0000\u0000"+
		"WX\u0005<\u0000\u0000X\u0014\u0001\u0000\u0000\u0000YZ\u0005<\u0000\u0000"+
		"Z[\u0005=\u0000\u0000[\u0016\u0001\u0000\u0000\u0000\\]\u0005=\u0000\u0000"+
		"]\u0018\u0001\u0000\u0000\u0000^_\u0005<\u0000\u0000_c\u0005>\u0000\u0000"+
		"`a\u0005!\u0000\u0000ac\u0005=\u0000\u0000b^\u0001\u0000\u0000\u0000b"+
		"`\u0001\u0000\u0000\u0000c\u001a\u0001\u0000\u0000\u0000de\u0005>\u0000"+
		"\u0000e\u001c\u0001\u0000\u0000\u0000fg\u0005>\u0000\u0000gh\u0005=\u0000"+
		"\u0000h\u001e\u0001\u0000\u0000\u0000ij\u0005i\u0000\u0000jk\u0005n\u0000"+
		"\u0000k \u0001\u0000\u0000\u0000lm\u0005n\u0000\u0000mn\u0005o\u0000\u0000"+
		"no\u0005t\u0000\u0000op\u0005 \u0000\u0000pq\u0005i\u0000\u0000qr\u0005"+
		"n\u0000\u0000r\"\u0001\u0000\u0000\u0000st\u0005l\u0000\u0000tu\u0005"+
		"e\u0000\u0000uv\u0005a\u0000\u0000vw\u0005s\u0000\u0000wx\u0005t\u0000"+
		"\u0000xy\u0005 \u0000\u0000yz\u0005b\u0000\u0000z{\u0005u\u0000\u0000"+
		"{|\u0005s\u0000\u0000|}\u0005y\u0000\u0000}~\u0005 \u0000\u0000~\u007f"+
		"\u0005o\u0000\u0000\u007f\u0080\u0005f\u0000\u0000\u0080$\u0001\u0000"+
		"\u0000\u0000\u0081\u0082\u0005q\u0000\u0000\u0082\u0083\u0005u\u0000\u0000"+
		"\u0083\u0084\u0005e\u0000\u0000\u0084\u0085\u0005u\u0000\u0000\u0085\u0086"+
		"\u0005e\u0000\u0000\u0086\u0087\u0005 \u0000\u0000\u0087\u0088\u0005t"+
		"\u0000\u0000\u0088\u0089\u0005o\u0000\u0000\u0089&\u0001\u0000\u0000\u0000"+
		"\u008a\u008e\u0007\u0000\u0000\u0000\u008b\u008d\u0007\u0001\u0000\u0000"+
		"\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000"+
		"\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000"+
		"\u008f(\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091"+
		"\u0093\u0007\u0002\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093"+
		"\u0094\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0001\u0000\u0000\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096"+
		"\u0098\u0005-\u0000\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u009a\u0001\u0000\u0000\u0000\u0099\u0092"+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u0099"+
		"\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c*\u0001"+
		"\u0000\u0000\u0000\u009d\u009f\u0007\u0003\u0000\u0000\u009e\u009d\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u009e\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0006\u0015\u0000\u0000\u00a3,\u0001\u0000"+
		"\u0000\u0000\u0007\u0000b\u008e\u0094\u0097\u009b\u00a0\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}