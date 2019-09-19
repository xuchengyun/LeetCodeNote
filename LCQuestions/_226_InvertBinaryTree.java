package LCQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class _226_InvertBinaryTree {
    // recursion
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree1(root.right);
        root.right = invertTree1(temp);
        return root;
    }

    // iteration
    public TreeNode invertTree2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            cur.left = right;
            cur.right = left;
            if (left != null) q.offer(left);
            if (right != null) q.offer(right);
        }
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
