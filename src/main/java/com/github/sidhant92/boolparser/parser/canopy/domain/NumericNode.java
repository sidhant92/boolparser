package com.github.sidhant92.boolparser.parser.canopy.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.parser.canopy.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 08/07/2020
 */
@AllArgsConstructor
@Getter
public class NumericNode extends TreeNode {
    final String field;

    final Object value;

    final String operator;

    final DataType dataType;
}
