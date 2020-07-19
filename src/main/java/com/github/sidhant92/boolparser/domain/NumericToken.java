package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.constant.Operator;
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
public class NumericToken extends Node {
    private final String field;

    private final Object value;

    private final Operator operator;

    private final DataType dataType;

    @Override
    public NodeType getNodeType() {
        return NodeType.NUMERIC_TOKEN;
    }
}
