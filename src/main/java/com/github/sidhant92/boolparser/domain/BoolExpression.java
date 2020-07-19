package com.github.sidhant92.boolparser.domain;

import java.util.ArrayList;
import java.util.List;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sidhant.aggarwal
 * @since 15/07/2020
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BoolExpression extends Node {
    private final List<Node> orOperations = new ArrayList<>();

    private final List<Node> andOperations = new ArrayList<>();

    private final List<Node> notOperations = new ArrayList<>();

    public void addNode(final Node node, final LogicalOperationType logicalOperationType) {
        switch (logicalOperationType) {
            case AND:
                andOperations.add(node);
                break;
            case OR:
                orOperations.add(node);
                break;
            case NOT:
                notOperations.add(node);
                break;
            default:
        }
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.BOOL_EXPRESSION;
    }
}
