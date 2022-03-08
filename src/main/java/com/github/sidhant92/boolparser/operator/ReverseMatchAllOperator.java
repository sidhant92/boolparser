package com.github.sidhant92.boolparser.operator;

import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.SetUtils;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

public class ReverseMatchAllOperator extends AbstractOperator {
    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final DataType dataType,
                                                              final Object leftOperand, final Object... rightOperands) {
        assert containerDataType.getClassType() == Set.class;
        final Optional<Set<?>> leftValueOptional = containerDataType.getValue(dataType, leftOperand);
        final Optional<Set<?>> rightValueOptional = containerDataType.getValue(dataType, rightOperands[0]);
        return leftValueOptional.flatMap(leftValue -> rightValueOptional.map(rightValue -> SetUtils.difference(leftValue,
                rightValue).isEmpty())).orElse(false);
    }

    @Override
    public Operator getOperator() {
        return Operator.REVERSE_MATCH_ALL;
    }

    @Override
    public String getSymbol() {
        return "rev_all";
    }
}