package com.github.sidhant92.boolparser.constant;

import lombok.AllArgsConstructor;

/**
 * @author sidhant.aggarwal
 * @since 27/07/2020
 */
@AllArgsConstructor
public enum OperatorType {
    binary(1, 1),
    ternary(2, 2),
    n_ary(1, Integer.MAX_VALUE);

    private int minOperandLength;

    private int maxOperandLength;

    public boolean isOperandListValid(Object... operands) {
        return (operands != null && operands.length >= minOperandLength && operands.length <= maxOperandLength);
    }
}
