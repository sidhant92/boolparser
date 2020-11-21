package com.github.sidhant92.boolparser.application;

import java.util.Map;
import java.util.Optional;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BoolExpression;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.NumericRangeToken;
import com.github.sidhant92.boolparser.domain.NumericToken;
import com.github.sidhant92.boolparser.domain.StringToken;
import com.github.sidhant92.boolparser.operator.OperatorService;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import com.github.sidhant92.boolparser.parser.canopy.PEGBoolExpressionParser;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 28/07/2020
 */
@Slf4j
public class BooleanExpressionEvaluator {
    private final BoolExpressionParser boolExpressionParser;

    private final OperatorService operatorService;

    public BooleanExpressionEvaluator() {
        boolExpressionParser = new PEGBoolExpressionParser();
        operatorService = new OperatorService();
    }

    public Optional<Boolean> evaluate(final String expression, final Map<String, Object> data) {
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression(expression);
        return nodeOptional.map(node -> evaluateNode(node, data));
    }

    private boolean evaluateNode(final Node node, final Map<String, Object> data) {
        switch (node.getNodeType()) {
            case STRING_TOKEN:
                return evaluateStringToken((StringToken) node, data);
            case NUMERIC_TOKEN:
                return evaluateNumericToken((NumericToken) node, data);
            case NUMERIC_RANGE_TOKEN:
                return evaluateNumericRangeToken((NumericRangeToken) node, data);
            case BOOL_EXPRESSION:
                return evaluateBooleanNode((BoolExpression) node, data);
            default:
                return false;
        }
    }

    private boolean evaluateStringToken(final StringToken stringToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(stringToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(stringToken.getField());
        return operatorService.evaluate(Operator.EQUALS, ContainerDataType.primitive, DataType.STRING, fieldData, stringToken.getValue());
    }

    private boolean evaluateNumericToken(final NumericToken numericToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(numericToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(numericToken.getField());
        return operatorService
                .evaluate(numericToken.getOperator(), ContainerDataType.primitive, numericToken.getDataType(), fieldData, numericToken.getValue());
    }

    private boolean evaluateNumericRangeToken(final NumericRangeToken numericRangeToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(numericRangeToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(numericRangeToken.getField());
        return operatorService.evaluate(Operator.GREATER_THAN_EQUAL, ContainerDataType.primitive, numericRangeToken.getFromDataType(), fieldData,
                numericRangeToken.getFromValue()) && operatorService
                .evaluate(Operator.LESS_THAN_EQUAL, ContainerDataType.primitive, numericRangeToken.getToDataType(), fieldData,
                        numericRangeToken.getToValue());
    }

    private boolean evaluateBooleanNode(final BoolExpression boolExpression, final Map<String, Object> data) {
        return (boolExpression.getOrOperations().stream().anyMatch(orOperation -> evaluateNode(orOperation, data)) || boolExpression.getOrOperations()
                                                                                                                                    .isEmpty()) && (boolExpression
                .getAndOperations().stream().allMatch(andOperation -> evaluateNode(andOperation, data)) || boolExpression.getAndOperations()
                                                                                                                         .isEmpty()) && (!boolExpression
                .getNotOperations().stream().allMatch(notOperation -> evaluateNode(notOperation, data)) || boolExpression.getNotOperations()
                                                                                                                         .isEmpty());
    }

    private boolean checkFieldDataMissing(final String field, final Map<String, Object> data) {
        if (!data.containsKey(field)) {
            log.error("Error data not found for field {}", field);
            return true;
        }
        return false;
    }
}
