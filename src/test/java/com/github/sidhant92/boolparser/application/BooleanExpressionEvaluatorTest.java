package com.github.sidhant92.boolparser.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author sidhant.aggarwal
 * @since 29/07/2020
 */
public class BooleanExpressionEvaluatorTest {
    @Test
    public void testReverseAllMatchCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", List.of(1, 2));
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age rev_all (1,2,4)", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testReverseAllMatchInCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", List.of(1, 2));
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age rev_all (1,3,4)", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testSimpleTrueCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:abc", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testSimpleTrueCorrectExpressions() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc-");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:abc-", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testSimpleFalseIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "def");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:abc", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericEqualCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericEqualIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > 20", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > 27", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanEqualCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age >= 20", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanEqualIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age >= 27", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericLessThanEqualCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age < 30", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericLessThanEqualIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age <= 20", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericNotEqualCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age != 30", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericNotEqualIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age != 26", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testSimpleNotStringExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("NOT name:abc", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testSimpleNotNumericExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("NOT age = 24", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComplexAndCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid AND age = 25", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexAndIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid AND age = 23", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComplexORCorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid OR age = 23", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexORIncorrectExpression() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sidh");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid OR age = 23", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testCorrectComplexExpressionWithParenthesis() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid AND (age = 25 OR num = 44)", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNegativeInClauseForIntegers() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age in (26,56,34)", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForIntegers() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age in (26,25,34)", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForDecimals() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("num", 25.3);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("num in (26,25,34, 25.3)", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNegativeInClauseForStrings() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "test");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name in (tes, abc)", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForStrings() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "test");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name in (abc, test)", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testCorrectComplexExpressionWithParenthesis1() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sidh OR (age = 25 AND num = 45)", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testIncorrectComplexExpressionWithParenthesis() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid AND (age = 23 OR num = 44)", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testWrongDataType() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = dsf", data);
        assertFalse(booleanOptional.isPresent());
    }

    @Test
    public void testWrongDataType1() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "sf");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testKeyMissing() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("agee", 34);
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isPresent());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThan() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.0.6");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version > 1.0.5", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThan1() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.0.6.15");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version > 1.0.6.14", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThan2() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.54");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version > 1.53", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThanEqualTo() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.0.6");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version >= 1.0.6", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionLessThan() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.5.9");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version < 1.5.10", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionLessThanEqualTo() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.5.9");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version <= 1.5.9", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testStringEqualityWithQuotes() {
        final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "sidhant aggarwal");
        final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name : 'sidhant aggarwal'", data);
        assertTrue(booleanOptional.isPresent());
        assertTrue(booleanOptional.get());
    }
}
