package com.github.sidhant92.boolparser.parser.canopy.domain;

import java.util.List;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.parser.canopy.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReverseAllMatchNode extends TreeNode {
    final String field;

    final List<Object> values;

    final DataType dataType;
}
