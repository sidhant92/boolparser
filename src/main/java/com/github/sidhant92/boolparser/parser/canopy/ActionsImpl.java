package com.github.sidhant92.boolparser.parser.canopy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.parser.canopy.domain.NumericRangeNode;
import com.github.sidhant92.boolparser.parser.canopy.domain.NumericNode;
import com.github.sidhant92.boolparser.parser.canopy.domain.BooleanNode;
import com.github.sidhant92.boolparser.parser.canopy.domain.StringNode;

/**
 * @author sidhant.aggarwal
 * @since 27/05/19
 */
public class ActionsImpl implements Actions {
    @Override
    public TreeNode make_string_token(String input, int start, int end, List<TreeNode> elements) {
        final StringNode stringNode = new StringNode(getCapturedString(elements.get(2).text), getCapturedString(elements.get(6).text));
        return checkNotExpression(elements, stringNode);
    }

    @Override
    public TreeNode make_numeric_token(String input, int start, int end, List<TreeNode> elements) {
        final String field = getCapturedString(elements.get(2).text);
        final String operator = elements.get(4).text;
        final String stringValue = elements.get(6).text;
        Object value;
        DataType valueDataType;
        if (stringValue.indexOf('.') == -1) {
            valueDataType = getNumericDataType(stringValue);
            value = getValue(stringValue, valueDataType);
        } else {
            value = Double.parseDouble(stringValue);
            valueDataType = DataType.DECIMAL;
        }
        final NumericNode numericNode = new NumericNode(field, value, operator, valueDataType);
        return checkNotExpression(elements, numericNode);
    }

    @Override
    public TreeNode make_app_version_token(String input, int start, int end, List<TreeNode> elements) {
        final String field = getCapturedString(elements.get(2).text);
        final String operator = elements.get(4).text;
        final String value = elements.get(6).text;
        final NumericNode numericNode = new NumericNode(field, value, operator, DataType.APP_VERSION);
        return checkNotExpression(elements, numericNode);
    }

    @Override
    public TreeNode make_numeric_range_token(String input, int start, int end, List<TreeNode> elements) {
        final String field = getCapturedString(elements.get(2).text);
        final String fromStringValue = elements.get(6).text;
        final String toStringValue = elements.get(10).text;
        Object fromValue, toValue;
        DataType fromValueDataType, toValueDataType;
        if (fromStringValue.indexOf('.') == -1) {
            fromValueDataType = getNumericDataType(fromStringValue);
            fromValue = getValue(fromStringValue, fromValueDataType);
        } else {
            fromValue = Double.parseDouble(fromStringValue);
            fromValueDataType = DataType.DECIMAL;
        }
        if (toStringValue.indexOf('.') == -1) {
            toValueDataType = getNumericDataType(toStringValue);
            toValue = getValue(toStringValue, toValueDataType);
        } else {
            toValue = Double.parseDouble(toStringValue);
            toValueDataType = DataType.DECIMAL;
        }
        final NumericRangeNode numericRangeNode = new NumericRangeNode(field, fromValue, toValue, fromValueDataType, toValueDataType);
        return checkNotExpression(elements, numericRangeNode);
    }

    public TreeNode make_primary(String input, int start, int end, List<TreeNode> elements) {
        return elements.get(1);
    }

    private TreeNode checkNotExpression(final List<TreeNode> elements, final TreeNode node) {
        if (elements.get(0).text.equals("NOT")) {
            final BooleanNode booleanNode = new BooleanNode();
            booleanNode.addClause(node, LogicalOperationType.NOT);
            return booleanNode;
        } else {
            return node;
        }
    }

    private DataType getNumericDataType(final String value) {
        final Optional<Integer> integerOptional = parseInteger(value);
        return integerOptional.isPresent() ? DataType.INTEGER : DataType.LONG;
    }

