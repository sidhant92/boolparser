package com.github.sidhant92.boolparser.parser.antlr;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.NumericToken;
import com.github.sidhant92.boolparser.domain.StringToken;
import com.github.sidhant92.boolparser.operator.OperatorFactory;

public class BooleanFilterListener extends FilterBaseListener {
    private final FilterLexer filterLexer;

    private Node node;

    private final List<Node> currentNodes;

    public BooleanFilterListener(final FilterLexer filterLexer) {
        this.filterLexer = filterLexer;
        this.node = null;
        this.currentNodes = new ArrayList<>();
        OperatorFactory.initialize();
    }

    public Node getNode() {
        return this.node;
    }

    @Override
    public void exitIidentifierExpressionession(FilterParser.IidentifierExpressionessionContext ctx) {
        super.enterIidentifierExpressionession(ctx);
    }

    @Override
    public void exitComparatorExpression(FilterParser.ComparatorExpressionContext ctx) {
        final String variableName = ctx.left.getText();
        final DataType dataType = getDataType(ctx.right.getStart());
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        if (dataType == DataType.STRING) {
            currentNodes.add(new StringToken(variableName, ctx.right.getText()));
        } else {
            currentNodes.add(new NumericToken(variableName, getValue(ctx.right.getText(), dataType), operator, dataType));
        }
        super.enterComparatorExpression(ctx);
    }

    private Object getValue(final String value, final DataType dataType) {
        return switch (dataType) {
            case INTEGER -> Integer.parseInt(value);
            case LONG -> Long.parseLong(value);
            case DECIMAL -> Double.parseDouble(value);
            default -> value;
        };
    }

    private DataType getDataType(final Token token) {
        return switch (filterLexer.getVocabulary().getDisplayName(token.getType())) {
            case "DECIMAL" -> DataType.DECIMAL;
            case "INTEGER" -> DataType.INTEGER;
            default -> DataType.STRING;
        };
    }

    @Override
    public void exitFilter(FilterParser.FilterContext ctx) {
        if (this.node == null && this.currentNodes.size() == 1) {
            this.node = currentNodes.get(0);
        }
        super.exitFilter(ctx);
    }

    @Override
    public void exitBoolExpression(FilterParser.BoolExpressionContext ctx) {
        super.enterBoolExpression(ctx);
    }

    @Override
    public void exitBinaryExpression(FilterParser.BinaryExpressionContext ctx) {
        super.enterBinaryExpression(ctx);
    }
}
