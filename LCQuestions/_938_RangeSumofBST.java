package LCQuestions;

import Utils.TreeNode;

import java.util.Stack;

public class _938_RangeSumofBST {
    // recursion
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val > R) {
            rangeSumBST(root.left, L, R);
        }
        if (root.val > L) {
            rangeSumBST(root.right, L, R);
        }
        return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
    }

    // iteration
    private int rangeSumBST1(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.val <= R && cur.val >= L) {
                sum += cur.val;
            }
            if (cur.val > L) {
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
            if (cur.val < R) {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            }
        }
        return sum;
    }

    // iteration
    private int rangeSumBST2(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                if (root.val <= R && root.val >= L) {
                    sum += root.val;
                }
                if (root.val > L && root.left != null) {
                    root = root.left;
                } else {
                    root = null;
                }
            } else {
                TreeNode cur = stack.pop();
                if (cur.val < R && cur.right != null) {
                    root = cur.right;
                } else {
                    root = null;
                }
            }
        }
        return sum;
    }
}
