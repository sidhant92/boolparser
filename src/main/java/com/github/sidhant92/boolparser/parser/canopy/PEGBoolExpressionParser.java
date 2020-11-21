package com.github.sidhant92.boolparser.parser.canopy;

import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import com.github.sidhant92.boolparser.parser.canopy.domain.BooleanNode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 15/07/2020
 */
@Slf4j
public class PEGBoolExpressionParser implements BoolExpressionParser {
    private final Actions actionsImpl;

    public PEGBoolExpressionParser() {
        actionsImpl = new ActionsImpl();
        DataTypeFactory.initialize();
        OperatorFactory.initialize();
    }

    @Override
    public Optional<Node> parseExpression(final String expression) {
        try {
            if (expression == null || expression.equals("")) {
                return Optional.empty();
            }
            final TreeNode node = Filters.parse(expression, actionsImpl);
            if (node instanceof BooleanNode) {
                return Optional.of(new ExpressionMapper().getFilterQuery((BooleanNode) node));
            }
        } catch (final Exception ex) {
            log.error("Expression could not be parsed with error {}", ex.getMessage(), ex);
        }
        return Optional.empty();
    }
}
