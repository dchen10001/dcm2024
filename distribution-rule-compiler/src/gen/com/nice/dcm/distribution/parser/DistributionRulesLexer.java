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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		LEAST_BUSY=18, QUEUE_TO=19, NUMBER=20, UUID_OR_HEXA=21, WHITESPACE=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"LEAST_BUSY", "QUEUE_TO", "NUMBER", "UUID_OR_HEXA", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'('", "','", "')'", "'@S:'", "'level'", "'..'", "'<'", 
			"'<='", "'='", "'<>'", "'>='", "'>'", "'in'", "'not in'", "'with priority'", 
			"'wait'", "'least busy of'", "'queue to'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "LEAST_BUSY", "QUEUE_TO", "NUMBER", 
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
		"\u0004\u0000\u0016\u00a1\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
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
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u008a\b\u0013\n\u0013\f\u0013\u008d\t\u0013\u0001\u0014\u0004\u0014"+
		"\u0090\b\u0014\u000b\u0014\f\u0014\u0091\u0001\u0014\u0003\u0014\u0095"+
		"\b\u0014\u0004\u0014\u0097\b\u0014\u000b\u0014\f\u0014\u0098\u0001\u0015"+
		"\u0004\u0015\u009c\b\u0015\u000b\u0015\f\u0015\u009d\u0001\u0015\u0001"+
		"\u0015\u0000\u0000\u0016\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'"+
		"\u0014)\u0015+\u0016\u0001\u0000\u0004\u0001\u000019\u0001\u000009\u0003"+
		"\u000009AFaf\u0003\u0000\t\n\r\r  \u00a5\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0001-\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u00053"+
		"\u0001\u0000\u0000\u0000\u00075\u0001\u0000\u0000\u0000\t7\u0001\u0000"+
		"\u0000\u0000\u000b;\u0001\u0000\u0000\u0000\rA\u0001\u0000\u0000\u0000"+
		"\u000fD\u0001\u0000\u0000\u0000\u0011F\u0001\u0000\u0000\u0000\u0013I"+
		"\u0001\u0000\u0000\u0000\u0015K\u0001\u0000\u0000\u0000\u0017N\u0001\u0000"+
		"\u0000\u0000\u0019Q\u0001\u0000\u0000\u0000\u001bS\u0001\u0000\u0000\u0000"+
		"\u001dV\u0001\u0000\u0000\u0000\u001f]\u0001\u0000\u0000\u0000!k\u0001"+
		"\u0000\u0000\u0000#p\u0001\u0000\u0000\u0000%~\u0001\u0000\u0000\u0000"+
		"\'\u0087\u0001\u0000\u0000\u0000)\u0096\u0001\u0000\u0000\u0000+\u009b"+
		"\u0001\u0000\u0000\u0000-.\u0005a\u0000\u0000./\u0005n\u0000\u0000/0\u0005"+
		"d\u0000\u00000\u0002\u0001\u0000\u0000\u000012\u0005(\u0000\u00002\u0004"+
		"\u0001\u0000\u0000\u000034\u0005,\u0000\u00004\u0006\u0001\u0000\u0000"+
		"\u000056\u0005)\u0000\u00006\b\u0001\u0000\u0000\u000078\u0005@\u0000"+
		"\u000089\u0005S\u0000\u00009:\u0005:\u0000\u0000:\n\u0001\u0000\u0000"+
		"\u0000;<\u0005l\u0000\u0000<=\u0005e\u0000\u0000=>\u0005v\u0000\u0000"+
		">?\u0005e\u0000\u0000?@\u0005l\u0000\u0000@\f\u0001\u0000\u0000\u0000"+
		"AB\u0005.\u0000\u0000BC\u0005.\u0000\u0000C\u000e\u0001\u0000\u0000\u0000"+
		"DE\u0005<\u0000\u0000E\u0010\u0001\u0000\u0000\u0000FG\u0005<\u0000\u0000"+
		"GH\u0005=\u0000\u0000H\u0012\u0001\u0000\u0000\u0000IJ\u0005=\u0000\u0000"+
		"J\u0014\u0001\u0000\u0000\u0000KL\u0005<\u0000\u0000LM\u0005>\u0000\u0000"+
		"M\u0016\u0001\u0000\u0000\u0000NO\u0005>\u0000\u0000OP\u0005=\u0000\u0000"+
		"P\u0018\u0001\u0000\u0000\u0000QR\u0005>\u0000\u0000R\u001a\u0001\u0000"+
		"\u0000\u0000ST\u0005i\u0000\u0000TU\u0005n\u0000\u0000U\u001c\u0001\u0000"+
		"\u0000\u0000VW\u0005n\u0000\u0000WX\u0005o\u0000\u0000XY\u0005t\u0000"+
		"\u0000YZ\u0005 \u0000\u0000Z[\u0005i\u0000\u0000[\\\u0005n\u0000\u0000"+
		"\\\u001e\u0001\u0000\u0000\u0000]^\u0005w\u0000\u0000^_\u0005i\u0000\u0000"+
		"_`\u0005t\u0000\u0000`a\u0005h\u0000\u0000ab\u0005 \u0000\u0000bc\u0005"+
		"p\u0000\u0000cd\u0005r\u0000\u0000de\u0005i\u0000\u0000ef\u0005o\u0000"+
		"\u0000fg\u0005r\u0000\u0000gh\u0005i\u0000\u0000hi\u0005t\u0000\u0000"+
		"ij\u0005y\u0000\u0000j \u0001\u0000\u0000\u0000kl\u0005w\u0000\u0000l"+
		"m\u0005a\u0000\u0000mn\u0005i\u0000\u0000no\u0005t\u0000\u0000o\"\u0001"+
		"\u0000\u0000\u0000pq\u0005l\u0000\u0000qr\u0005e\u0000\u0000rs\u0005a"+
		"\u0000\u0000st\u0005s\u0000\u0000tu\u0005t\u0000\u0000uv\u0005 \u0000"+
		"\u0000vw\u0005b\u0000\u0000wx\u0005u\u0000\u0000xy\u0005s\u0000\u0000"+
		"yz\u0005y\u0000\u0000z{\u0005 \u0000\u0000{|\u0005o\u0000\u0000|}\u0005"+
		"f\u0000\u0000}$\u0001\u0000\u0000\u0000~\u007f\u0005q\u0000\u0000\u007f"+
		"\u0080\u0005u\u0000\u0000\u0080\u0081\u0005e\u0000\u0000\u0081\u0082\u0005"+
		"u\u0000\u0000\u0082\u0083\u0005e\u0000\u0000\u0083\u0084\u0005 \u0000"+
		"\u0000\u0084\u0085\u0005t\u0000\u0000\u0085\u0086\u0005o\u0000\u0000\u0086"+
		"&\u0001\u0000\u0000\u0000\u0087\u008b\u0007\u0000\u0000\u0000\u0088\u008a"+
		"\u0007\u0001\u0000\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u008d"+
		"\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0001\u0000\u0000\u0000\u008c(\u0001\u0000\u0000\u0000\u008d\u008b\u0001"+
		"\u0000\u0000\u0000\u008e\u0090\u0007\u0002\u0000\u0000\u008f\u008e\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u008f\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0094\u0001"+
		"\u0000\u0000\u0000\u0093\u0095\u0005-\u0000\u0000\u0094\u0093\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0097\u0001\u0000"+
		"\u0000\u0000\u0096\u008f\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000"+
		"\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000"+
		"\u0000\u0000\u0099*\u0001\u0000\u0000\u0000\u009a\u009c\u0007\u0003\u0000"+
		"\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000"+
		"\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0\u0006\u0015\u0000"+
		"\u0000\u00a0,\u0001\u0000\u0000\u0000\u0006\u0000\u008b\u0091\u0094\u0098"+
		"\u009d\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}