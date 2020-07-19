package com.github.sidhant92.boolparser.parser.canopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BoolExpression;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.NumericRangeToken;
import com.github.sidhant92.boolparser.domain.NumericToken;
import com.github.sidhant92.boolparser.domain.StringToken;

/**
 * @author sidhant.aggarwal
 * @since 19/07/2020
 */
public class CanopyBoolExpressionParserTest {
    @Test
    public void testSingleStringToken() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("name:test");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "test");
    }

    @Test
    public void testSingleIntToken() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("age=44");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 44, Operator.EQUALS);
    }

    @Test
    public void testSingleDecimalToken() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("age=44.34");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 44.34, Operator.EQUALS);
    }

    @Test
    public void testSingleIntRangeToken() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("age: 18 TO 44");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_RANGE_TOKEN.name());
        verifyNumericRangeToken((NumericRangeToken) nodeOptional.get(), "age", 18, 44);
    }

    @Test
    public void testGreaterThan() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("age > 18");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 18, Operator.GREATER_THAN);
    }

    @Test
    public void testSingleDecimalRangeToken() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("age: 18.4 TO 44.2");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_RANGE_TOKEN.name());
        verifyNumericRangeToken((NumericRangeToken) nodeOptional.get(), "age", 18.4, 44.2);
    }

    @Test
    public void testSimpleOrCondition() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("name:test OR age=33");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 2);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(0), "name", "test");
        verifyNumericToken((NumericToken) boolExpression.getOrOperations().get(1), "age", 33, Operator.EQUALS);
    }

    @Test
    public void testSimpleAndCondition() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("name:test AND age=33");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getAndOperations().size(), 2);
        assertEquals(boolExpression.getAndOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getAndOperations().get(1).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getAndOperations().get(0), "name", "test");
        verifyNumericToken((NumericToken) boolExpression.getAndOperations().get(1), "age", 33, Operator.EQUALS);
    }

    @Test
    public void testSimpleNotCondition() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("NOT name:test");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getNotOperations().size(), 1);
        assertEquals(boolExpression.getNotOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getNotOperations().get(0), "name", "test");
    }

    @Test
    public void testNestedAndCondition() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("name:test OR (age=33 AND city:dummy)");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 2);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(0), "name", "test");
        final BoolExpression nestedBooleanExpression = (BoolExpression) boolExpression.getOrOperations().get(1);
        assertEquals(nestedBooleanExpression.getAndOperations().size(), 2);
        assertEquals(nestedBooleanExpression.getAndOperations().get(0).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        assertEquals(nestedBooleanExpression.getAndOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyNumericToken((NumericToken) nestedBooleanExpression.getAndOperations().get(0), "age", 33, Operator.EQUALS);
        verifyStringToken((StringToken) nestedBooleanExpression.getAndOperations().get(1), "city", "dummy");
    }

    @Test
    public void testNestedOrCondition() {
        final CanopyBoolExpressionParser canopyBoolExpressionParser = new CanopyBoolExpressionParser();
        final Optional<Node> nodeOptional = canopyBoolExpressionParser.parseExpression("name:test AND (age=33 OR city:dummy)");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getAndOperations().size(), 2);
        assertEquals(boolExpression.getAndOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getAndOperations().get(1).getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        verifyStringToken((StringToken) boolExpression.getAndOperations().get(0), "name", "test");
        final BoolExpression nestedBooleanExpression = (BoolExpression) boolExpression.getAndOperations().get(1);
        assertEquals(nestedBooleanExpression.getOrOperations().size(), 2);
        assertEquals(nestedBooleanExpression.getOrOperations().get(0).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        assertEquals(nestedBooleanExpression.getOrOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyNumericToken((NumericToken) nestedBooleanExpression.getOrOperations().get(0), "age", 33, Operator.EQUALS);
        verifyStringToken((StringToken) nestedBooleanExpression.getOrOperations().get(1), "city", "dummy");
    }

    private void verifyStringToken(final StringToken stringToken, final String field, final String value) {
        assertEquals(stringToken.getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(stringToken.getField(), field);
        assertEquals(stringToken.getValue(), value);
    }

    private void verifyNumericToken(final NumericToken numericToken, final String field, final Object value, final Operator operator) {
        assertEquals(numericToken.getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        assertEquals(numericToken.getField(), field);
        assertEquals(numericToken.getValue(), value);
        assertEquals(numericToken.getOperator().name(), operator.name());
    }

    private void verifyNumericRangeToken(final NumericRangeToken numericRangeToken, final String field, final Object fromValue,
            final Object toValue) {
        assertEquals(numericRangeToken.getNodeType().name(), NodeType.NUMERIC_RANGE_TOKEN.name());
        assertEquals(numericRangeToken.getField(), field);
        assertEquals(numericRangeToken.getFromValue(), fromValue);
        assertEquals(numericRangeToken.getToValue(), toValue);
    }
}
