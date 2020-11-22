# boolparser
A Boolean Expression Parser

The library can help parse complex and nested boolean expressions.
The filter expression is SQL-like syntax, where you can use boolean operators and parentheses to combine individual filters.

#### Textual Equality

Format: `${attributeName}:${value}`

Example: `name:john`

#### Numeric Comparisons

Format: `${attributeName} ${operator} ${value}`

Example: `price > 12.99`

The ${value} must be numeric. Supported operators are `<`, `<=`, `=`, `!=`, `>=` and `>`, with the same semantics as in virtually all programming languages.

#### Numeric Range

Format: `${attributeName}:${lowerBound} TO ${upperBound}`

Example: `price:5.99 TO 100`

`${lowerBound}` and `${upperBound}` must be numeric. Both are inclusive.

#### Boolean operators

Example:

`price < 10 AND (category:Book OR NOT category:Ebook)`

Individual filters can be combined via boolean operators. The following operators are supported:

* `OR`: must match any of the combined conditions (disjunction)
* `AND`: must match all of the combined conditions (conjunction)
* `NOT`: negate a filter

Parentheses, `(` and `)`, can be used for grouping.

You cannot negate a group of filters, only an individual filter. For example, NOT(filter1 OR filter2) is not allowed.

#### Usage Notes
* Phrases that includes quotes, like `content:"It's a wonderful day"`
* Phrases that includes quotes, like `attribute:'She said "Hello World"'`

## Usage
POM
```xml
    <dependencies>
        <dependency>
            <groupId>com.github.sidhant92</groupId>
            <artifactId>boolparser</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
```
Gradle
```
dependencies {
	implementation "com.github.sidhant92:boolparser:1.0.3"
}
```

```
final PEGBoolExpressionParser boolExpressionParser = new PEGBoolExpressionParser();
final Optional<Node> nodeOptional = boolExpressionParser.parseExpression("name:test");
```

### Node Types
####
StringToken
```
private final String field;

private final String value;
```

####
NumericToken
```
private final String field;

private final Object value;

private final Operator operator;

private final DataType dataType;
```

####
NumericRangeToken
```
private final String field;

private final Object fromValue;

private final Object toValue;

private final DataType fromDataType;

private final DataType toDataType;
```

####
BoolExpression
```
private final List<Node> orOperations = new ArrayList<>();

private final List<Node> andOperations = new ArrayList<>();

private final List<Node> notOperations = new ArrayList<>();
```

## Applications

### Boolean Expression Evaluator

The library can be used to evaluate a boolean expression.

The following Data Types are supported:
1. String
2. Integers
3. Decimals
4. Boolean
5. App Version

Usage examples:

Simple Numerical Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("age", 26);
final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age >= 27", data);
assertTrue(booleanOptional.isPresent());
assertFalse(booleanOptional.get());
```
Boolean Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("age", 25);
data.put("name", "sid");
final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid AND age = 25", data);
assertTrue(booleanOptional.isPresent());
assertTrue(booleanOptional.get());
```
Nested Boolean Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("age", 25);
data.put("name", "sid");
data.put("num", 45);
final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name:sid AND (age = 25 OR num = 44)", data);
assertTrue(booleanOptional.isPresent());
assertTrue(booleanOptional.get());
```
App Version Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("app_version", "1.5.9");
final Optional<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version < 1.5.10", data);
assertTrue(booleanOptional.isPresent());
assertTrue(booleanOptional.get());
```

The return type is `Optional<Boolean>`. If its absent which means parsing has failed and any fallback can be used.

[For a complete list of examples please check out the test file](src/test/java/com/github/sidhant92/boolparser/application/BooleanExpressionEvaluatorTest.java)