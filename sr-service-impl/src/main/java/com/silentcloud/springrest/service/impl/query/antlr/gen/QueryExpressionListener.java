// Generated from D:/Code/IdeaProjects/PersonalCode/demo/springboot-rest/sr-service-impl/src/main/resources\QueryExpression.g4 by ANTLR 4.8
package com.silentcloud.springrest.service.impl.query.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryExpressionParser}.
 */
public interface QueryExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryExpressionParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(QueryExpressionParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryExpressionParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(QueryExpressionParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryExpressionParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(QueryExpressionParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryExpressionParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(QueryExpressionParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryExpressionParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(QueryExpressionParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryExpressionParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(QueryExpressionParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryExpressionParser#logical_op}.
	 * @param ctx the parse tree
	 */
	void enterLogical_op(QueryExpressionParser.Logical_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryExpressionParser#logical_op}.
	 * @param ctx the parse tree
	 */
	void exitLogical_op(QueryExpressionParser.Logical_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryExpressionParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(QueryExpressionParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryExpressionParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(QueryExpressionParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryExpressionParser}.
	 * @param ctx the parse tree
	 */
	void enterExpr(QueryExpressionParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryExpressionParser}.
	 * @param ctx the parse tree
	 */
	void exitExpr(QueryExpressionParser.ExprContext ctx);
}
