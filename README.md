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
	implementation "com.github.sidhant92:boolparser:1.0.0"
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