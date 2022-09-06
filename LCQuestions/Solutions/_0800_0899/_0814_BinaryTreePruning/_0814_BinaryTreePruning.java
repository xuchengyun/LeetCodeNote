package LCQuestions.Solutions._0800_0899._0814_BinaryTreePruning;

import Utils.TreeNode;

public class _0814_BinaryTreePruning {
    // DFS-1
    public TreeNode pruneTree(TreeNode root) {
        return traverse(root) ? null : root;
    }

    private boolean traverse(TreeNode node)  {
        if (node == null) return true;
        boolean left = traverse(node.left);
        boolean right = traverse(node.right);
        if (left) {
            node.left = null;
        }
        if (right) {
            node.right = null;
        }
        return node.val == 0 && left && right;
    }

    // DFS-2
    public TreeNode pruneTree1(TreeNode root) {
        return traverse1(root);
    }

    private TreeNode traverse1(TreeNode node)  {
        if (node == null) return null;
        node.left = traverse1(node.left);
        node.right = traverse1(node.right);
        if (node.val == 0 && node.left == null && node.right == null) {
            return null;
        }
        return node;
    }
}
