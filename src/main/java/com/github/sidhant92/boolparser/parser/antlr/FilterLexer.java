package com.github.sidhant92.boolparser.parser.antlr;// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FilterLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, NOT=3, TRUE=4, FALSE=5, NE=6, GT=7, GE=8, LT=9, LE=10, EQ=11, 
		LPAREN=12, RPAREN=13, DECIMAL=14, INTEGER=15, WS=16, WORD=17, ALPHANUMERIC=18, 
		SQ=19, DQ=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"AND", "OR", "NOT", "TRUE", "FALSE", "NE", "GT", "GE", "LT", "LE", "EQ", 
			"LPAREN", "RPAREN", "DECIMAL", "INTEGER", "WS", "WORD", "ALPHANUMERIC", 
			"SQ", "DQ"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'TRUE'", "'FALSE'", "'!='", "'>'", "'>='", "'<'", 
			"'<='", "'='", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "NOT", "TRUE", "FALSE", "NE", "GT", "GE", "LT", "LE", 
			"EQ", "LPAREN", "RPAREN", "DECIMAL", "INTEGER", "WS", "WORD", "ALPHANUMERIC", 
			"SQ", "DQ"
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


	public FilterLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Filter.g4"; }

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
		"\u0004\u0000\u0014\u0094\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0003\u00002\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001:\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002B\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0004\rc\b\r\u000b\r\f\rd\u0001"+
		"\r\u0001\r\u0004\ri\b\r\u000b\r\f\rj\u0001\u000e\u0004\u000en\b\u000e"+
		"\u000b\u000e\f\u000eo\u0001\u000f\u0004\u000fs\b\u000f\u000b\u000f\f\u000f"+
		"t\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0004\u0010}\b\u0010\u000b\u0010\f\u0010~\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0005\u0012\u0085\b\u0012\n\u0012\f\u0012\u0088\t\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013\u008e\b\u0013"+
		"\n\u0013\f\u0013\u0091\t\u0013\u0001\u0013\u0001\u0013\u0002\u0086\u008f"+
		"\u0000\u0014\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014\u0001\u0000"+
		"\u0004\u0001\u000009\u0003\u0000\t\n\f\r  \u0002\u0000-.__\u0003\u0000"+
		"09AZaz\u00a2\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u00011\u0001"+
		"\u0000\u0000\u0000\u00039\u0001\u0000\u0000\u0000\u0005A\u0001\u0000\u0000"+
		"\u0000\u0007C\u0001\u0000\u0000\u0000\tH\u0001\u0000\u0000\u0000\u000b"+
		"N\u0001\u0000\u0000\u0000\rQ\u0001\u0000\u0000\u0000\u000fS\u0001\u0000"+
		"\u0000\u0000\u0011V\u0001\u0000\u0000\u0000\u0013X\u0001\u0000\u0000\u0000"+
		"\u0015[\u0001\u0000\u0000\u0000\u0017]\u0001\u0000\u0000\u0000\u0019_"+
		"\u0001\u0000\u0000\u0000\u001bb\u0001\u0000\u0000\u0000\u001dm\u0001\u0000"+
		"\u0000\u0000\u001fr\u0001\u0000\u0000\u0000!|\u0001\u0000\u0000\u0000"+
		"#\u0080\u0001\u0000\u0000\u0000%\u0082\u0001\u0000\u0000\u0000\'\u008b"+
		"\u0001\u0000\u0000\u0000)*\u0005A\u0000\u0000*+\u0005N\u0000\u0000+2\u0005"+
		"D\u0000\u0000,-\u0005a\u0000\u0000-.\u0005n\u0000\u0000.2\u0005d\u0000"+
		"\u0000/0\u0005&\u0000\u000002\u0005&\u0000\u00001)\u0001\u0000\u0000\u0000"+
		"1,\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u00002\u0002\u0001\u0000"+
		"\u0000\u000034\u0005O\u0000\u00004:\u0005R\u0000\u000056\u0005o\u0000"+
		"\u00006:\u0005r\u0000\u000078\u0005|\u0000\u00008:\u0005|\u0000\u0000"+
		"93\u0001\u0000\u0000\u000095\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u0000:\u0004\u0001\u0000\u0000\u0000;<\u0005N\u0000\u0000<=\u0005O\u0000"+
		"\u0000=B\u0005T\u0000\u0000>?\u0005n\u0000\u0000?@\u0005o\u0000\u0000"+
		"@B\u0005t\u0000\u0000A;\u0001\u0000\u0000\u0000A>\u0001\u0000\u0000\u0000"+
		"B\u0006\u0001\u0000\u0000\u0000CD\u0005T\u0000\u0000DE\u0005R\u0000\u0000"+
		"EF\u0005U\u0000\u0000FG\u0005E\u0000\u0000G\b\u0001\u0000\u0000\u0000"+
		"HI\u0005F\u0000\u0000IJ\u0005A\u0000\u0000JK\u0005L\u0000\u0000KL\u0005"+
		"S\u0000\u0000LM\u0005E\u0000\u0000M\n\u0001\u0000\u0000\u0000NO\u0005"+
		"!\u0000\u0000OP\u0005=\u0000\u0000P\f\u0001\u0000\u0000\u0000QR\u0005"+
		">\u0000\u0000R\u000e\u0001\u0000\u0000\u0000ST\u0005>\u0000\u0000TU\u0005"+
		"=\u0000\u0000U\u0010\u0001\u0000\u0000\u0000VW\u0005<\u0000\u0000W\u0012"+
		"\u0001\u0000\u0000\u0000XY\u0005<\u0000\u0000YZ\u0005=\u0000\u0000Z\u0014"+
		"\u0001\u0000\u0000\u0000[\\\u0005=\u0000\u0000\\\u0016\u0001\u0000\u0000"+
		"\u0000]^\u0005(\u0000\u0000^\u0018\u0001\u0000\u0000\u0000_`\u0005)\u0000"+
		"\u0000`\u001a\u0001\u0000\u0000\u0000ac\u0007\u0000\u0000\u0000ba\u0001"+
		"\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fh\u0005.\u0000\u0000"+
		"gi\u0007\u0000\u0000\u0000hg\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000k\u001c\u0001"+
		"\u0000\u0000\u0000ln\u0007\u0000\u0000\u0000ml\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000"+
		"\u0000p\u001e\u0001\u0000\u0000\u0000qs\u0007\u0001\u0000\u0000rq\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000"+
		"tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0006\u000f\u0000"+
		"\u0000w \u0001\u0000\u0000\u0000x}\u0003#\u0011\u0000y}\u0007\u0002\u0000"+
		"\u0000z}\u0003%\u0012\u0000{}\u0003\'\u0013\u0000|x\u0001\u0000\u0000"+
		"\u0000|y\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|{\u0001\u0000"+
		"\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f"+
		"\u0001\u0000\u0000\u0000\u007f\"\u0001\u0000\u0000\u0000\u0080\u0081\u0007"+
		"\u0003\u0000\u0000\u0081$\u0001\u0000\u0000\u0000\u0082\u0086\u0005\'"+
		"\u0000\u0000\u0083\u0085\t\u0000\u0000\u0000\u0084\u0083\u0001\u0000\u0000"+
		"\u0000\u0085\u0088\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000"+
		"\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u0089\u0001\u0000\u0000"+
		"\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008a\u0005\'\u0000\u0000"+
		"\u008a&\u0001\u0000\u0000\u0000\u008b\u008f\u0005\"\u0000\u0000\u008c"+
		"\u008e\t\u0000\u0000\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u0091"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u008f"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0005\"\u0000\u0000\u0093(\u0001"+
		"\u0000\u0000\u0000\f\u000019Adjot|~\u0086\u008f\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}