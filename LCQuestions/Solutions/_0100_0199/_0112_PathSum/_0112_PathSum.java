package LCQuestions.Solutions._0100_0199._0112_PathSum;

import Utils.TreeNode;

public class _0112_PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return traverse(root, targetSum);
    }

    private boolean traverse(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {
            return node.val == sum;
        }

        return traverse(node.left, sum - node.val) || traverse(node.right, sum - node.val);
    }
}
