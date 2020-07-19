package com.github.sidhant92.boolparser.parser.canopy.domain;

import java.util.ArrayList;
import java.util.List;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.parser.canopy.TreeNode;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 27/05/19
 */
@Getter
public class BooleanNode extends TreeNode {
    private List<TreeNode> orQueries;

    private List<TreeNode> andQueries;

    private List<TreeNode> notQueries;

    public BooleanNode() {
        orQueries = new ArrayList<>();
        andQueries = new ArrayList<>();
        notQueries = new ArrayList<>();
    }

    public void addClause(final TreeNode query, final LogicalOperationType clause) {
        if (clause == LogicalOperationType.AND) {
            andQueries.add(query);
        } else if (clause == LogicalOperationType.OR) {
            orQueries.add(query);
        } else {
            notQueries.add(query);
        }
    }
}
