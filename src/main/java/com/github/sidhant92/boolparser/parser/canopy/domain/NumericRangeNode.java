package com.github.sidhant92.boolparser.parser.canopy.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.parser.canopy.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 09/07/2020
 */
@AllArgsConstructor
@Getter
public class NumericRangeNode extends TreeNode {
    final String field;

    final Object fromValue;

    final Object toValue;

    final DataType fromDataType;

    final DataType toDataType;
}
