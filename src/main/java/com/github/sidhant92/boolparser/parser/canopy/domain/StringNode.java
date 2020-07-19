package com.github.sidhant92.boolparser.parser.canopy.domain;

import com.github.sidhant92.boolparser.parser.canopy.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 27/05/19
 */
@AllArgsConstructor
@Getter
public class StringNode extends TreeNode {
    final String field;

    final String value;
}
