package com.github.sidhant92.boolparser.constant;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.github.sidhant92.boolparser.operator.AbstractOperator;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 19/07/2020
 */
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Getter
public enum Operator {
    EQUALS,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    NOT_EQUAL;

    public static Optional<Operator> getOperatorFromSymbol(final String symbol) {
        final List<AbstractOperator> abc = OperatorFactory.getAllOperators();
        return OperatorFactory.getAllOperators().stream().filter(operator -> operator.getSymbol().equals(symbol)).map(AbstractOperator::getOperator)
                              .findFirst();
    }
}
