package LCQuestions;

import Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _965_UnivaluedBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        if (root.left != null) {
            if (!isUnivalTree(root.left))
                return false;
            if (root.left.val != root.val)
                return false;
        }

        if (root.right != null) {
            if (!isUnivalTree(root.right))
                return false;
            if (root.right.val != root.val)
                return false;
        }
        return true;
    }

    //bfs
    public boolean isUnivalTree1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        int val = root.val;
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.val != val) {
                return false;
            }
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        return true;
    }
}
