package com.github.sidhant92.boolparser.parser;

import java.util.Optional;
import com.github.sidhant92.boolparser.domain.Node;

/**
 * @author sidhant.aggarwal
 * @since 15/07/2020
 */
public interface BoolExpressionParser {
    Optional<Node> parseExpression(final String expression);
}
