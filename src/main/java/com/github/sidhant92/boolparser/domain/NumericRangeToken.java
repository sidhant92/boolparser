package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.NodeType;
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
public class NumericRangeToken extends Node {
    private final String field;

    private final Object fromValue;

    private final Object toValue;

    private final DataType fromDataType;

    private final DataType toDataType;

    @Override
    public NodeType getNodeType() {
        return NodeType.NUMERIC_RANGE_TOKEN;
    }
}
