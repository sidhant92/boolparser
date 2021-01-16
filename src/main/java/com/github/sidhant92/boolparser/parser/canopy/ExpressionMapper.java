package com.github.sidhant92.boolparser.parser.canopy;

import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BoolExpression;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.StringToken;
import com.github.sidhant92.boolparser.parser.canopy.domain.NumericRangeNode;
import com.github.sidhant92.boolparser.parser.canopy.domain.NumericNode;
import com.github.sidhant92.boolparser.parser.canopy.domain.BooleanNode;
import com.github.sidhant92.boolparser.parser.canopy.domain.StringNode;

/**
 * @author sidhant.aggarwal
 * @since 15/07/2020
 */
public class ExpressionMapper {
    private Node processBooleanExpression(final BooleanNode booleanNode) {
        final BoolExpression boolExpression = new BoolExpression();
        booleanNode.getAndQueries().forEach(query -> processQuery(query, boolExpression, LogicalOperationType.AND));
        booleanNode.getOrQueries().forEach(query -> processQuery(query, boolExpression, LogicalOperationType.OR));
        booleanNode.getNotQueries().forEach(query -> processQuery(query, boolExpression, LogicalOperationType.NOT));
        return boolExpression;
    }

    private void processQuery(final TreeNode treeNode, final BoolExpression boolExpression, final LogicalOperationType logicalOperationType) {
        if (treeNode instanceof StringNode) {
            final StringNode stringNode = (StringNode) treeNode;
            boolExpression.addNode(mapToStringToken(stringNode), logicalOperationType);
        } else if (treeNode instanceof NumericNode) {
            final NumericNode numericNode = (NumericNode) treeNode;
            boolExpression.addNode(mapToNumericToken(numericNode), logicalOperationType);
        } else if (treeNode instanceof NumericRangeNode) {
            final NumericRangeNode numericRangeNode = (NumericRangeNode) treeNode;
            boolExpression.addNode(mapToNumericRangeToken(numericRangeNode), logicalOperationType);
        } else {
            boolExpression.addNode(processBooleanExpression((BooleanNode) treeNode), logicalOperationType);
        }
    }

    Node identifyAndMapToNode(final TreeNode treeNode) {
        if (treeNode instanceof StringNode) {
            final StringNode stringNode = (StringNode) treeNode;
            return mapToStringToken(stringNode);
        } else if (treeNode instanceof NumericNode) {
            final NumericNode numericNode = (NumericNode) treeNode;
            return mapToNumericToken(numericNode);
        } else if (treeNode instanceof NumericRangeNode) {
            final NumericRangeNode numericRangeNode = (NumericRangeNode) treeNode;
            return mapToNumericRangeToken(numericRangeNode);
        } else {
            return processBooleanExpression((BooleanNode) treeNode);
        }
    }

    private StringToken mapToStringToken(final StringNode stringNode) {
        return new StringToken(stringNode.getField(), stringNode.getValue());
    }

    private com.github.sidhant92.boolparser.domain.NumericToken mapToNumericToken(final NumericNode token) {
        return com.github.sidhant92.boolparser.domain.NumericToken.builder().field(token.getField()).value(token.getValue())
                                                                  .dataType(token.getDataType())
                                                                  .operator(Operator.getOperatorFromSymbol(token.getOperator()).orElse(null)).build();
    }

    private com.github.sidhant92.boolparser.domain.NumericRangeToken mapToNumericRangeToken(final NumericRangeNode token) {
        return com.github.sidhant92.boolparser.domain.NumericRangeToken.builder().field(token.getField()).fromValue(token.getFromValue())
                                                                       .fromDataType(token.getFromDataType()).toValue(token.getToValue())
                                                                       .toDataType(token.getToDataType()).build();
    }

    Node getFilterQuery(final BooleanNode booleanNode) {
        if (booleanNode.getAndQueries().size() == 1 && booleanNode.getOrQueries().isEmpty() && booleanNode.getNotQueries().isEmpty()) {
            return identifyAndMapToNode(booleanNode.getAndQueries().get(0));
        } else if (booleanNode.getOrQueries().size() == 1 && booleanNode.getAndQueries().isEmpty() && booleanNode.getNotQueries().isEmpty()) {
            return identifyAndMapToNode(booleanNode.getOrQueries().get(0));
        } else {
            return identifyAndMapToNode(booleanNode);
        }
    }
}
