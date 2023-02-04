package com.github.sidhant92.boolparser.parser.antlr;// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FilterParser}.
 */
public interface FilterListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FilterParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(FilterParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(FilterParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(FilterParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(FilterParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(FilterParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(FilterParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code iidentifierExpressionession}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIidentifierExpressionession(FilterParser.IidentifierExpressionessionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code iidentifierExpressionession}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIidentifierExpressionession(FilterParser.IidentifierExpressionessionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(FilterParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(FilterParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(FilterParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(FilterParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integer}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInteger(FilterParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integer}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInteger(FilterParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparatorExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparatorExpression(FilterParser.ComparatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparatorExpression}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparatorExpression(FilterParser.ComparatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpr(FilterParser.IdentifierExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link FilterParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpr(FilterParser.IdentifierExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(FilterParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(FilterParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(FilterParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(FilterParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link FilterParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(FilterParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link FilterParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(FilterParser.BoolContext ctx);
}