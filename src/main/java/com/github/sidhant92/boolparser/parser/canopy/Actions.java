package com.github.sidhant92.boolparser.parser.canopy;

import java.util.List;

public interface Actions {
    TreeNode make_app_version_token(String input, int start, int end, List<TreeNode> elements);
    TreeNode make_logical_and(String input, int start, int end, List<TreeNode> elements);
    TreeNode make_logical_or(String input, int start, int end, List<TreeNode> elements);
    TreeNode make_numeric_range_token(String input, int start, int end, List<TreeNode> elements);
    TreeNode make_numeric_token(String input, int start, int end, List<TreeNode> elements);
    TreeNode make_primary(String input, int start, int end, List<TreeNode> elements);
    TreeNode make_string_token(String input, int start, int end, List<TreeNode> elements);
}
