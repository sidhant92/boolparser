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
public class PEGBoolExpressionParserTest {
    @Test
    public void testSingleStringToken() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:test");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "test");
    }

    @Test
    public void testSingleStringTokenWithSingleQuotes() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:\"te\'st\"");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "te\'st");
    }

    @Test
    public void testSingleStringTokenWithDoubleQuotes() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:'te\"st'");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "te\"st");
    }

    @Test
    public void testSingleStringTokenWithSpace() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:\"first second\"");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "first second");
    }

    @Test
    public void testSingleIntToken() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age=44");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 44, Operator.EQUALS);
    }

    @Test
    public void testSingleLongToken() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age=1611473334114");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 1611473334114L, Operator.EQUALS);
    }

    @Test
    public void testSingleDecimalToken() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age=44.34");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 44.34, Operator.EQUALS);
    }

    @Test
    public void testSingleIntRangeToken() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age: 18 TO 44");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_RANGE_TOKEN.name());
        verifyNumericRangeToken((NumericRangeToken) nodeOptional.get(), "age", 18, 44);
    }

    @Test
    public void testGreaterThan() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age > 18");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 18, Operator.GREATER_THAN);
    }

    @Test
    public void testSingleDecimalRangeToken() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age: 18.4 TO 44.2");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.NUMERIC_RANGE_TOKEN.name());
        verifyNumericRangeToken((NumericRangeToken) nodeOptional.get(), "age", 18.4, 44.2);
    }

    @Test
    public void testSimpleOrCondition() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:test OR age=33");
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
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:test AND age=33");
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
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("NOT name:test");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getNotOperations().size(), 1);
        assertEquals(boolExpression.getNotOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getNotOperations().get(0), "name", "test");
    }

    @Test
    public void testNestedAndCondition() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:test OR (age=33 AND city:dummy)");
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
    public void testNestedAndCondition1() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("(agel=44 AND cityl:abc) OR (ager=33 AND cityr:dummy)");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 2);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression nestedLeftBooleanExpression = (BoolExpression) boolExpression.getOrOperations().get(0);
        assertEquals(nestedLeftBooleanExpression.getAndOperations().size(), 2);
        assertEquals(nestedLeftBooleanExpression.getAndOperations().get(0).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        assertEquals(nestedLeftBooleanExpression.getAndOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyNumericToken((NumericToken) nestedLeftBooleanExpression.getAndOperations().get(0), "agel", 44, Operator.EQUALS);
        verifyStringToken((StringToken) nestedLeftBooleanExpression.getAndOperations().get(1), "cityl", "abc");
        final BoolExpression nestedRightBooleanExpression = (BoolExpression) boolExpression.getOrOperations().get(1);
        assertEquals(nestedRightBooleanExpression.getAndOperations().size(), 2);
        assertEquals(nestedRightBooleanExpression.getAndOperations().get(0).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        assertEquals(nestedRightBooleanExpression.getAndOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyNumericToken((NumericToken) nestedRightBooleanExpression.getAndOperations().get(0), "ager", 33, Operator.EQUALS);
        verifyStringToken((StringToken) nestedRightBooleanExpression.getAndOperations().get(1), "cityr", "dummy");
    }

    @Test
    public void testNestedOrCondition() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:test AND (age=33 OR city:dummy)");
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

    @Test
    public void testIntegerList() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("age IN (12,45)");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 2);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.NUMERIC_TOKEN.name());
        verifyNumericToken((NumericToken) boolExpression.getOrOperations().get(0), "age", 12, Operator.EQUALS);
        verifyNumericToken((NumericToken) boolExpression.getOrOperations().get(1), "age", 45, Operator.EQUALS);
    }

    @Test
    public void testStringList() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name IN (abc, def, 'abc def')");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 3);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(2).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(0), "name", "abc");
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(1), "name", "def");
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(2), "name", "abc def");
    }

    @Test
    public void testStringList1() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name IN (abc, def, 'abc, def')");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 3);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(2).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(0), "name", "abc");
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(1), "name", "def");
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(2), "name", "abc, def");
    }

    @Test
    public void testStringList2() {
        final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
        final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name IN (abc, def, 'ab\"c')");
        assertTrue(nodeOptional.isPresent());
        assertEquals(nodeOptional.get().getNodeType().name(), NodeType.BOOL_EXPRESSION.name());
        final BoolExpression boolExpression = (BoolExpression) nodeOptional.get();
        assertEquals(boolExpression.getOrOperations().size(), 3);
        assertEquals(boolExpression.getOrOperations().get(0).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(1).getNodeType().name(), NodeType.STRING_TOKEN.name());
        assertEquals(boolExpression.getOrOperations().get(2).getNodeType().name(), NodeType.STRING_TOKEN.name());
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(0), "name", "abc");
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(1), "name", "def");
        verifyStringToken((StringToken) boolExpression.getOrOperations().get(2), "name", "ab\"c");
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
