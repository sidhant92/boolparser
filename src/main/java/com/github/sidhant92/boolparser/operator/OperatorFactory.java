package com.github.sidhant92.boolparser.operator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 28/07/2020
 */
public class OperatorFactory {
    private static final Map<Operator, AbstractOperator> operatorMap = new EnumMap<>(Operator.class);

    private OperatorFactory() {
        super();
    }

    public static void initialize() {
        operatorMap.put(Operator.EQUALS, new EqualsOperator());
        operatorMap.put(Operator.GREATER_THAN, new GreaterThanOperator());
        operatorMap.put(Operator.GREATER_THAN_EQUAL, new GreaterThanEqualOperator());
        operatorMap.put(Operator.LESS_THAN, new LessThanOperator());
        operatorMap.put(Operator.LESS_THAN_EQUAL, new LessThanEqualOperator());
        operatorMap.put(Operator.NOT_EQUAL, new NotEqualsOperator());
        operatorMap.put(Operator.REVERSE_MATCH_ALL, new ReverseMatchAllOperator());
    }

    public static AbstractOperator getOperator(final Operator operator) {
        return operatorMap.get(operator);
    }

    public static List<AbstractOperator> getAllOperators() {
        return new ArrayList<>(operatorMap.values());
    }

    public static void register(final AbstractOperator abstractOperator) {
        operatorMap.put(abstractOperator.getOperator(), abstractOperator);
    }
}
