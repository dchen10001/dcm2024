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
		T__0=1, T__1=2, T__2=3, T__3=4, LEAST_BUSY=5, HIGHER_RANKING=6, QUEUE_TO=7, 
		ROUTE_TO=8, NUMBER=9, UUID_OR_HEXA=10, WHITESPACE=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "LEAST_BUSY", "HIGHER_RANKING", "QUEUE_TO", 
			"ROUTE_TO", "NUMBER", "UUID_OR_HEXA", "WHITESPACE"
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
		"\u0004\u0000\u000b~\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0005"+
		"\bg\b\b\n\b\f\bj\t\b\u0001\t\u0004\tm\b\t\u000b\t\f\tn\u0001\t\u0003\t"+
		"r\b\t\u0004\tt\b\t\u000b\t\f\tu\u0001\n\u0004\ny\b\n\u000b\n\f\nz\u0001"+
		"\n\u0001\n\u0000\u0000\u000b\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0001\u0000\u0004\u0001\u000019\u0001\u000009\u0003\u000009AFaf\u0003"+
		"\u0000\t\n\r\r  \u0082\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0001\u0017\u0001\u0000"+
		"\u0000\u0000\u0003\u001b\u0001\u0000\u0000\u0000\u0005\u001f\u0001\u0000"+
		"\u0000\u0000\u0007-\u0001\u0000\u0000\u0000\t2\u0001\u0000\u0000\u0000"+
		"\u000b@\u0001\u0000\u0000\u0000\rR\u0001\u0000\u0000\u0000\u000f[\u0001"+
		"\u0000\u0000\u0000\u0011d\u0001\u0000\u0000\u0000\u0013s\u0001\u0000\u0000"+
		"\u0000\u0015x\u0001\u0000\u0000\u0000\u0017\u0018\u0005a\u0000\u0000\u0018"+
		"\u0019\u0005n\u0000\u0000\u0019\u001a\u0005d\u0000\u0000\u001a\u0002\u0001"+
		"\u0000\u0000\u0000\u001b\u001c\u0005@\u0000\u0000\u001c\u001d\u0005S\u0000"+
		"\u0000\u001d\u001e\u0005:\u0000\u0000\u001e\u0004\u0001\u0000\u0000\u0000"+
		"\u001f \u0005w\u0000\u0000 !\u0005i\u0000\u0000!\"\u0005t\u0000\u0000"+
		"\"#\u0005h\u0000\u0000#$\u0005 \u0000\u0000$%\u0005p\u0000\u0000%&\u0005"+
		"r\u0000\u0000&\'\u0005i\u0000\u0000\'(\u0005o\u0000\u0000()\u0005r\u0000"+
		"\u0000)*\u0005i\u0000\u0000*+\u0005t\u0000\u0000+,\u0005y\u0000\u0000"+
		",\u0006\u0001\u0000\u0000\u0000-.\u0005w\u0000\u0000./\u0005a\u0000\u0000"+
		"/0\u0005i\u0000\u000001\u0005t\u0000\u00001\b\u0001\u0000\u0000\u0000"+
		"23\u0005l\u0000\u000034\u0005e\u0000\u000045\u0005a\u0000\u000056\u0005"+
		"s\u0000\u000067\u0005t\u0000\u000078\u0005 \u0000\u000089\u0005b\u0000"+
		"\u00009:\u0005u\u0000\u0000:;\u0005s\u0000\u0000;<\u0005y\u0000\u0000"+
		"<=\u0005 \u0000\u0000=>\u0005o\u0000\u0000>?\u0005f\u0000\u0000?\n\u0001"+
		"\u0000\u0000\u0000@A\u0005h\u0000\u0000AB\u0005i\u0000\u0000BC\u0005g"+
		"\u0000\u0000CD\u0005h\u0000\u0000DE\u0005e\u0000\u0000EF\u0005r\u0000"+
		"\u0000FG\u0005 \u0000\u0000GH\u0005r\u0000\u0000HI\u0005a\u0000\u0000"+
		"IJ\u0005n\u0000\u0000JK\u0005k\u0000\u0000KL\u0005i\u0000\u0000LM\u0005"+
		"n\u0000\u0000MN\u0005g\u0000\u0000NO\u0005 \u0000\u0000OP\u0005o\u0000"+
		"\u0000PQ\u0005f\u0000\u0000Q\f\u0001\u0000\u0000\u0000RS\u0005q\u0000"+
		"\u0000ST\u0005u\u0000\u0000TU\u0005e\u0000\u0000UV\u0005u\u0000\u0000"+
		"VW\u0005e\u0000\u0000WX\u0005 \u0000\u0000XY\u0005t\u0000\u0000YZ\u0005"+
		"o\u0000\u0000Z\u000e\u0001\u0000\u0000\u0000[\\\u0005r\u0000\u0000\\]"+
		"\u0005o\u0000\u0000]^\u0005u\u0000\u0000^_\u0005t\u0000\u0000_`\u0005"+
		"e\u0000\u0000`a\u0005 \u0000\u0000ab\u0005t\u0000\u0000bc\u0005o\u0000"+
		"\u0000c\u0010\u0001\u0000\u0000\u0000dh\u0007\u0000\u0000\u0000eg\u0007"+
		"\u0001\u0000\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000i\u0012\u0001\u0000"+
		"\u0000\u0000jh\u0001\u0000\u0000\u0000km\u0007\u0002\u0000\u0000lk\u0001"+
		"\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000\u0000pr\u0005-\u0000\u0000"+
		"qp\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rt\u0001\u0000\u0000"+
		"\u0000sl\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000us\u0001\u0000"+
		"\u0000\u0000uv\u0001\u0000\u0000\u0000v\u0014\u0001\u0000\u0000\u0000"+
		"wy\u0007\u0003\u0000\u0000xw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000"+
		"\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|}\u0006\n\u0000\u0000}\u0016\u0001\u0000\u0000\u0000\u0006"+
		"\u0000hnquz\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}