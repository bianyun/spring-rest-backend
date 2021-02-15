// Generated from D:/Code/IdeaProjects/PersonalCode/action/spring-rest/sr-service-impl/src/main/resources\QueryExpression.g4 by ANTLR 4.8
package com.silentcloud.springrest.service.impl.query.antlr.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LITERAL_NULL=2, LITERAL_TRUE=3, LITERAL_FALSE=4, DECIMAL=5, OP_CONTAINS=6,
		OP_LIKE=7, IDENTIFIER=8, REAL=9, LR_BRACKET=10, RR_BRACKET=11, EQUAL=12,
		GREATER=13, LESS=14, EXCLAMATION=15, DOT=16, UNDERLINE=17, LOGICAL_AND=18,
		LOGICAL_OR=19, SPACE=20, STRING=21;
	public static final int
		RULE_operator = 0, RULE_field = 1, RULE_value = 2, RULE_logical_op = 3,
		RULE_condition = 4, RULE_expr = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"operator", "field", "value", "logical_op", "condition", "expr"
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

	@Override
	public String getGrammarFileName() { return "QueryExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QueryExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode GREATER() { return getToken(QueryExpressionParser.GREATER, 0); }
		public TerminalNode LESS() { return getToken(QueryExpressionParser.LESS, 0); }
		public TerminalNode EQUAL() { return getToken(QueryExpressionParser.EQUAL, 0); }
		public TerminalNode EXCLAMATION() { return getToken(QueryExpressionParser.EXCLAMATION, 0); }
		public TerminalNode OP_LIKE() { return getToken(QueryExpressionParser.OP_LIKE, 0); }
		public TerminalNode OP_CONTAINS() { return getToken(QueryExpressionParser.OP_CONTAINS, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryExpressionVisitor ) return ((QueryExpressionVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_operator);
		try {
			setState(23);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				match(GREATER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				match(LESS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(14);
				match(EQUAL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(15);
				match(EXCLAMATION);
				setState(16);
				match(EQUAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(17);
				match(GREATER);
				setState(18);
				match(EQUAL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(19);
				match(LESS);
				setState(20);
				match(EQUAL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(21);
				match(OP_LIKE);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(22);
				match(OP_CONTAINS);
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

	public static class FieldContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QueryExpressionParser.IDENTIFIER, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryExpressionVisitor ) return ((QueryExpressionVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(IDENTIFIER);
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QueryExpressionParser.STRING, 0); }
		public TerminalNode DECIMAL() { return getToken(QueryExpressionParser.DECIMAL, 0); }
		public TerminalNode LITERAL_NULL() { return getToken(QueryExpressionParser.LITERAL_NULL, 0); }
		public TerminalNode LITERAL_TRUE() { return getToken(QueryExpressionParser.LITERAL_TRUE, 0); }
		public TerminalNode LITERAL_FALSE() { return getToken(QueryExpressionParser.LITERAL_FALSE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QueryExpressionParser.IDENTIFIER, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryExpressionVisitor ) return ((QueryExpressionVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LITERAL_NULL) | (1L << LITERAL_TRUE) | (1L << LITERAL_FALSE) | (1L << DECIMAL) | (1L << IDENTIFIER) | (1L << STRING))) != 0)) ) {
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

	public static class Logical_opContext extends ParserRuleContext {
		public TerminalNode LOGICAL_AND() { return getToken(QueryExpressionParser.LOGICAL_AND, 0); }
		public TerminalNode LOGICAL_OR() { return getToken(QueryExpressionParser.LOGICAL_OR, 0); }
		public Logical_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).enterLogical_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).exitLogical_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryExpressionVisitor ) return ((QueryExpressionVisitor<? extends T>)visitor).visitLogical_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_opContext logical_op() throws RecognitionException {
		Logical_opContext _localctx = new Logical_opContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_logical_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_la = _input.LA(1);
			if ( !(_la==LOGICAL_AND || _la==LOGICAL_OR) ) {
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

	public static class ConditionContext extends ParserRuleContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryExpressionVisitor ) return ((QueryExpressionVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			field();
			setState(32);
			operator();
			setState(33);
			value();
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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LR_BRACKET() { return getToken(QueryExpressionParser.LR_BRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RR_BRACKET() { return getToken(QueryExpressionParser.RR_BRACKET, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<Logical_opContext> logical_op() {
			return getRuleContexts(Logical_opContext.class);
		}
		public Logical_opContext logical_op(int i) {
			return getRuleContext(Logical_opContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryExpressionListener ) ((QueryExpressionListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QueryExpressionVisitor ) return ((QueryExpressionVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LR_BRACKET:
				{
				setState(36);
				match(LR_BRACKET);
				setState(37);
				expr(0);
				setState(38);
				match(RR_BRACKET);
				}
				break;
			case IDENTIFIER:
				{
				setState(40);
				condition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(43);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(47);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(44);
							logical_op();
							setState(45);
							expr(0);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(49);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					}
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27;\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\5\2\32\n\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7,\n\7\3\7\3\7\3\7\3\7\6\7\62\n\7\r\7\16\7\63\7\7\66"+
		"\n\7\f\7\16\79\13\7\3\7\2\3\f\b\2\4\6\b\n\f\2\4\5\2\4\7\n\n\27\27\3\2"+
		"\24\25\2>\2\31\3\2\2\2\4\33\3\2\2\2\6\35\3\2\2\2\b\37\3\2\2\2\n!\3\2\2"+
		"\2\f+\3\2\2\2\16\32\7\17\2\2\17\32\7\20\2\2\20\32\7\16\2\2\21\22\7\21"+
		"\2\2\22\32\7\16\2\2\23\24\7\17\2\2\24\32\7\16\2\2\25\26\7\20\2\2\26\32"+
		"\7\16\2\2\27\32\7\t\2\2\30\32\7\b\2\2\31\16\3\2\2\2\31\17\3\2\2\2\31\20"+
		"\3\2\2\2\31\21\3\2\2\2\31\23\3\2\2\2\31\25\3\2\2\2\31\27\3\2\2\2\31\30"+
		"\3\2\2\2\32\3\3\2\2\2\33\34\7\n\2\2\34\5\3\2\2\2\35\36\t\2\2\2\36\7\3"+
		"\2\2\2\37 \t\3\2\2 \t\3\2\2\2!\"\5\4\3\2\"#\5\2\2\2#$\5\6\4\2$\13\3\2"+
		"\2\2%&\b\7\1\2&\'\7\f\2\2\'(\5\f\7\2()\7\r\2\2),\3\2\2\2*,\5\n\6\2+%\3"+
		"\2\2\2+*\3\2\2\2,\67\3\2\2\2-\61\f\5\2\2./\5\b\5\2/\60\5\f\7\2\60\62\3"+
		"\2\2\2\61.\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2"+
		"\2\2\65-\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\r\3\2\2\29\67"+
		"\3\2\2\2\6\31+\63\67";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
