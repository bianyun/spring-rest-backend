// Generated from D:/Code/IdeaProjects/PersonalCode/action/spring-rest/sr-service-impl/src/main/resources\QueryExpression.g4 by ANTLR 4.8
package com.silentcloud.springrest.service.impl.query.antlr.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LITERAL_NULL=2, LITERAL_TRUE=3, LITERAL_FALSE=4, DECIMAL=5, OP_CONTAINS=6,
		OP_LIKE=7, IDENTIFIER=8, REAL=9, LR_BRACKET=10, RR_BRACKET=11, EQUAL=12,
		GREATER=13, LESS=14, EXCLAMATION=15, DOT=16, UNDERLINE=17, LOGICAL_AND=18,
		LOGICAL_OR=19, SPACE=20, STRING=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LETTER", "IPV6_OCTECT", "DEC_DOT_DEC", "HEX_DIGIT", "DEC_DIGIT", "OCTAL_ESC",
			"ESC_SEQ", "UNICODE_ESC", "WS", "LITERAL_NULL", "LITERAL_TRUE", "LITERAL_FALSE",
			"DECIMAL", "OP_CONTAINS", "OP_LIKE", "IDENTIFIER", "REAL", "LR_BRACKET",
			"RR_BRACKET", "EQUAL", "GREATER", "LESS", "EXCLAMATION", "DOT", "UNDERLINE",
			"LOGICAL_AND", "LOGICAL_OR", "SPACE", "STRING"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'contains'", "'like'", null, null,
			"'('", "')'", "'='", "'>'", "'<'", "'!'", "'.'", "'_'", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "LITERAL_NULL", "LITERAL_TRUE", "LITERAL_FALSE", "DECIMAL",
			"OP_CONTAINS", "OP_LIKE", "IDENTIFIER", "REAL", "LR_BRACKET", "RR_BRACKET",
			"EQUAL", "GREATER", "LESS", "EXCLAMATION", "DOT", "UNDERLINE", "LOGICAL_AND",
			"LOGICAL_OR", "SPACE", "STRING"
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


	public QueryExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QueryExpression.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00eb\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\6\4F\n\4\r\4\16\4G\3\4\3\4\6\4L\n\4\r\4\16\4M\3\4\6"+
		"\4Q\n\4\r\4\16\4R\3\4\3\4\3\4\3\4\6\4Y\n\4\r\4\16\4Z\5\4]\n\4\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7l\n\7\3\b\3\b\5\bp\n\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\6\nz\n\n\r\n\16\n{\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0088\n\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\5\f\u0092\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u009e"+
		"\n\r\3\16\6\16\u00a1\n\16\r\16\16\16\u00a2\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\7\21\u00b5\n\21"+
		"\f\21\16\21\u00b8\13\21\3\22\3\22\5\22\u00bc\n\22\3\22\3\22\5\22\u00c0"+
		"\n\22\3\22\6\22\u00c3\n\22\r\22\16\22\u00c4\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\35\6\35\u00de\n\35\r\35\16\35\u00df\3\36\3\36\3\36\7\36"+
		"\u00e5\n\36\f\36\16\36\u00e8\13\36\3\36\3\36\2\2\37\3\2\5\2\7\2\t\2\13"+
		"\2\r\2\17\2\21\2\23\3\25\4\27\5\31\6\33\7\35\b\37\t!\n#\13%\f\'\r)\16"+
		"+\17-\20/\21\61\22\63\23\65\24\67\259\26;\27\3\2\n\4\2C\\aa\4\2\62;CH"+
		"\3\2\62;\5\2\13\f\17\17\"\"\6\2%%C\\aac|\b\2%&\60\60\62;B\\aac|\4\2--"+
		"//\4\2))^^\2\u00f7\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2"+
		"\2\2\5?\3\2\2\2\7\\\3\2\2\2\t^\3\2\2\2\13`\3\2\2\2\rk\3\2\2\2\17o\3\2"+
		"\2\2\21q\3\2\2\2\23y\3\2\2\2\25\u0087\3\2\2\2\27\u0091\3\2\2\2\31\u009d"+
		"\3\2\2\2\33\u00a0\3\2\2\2\35\u00a4\3\2\2\2\37\u00ad\3\2\2\2!\u00b2\3\2"+
		"\2\2#\u00bb\3\2\2\2%\u00c6\3\2\2\2\'\u00c8\3\2\2\2)\u00ca\3\2\2\2+\u00cc"+
		"\3\2\2\2-\u00ce\3\2\2\2/\u00d0\3\2\2\2\61\u00d2\3\2\2\2\63\u00d4\3\2\2"+
		"\2\65\u00d6\3\2\2\2\67\u00d9\3\2\2\29\u00dd\3\2\2\2;\u00e1\3\2\2\2=>\t"+
		"\2\2\2>\4\3\2\2\2?@\t\3\2\2@A\t\3\2\2AB\t\3\2\2BC\t\3\2\2C\6\3\2\2\2D"+
		"F\5\13\6\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2IK\7\60\2"+
		"\2JL\5\13\6\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2N]\3\2\2\2OQ\5\13"+
		"\6\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7\60\2\2U]\3"+
		"\2\2\2VX\7\60\2\2WY\5\13\6\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2["+
		"]\3\2\2\2\\E\3\2\2\2\\P\3\2\2\2\\V\3\2\2\2]\b\3\2\2\2^_\t\3\2\2_\n\3\2"+
		"\2\2`a\t\4\2\2a\f\3\2\2\2bc\7^\2\2cd\4\62\65\2de\4\629\2el\4\629\2fg\7"+
		"^\2\2gh\4\629\2hl\4\629\2ij\7^\2\2jl\4\629\2kb\3\2\2\2kf\3\2\2\2ki\3\2"+
		"\2\2l\16\3\2\2\2mp\5\21\t\2np\5\r\7\2om\3\2\2\2on\3\2\2\2p\20\3\2\2\2"+
		"qr\7^\2\2rs\7w\2\2st\5\t\5\2tu\5\t\5\2uv\5\t\5\2vw\5\t\5\2w\22\3\2\2\2"+
		"xz\t\5\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\b\n\2\2"+
		"~\24\3\2\2\2\177\u0080\7p\2\2\u0080\u0081\7w\2\2\u0081\u0082\7n\2\2\u0082"+
		"\u0088\7n\2\2\u0083\u0084\7P\2\2\u0084\u0085\7W\2\2\u0085\u0086\7N\2\2"+
		"\u0086\u0088\7N\2\2\u0087\177\3\2\2\2\u0087\u0083\3\2\2\2\u0088\26\3\2"+
		"\2\2\u0089\u008a\7v\2\2\u008a\u008b\7t\2\2\u008b\u008c\7w\2\2\u008c\u0092"+
		"\7g\2\2\u008d\u008e\7V\2\2\u008e\u008f\7T\2\2\u008f\u0090\7W\2\2\u0090"+
		"\u0092\7G\2\2\u0091\u0089\3\2\2\2\u0091\u008d\3\2\2\2\u0092\30\3\2\2\2"+
		"\u0093\u0094\7h\2\2\u0094\u0095\7c\2\2\u0095\u0096\7n\2\2\u0096\u0097"+
		"\7u\2\2\u0097\u009e\7g\2\2\u0098\u0099\7H\2\2\u0099\u009a\7C\2\2\u009a"+
		"\u009b\7N\2\2\u009b\u009c\7U\2\2\u009c\u009e\7G\2\2\u009d\u0093\3\2\2"+
		"\2\u009d\u0098\3\2\2\2\u009e\32\3\2\2\2\u009f\u00a1\5\13\6\2\u00a0\u009f"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\34\3\2\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7q\2\2\u00a6\u00a7\7p\2\2\u00a7"+
		"\u00a8\7v\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7p\2\2"+
		"\u00ab\u00ac\7u\2\2\u00ac\36\3\2\2\2\u00ad\u00ae\7n\2\2\u00ae\u00af\7"+
		"k\2\2\u00af\u00b0\7m\2\2\u00b0\u00b1\7g\2\2\u00b1 \3\2\2\2\u00b2\u00b6"+
		"\t\6\2\2\u00b3\u00b5\t\7\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\"\3\2\2\2\u00b8\u00b6\3\2\2\2"+
		"\u00b9\u00bc\5\33\16\2\u00ba\u00bc\5\7\4\2\u00bb\u00b9\3\2\2\2\u00bb\u00ba"+
		"\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\7G\2\2\u00be\u00c0\t\b\2\2\u00bf"+
		"\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00c3\5\13"+
		"\6\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5$\3\2\2\2\u00c6\u00c7\7*\2\2\u00c7&\3\2\2\2\u00c8"+
		"\u00c9\7+\2\2\u00c9(\3\2\2\2\u00ca\u00cb\7?\2\2\u00cb*\3\2\2\2\u00cc\u00cd"+
		"\7@\2\2\u00cd,\3\2\2\2\u00ce\u00cf\7>\2\2\u00cf.\3\2\2\2\u00d0\u00d1\7"+
		"#\2\2\u00d1\60\3\2\2\2\u00d2\u00d3\7\60\2\2\u00d3\62\3\2\2\2\u00d4\u00d5"+
		"\7a\2\2\u00d5\64\3\2\2\2\u00d6\u00d7\7(\2\2\u00d7\u00d8\7(\2\2\u00d8\66"+
		"\3\2\2\2\u00d9\u00da\7~\2\2\u00da\u00db\7~\2\2\u00db8\3\2\2\2\u00dc\u00de"+
		"\t\5\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0:\3\2\2\2\u00e1\u00e6\7)\2\2\u00e2\u00e5\5\17\b\2"+
		"\u00e3\u00e5\n\t\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e8"+
		"\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e9\u00ea\7)\2\2\u00ea<\3\2\2\2\26\2GMRZ\\ko{\u0087\u0091"+
		"\u009d\u00a2\u00b6\u00bb\u00bf\u00c4\u00df\u00e4\u00e6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
