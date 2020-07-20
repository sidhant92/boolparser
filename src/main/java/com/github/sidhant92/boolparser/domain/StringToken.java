package com.github.sidhant92.boolparser.domain;

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
public class StringToken extends Node {
    private final String field;

    private final String value;

    @Override
    public NodeType getNodeType() {
        return NodeType.STRING_TOKEN;
    }
}
