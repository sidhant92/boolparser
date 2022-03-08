package com.github.sidhant92.boolparser.domain;

import java.util.List;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.NodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReverseAllMatchToken extends Node{
    final String field;

    final List<Object> values;

    final DataType dataType;

    @Override
    public NodeType getNodeType() {
        return NodeType.REVERSE_ALL_MATCH_TOKEN;
    }
}
