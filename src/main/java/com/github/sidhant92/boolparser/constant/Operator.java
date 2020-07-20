package com.github.sidhant92.boolparser.constant;

import java.util.Arrays;
import java.util.Optional;
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
    EQUALS("="),
    GREATER_THAN(">"),
    GREATER_THAN_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<="),
    NOT_EQUAL("!=");

    private final String symbol;

    public static Optional<Operator> getOperatorFromSymbol(final String symbol) {
        return Arrays.stream(Operator.values()).filter(operator -> operator.symbol.equals(symbol)).findFirst();
    }
}
