// Generated from D:/Code/IdeaProjects/PersonalCode/action/spring-rest/sr-service-impl/src/main/resources\QueryExpression.g4 by ANTLR 4.8
package com.silentcloud.springrest.service.impl.query.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QueryExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QueryExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QueryExpressionParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(QueryExpressionParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryExpressionParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(QueryExpressionParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryExpressionParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(QueryExpressionParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryExpressionParser#logical_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_op(QueryExpressionParser.Logical_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryExpressionParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QueryExpressionParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryExpressionParser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(QueryExpressionParser.ExprContext ctx);
}