    private String getCapturedString(final String input) {
        final int firstPosition = 0;
        final int lastPosition = input.length() - 1;
        if (firstPosition != lastPosition && input.charAt(firstPosition) == '"' && input.charAt(lastPosition) == '"') {
            return input.substring(1, input.length() - 1);
        } else if (firstPosition != lastPosition && input.charAt(firstPosition) == '\'' && input.charAt(lastPosition) == '\'') {
            return input.substring(1, input.length() - 1);
        } else {
            return input;
        }
    }

    public TreeNode make_logical_and(String input, int start, int end, List<TreeNode> elements) {
        BooleanNode booleanNode = new BooleanNode();
        if (elements.get(1).iterator().hasNext()) {
            booleanNode.addClause(elements.get(0), LogicalOperationType.AND);
        }
        for (TreeNode node : elements.get(1)) {
            booleanNode.addClause(node.get(Label.primary), LogicalOperationType.AND);
        }
        if (booleanNode.getAndQueries().size() == 0) {
            return elements.get(0);
        } else {
            return booleanNode;
        }
    }

    public TreeNode make_logical_or(String input, int start, int end, List<TreeNode> elements) {
        BooleanNode booleanNode = new BooleanNode();
        if (elements.get(0) instanceof BooleanNode) {
            if (elements.get(1).elements.isEmpty()) {
                booleanNode = (BooleanNode) elements.get(0);
            } else {
                booleanNode.addClause(elements.get(0), LogicalOperationType.OR);
                for (TreeNode node : elements.get(1)) {
                    booleanNode.addClause(node.get(Label.logical_and), LogicalOperationType.OR);
                }
            }
        } else {
            booleanNode.addClause(elements.get(0), LogicalOperationType.OR);
            for (TreeNode node : elements.get(1)) {
                booleanNode.addClause(node.get(Label.logical_and), LogicalOperationType.OR);
            }
        }
        if (booleanNode.getAndQueries().size() == 0 && booleanNode.getOrQueries().size() == 0) {
            return elements.get(0);
        } else {
            return booleanNode;
        }
    }

    public TreeNode make_decimal_list(String input, int start, int end, List<TreeNode> elements) {
        final BooleanNode booleanNode = new BooleanNode();
        final List<String> list = Arrays.stream(elements.get(7).text.split(",")).map(String::trim).collect(Collectors.toList());
        final DataType dataType = findNumericDataTypeForList(list);
        final List<TreeNode> nodes = list.stream().map(data -> new NumericNode(elements.get(2).text, getValue(data, dataType), "=", dataType))
                                         .collect(Collectors.toList());
        nodes.forEach(a -> booleanNode.addClause(a, LogicalOperationType.OR));
        return checkNotExpression(elements, booleanNode);
    }

    private DataType findNumericDataTypeForList(final List<String> list) {
        if (list.stream().anyMatch(a -> a.indexOf('.') != -1)) {
            return DataType.DECIMAL;
        } else {
            if (list.stream().allMatch(a -> parseInteger(a).isPresent())) {
                return DataType.INTEGER;
            } else {
                return DataType.LONG;
            }
        }
    }

    private Object getValue(final String value, final DataType dataType) {
        switch (dataType) {
            case INTEGER:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case DECIMAL:
                return Double.parseDouble(value);
            default:
                return value;
        }
    }

    private Optional<Integer> parseInteger(final String number) {
        try {
            return Optional.of(Integer.parseInt(number));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public TreeNode make_string_list(String input, int start, int end, List<TreeNode> elements) {
        final BooleanNode booleanNode = new BooleanNode();
        final List<String> list = Arrays.stream(elements.get(7).text.split(",")).map(String::trim).map(element -> element.replace("'", ""))
                                        .collect(Collectors.toList());
        final List<TreeNode> nodes = list.stream().map(data -> new StringNode(elements.get(2).text, data)).collect(Collectors.toList());
        nodes.forEach(a -> booleanNode.addClause(a, LogicalOperationType.OR));
        return checkNotExpression(elements, booleanNode);
    }
}
