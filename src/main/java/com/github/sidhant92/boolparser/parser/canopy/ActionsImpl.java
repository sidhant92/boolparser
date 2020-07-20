package com.github.sidhant92.boolparser.parser.canopy;

import java.util.List;
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
            value = Integer.parseInt(stringValue);
            valueDataType = DataType.INTEGER;
        } else {
            value = Double.parseDouble(stringValue);
            valueDataType = DataType.DOUBLE;
        }
        final NumericNode numericNode = new NumericNode(field, value, operator, valueDataType);
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
            fromValue = Integer.parseInt(fromStringValue);
            fromValueDataType = DataType.INTEGER;
        } else {
            fromValue = Double.parseDouble(fromStringValue);
            fromValueDataType = DataType.DOUBLE;
        }
        if (toStringValue.indexOf('.') == -1) {
            toValue = Integer.parseInt(toStringValue);
            toValueDataType = DataType.INTEGER;
        } else {
            toValue = Double.parseDouble(toStringValue);
            toValueDataType = DataType.DOUBLE;
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
        for (TreeNode node : elements.get(1)) {
            booleanNode.addClause(elements.get(0), LogicalOperationType.AND);
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
            booleanNode = (BooleanNode) elements.get(0);
        } else {
            booleanNode.addClause(elements.get(0), LogicalOperationType.OR);
        }
        for (TreeNode node : elements.get(1)) {
            booleanNode.addClause(node.get(Label.logical_and), LogicalOperationType.OR);
        }
        if (booleanNode.getAndQueries().size() == 0 && booleanNode.getOrQueries().size() == 0) {
            return elements.get(0);
        } else {
            return booleanNode;
        }
    }
}
