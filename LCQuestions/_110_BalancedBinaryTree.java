package LCQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _110_BalancedBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // brute force
    // time: n * lg(n)
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);

        return Math.abs(right - left) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        return !(checkDepth(root) == -1);
    }

    //iteration
    public boolean isBalanced2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        Map<TreeNode, Integer> map = new HashMap<>();
        while (!s.isEmpty() || cur != null) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = s.peek();
                if (cur.right != null && cur.right != pre) {
                    cur = cur.right;
                } else {
                    int left = cur.left != null ? map.get(cur.left) : 0;
                    int right = cur.right != null ? map.get(cur.right) : 0;
                    if (Math.abs(left - right) > 1) {
                        return false;
                    }
                    map.put(cur, Math.max(left, right) + 1);
                    s.pop();
                    pre = cur;
                }
            }

        }
        return true;
    }


    private int checkDepth(TreeNode root) {
        if (root == null) return 0;
        int left = checkDepth(root.left);
        if (left == -1) return -1;
        int right = checkDepth(root.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }


    private int getDepth(TreeNode node) {
        if (node == null) return 0;
        int left = getDepth(node.left);
        int right = getDepth(node.right);
        return Math.max(left, right) + 1;
    }
}
