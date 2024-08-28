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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, AGENT_STATUS=6, NUMBER=7, UUID_OR_HEXA=8, 
		WHITESPACE=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "AGENT_STATUS", "NUMBER", "UUID_OR_HEXA", 
			"WHITESPACE"
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
			null, null, null, null, null, null, "AGENT_STATUS", "NUMBER", "UUID_OR_HEXA", 
			"WHITESPACE"
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
		"\u0004\u0000\tq\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0005\u0006Z\b\u0006\n\u0006\f\u0006]\t\u0006\u0001\u0007\u0004"+
		"\u0007`\b\u0007\u000b\u0007\f\u0007a\u0001\u0007\u0003\u0007e\b\u0007"+
		"\u0004\u0007g\b\u0007\u000b\u0007\f\u0007h\u0001\b\u0004\bl\b\b\u000b"+
		"\b\f\bm\u0001\b\u0001\b\u0000\u0000\t\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0001\u0000\u0004"+
		"\u0001\u000019\u0001\u000009\u0003\u000009AFaf\u0003\u0000\t\n\r\r  v"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0001\u0013\u0001\u0000\u0000\u0000\u0003\u001c"+
		"\u0001\u0000\u0000\u0000\u0005 \u0001\u0000\u0000\u0000\u0007$\u0001\u0000"+
		"\u0000\u0000\t2\u0001\u0000\u0000\u0000\u000bU\u0001\u0000\u0000\u0000"+
		"\rW\u0001\u0000\u0000\u0000\u000ff\u0001\u0000\u0000\u0000\u0011k\u0001"+
		"\u0000\u0000\u0000\u0013\u0014\u0005q\u0000\u0000\u0014\u0015\u0005u\u0000"+
		"\u0000\u0015\u0016\u0005e\u0000\u0000\u0016\u0017\u0005u\u0000\u0000\u0017"+
		"\u0018\u0005e\u0000\u0000\u0018\u0019\u0005 \u0000\u0000\u0019\u001a\u0005"+
		"t\u0000\u0000\u001a\u001b\u0005o\u0000\u0000\u001b\u0002\u0001\u0000\u0000"+
		"\u0000\u001c\u001d\u0005a\u0000\u0000\u001d\u001e\u0005n\u0000\u0000\u001e"+
		"\u001f\u0005d\u0000\u0000\u001f\u0004\u0001\u0000\u0000\u0000 !\u0005"+
		"@\u0000\u0000!\"\u0005S\u0000\u0000\"#\u0005:\u0000\u0000#\u0006\u0001"+
		"\u0000\u0000\u0000$%\u0005w\u0000\u0000%&\u0005i\u0000\u0000&\'\u0005"+
		"t\u0000\u0000\'(\u0005h\u0000\u0000()\u0005 \u0000\u0000)*\u0005p\u0000"+
		"\u0000*+\u0005r\u0000\u0000+,\u0005i\u0000\u0000,-\u0005o\u0000\u0000"+
		"-.\u0005r\u0000\u0000./\u0005i\u0000\u0000/0\u0005t\u0000\u000001\u0005"+
		"y\u0000\u00001\b\u0001\u0000\u0000\u000023\u0005w\u0000\u000034\u0005"+
		"a\u0000\u000045\u0005i\u0000\u000056\u0005t\u0000\u00006\n\u0001\u0000"+
		"\u0000\u000078\u0005l\u0000\u000089\u0005e\u0000\u00009:\u0005a\u0000"+
		"\u0000:;\u0005s\u0000\u0000;<\u0005t\u0000\u0000<=\u0005 \u0000\u0000"+
		"=>\u0005b\u0000\u0000>?\u0005u\u0000\u0000?@\u0005s\u0000\u0000@A\u0005"+
		"y\u0000\u0000AB\u0005 \u0000\u0000BC\u0005o\u0000\u0000CV\u0005f\u0000"+
		"\u0000DE\u0005h\u0000\u0000EF\u0005i\u0000\u0000FG\u0005g\u0000\u0000"+
		"GH\u0005h\u0000\u0000HI\u0005e\u0000\u0000IJ\u0005r\u0000\u0000JK\u0005"+
		" \u0000\u0000KL\u0005r\u0000\u0000LM\u0005a\u0000\u0000MN\u0005n\u0000"+
		"\u0000NO\u0005k\u0000\u0000OP\u0005i\u0000\u0000PQ\u0005n\u0000\u0000"+
		"QR\u0005g\u0000\u0000RS\u0005 \u0000\u0000ST\u0005o\u0000\u0000TV\u0005"+
		"f\u0000\u0000U7\u0001\u0000\u0000\u0000UD\u0001\u0000\u0000\u0000V\f\u0001"+
		"\u0000\u0000\u0000W[\u0007\u0000\u0000\u0000XZ\u0007\u0001\u0000\u0000"+
		"YX\u0001\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000"+
		"\u0000[\\\u0001\u0000\u0000\u0000\\\u000e\u0001\u0000\u0000\u0000][\u0001"+
		"\u0000\u0000\u0000^`\u0007\u0002\u0000\u0000_^\u0001\u0000\u0000\u0000"+
		"`a\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000"+
		"\u0000bd\u0001\u0000\u0000\u0000ce\u0005-\u0000\u0000dc\u0001\u0000\u0000"+
		"\u0000de\u0001\u0000\u0000\u0000eg\u0001\u0000\u0000\u0000f_\u0001\u0000"+
		"\u0000\u0000gh\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001"+
		"\u0000\u0000\u0000i\u0010\u0001\u0000\u0000\u0000jl\u0007\u0003\u0000"+
		"\u0000kj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mk\u0001\u0000"+
		"\u0000\u0000mn\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000op\u0006"+
		"\b\u0000\u0000p\u0012\u0001\u0000\u0000\u0000\u0007\u0000U[adhm\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}