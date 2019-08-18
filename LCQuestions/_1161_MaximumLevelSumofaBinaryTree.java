package LCQuestions;

import Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _1161_MaximumLevelSumofaBinaryTree {

    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        int max = Integer.MIN_VALUE;
        int res = 0;
        int level = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            level++;
            if (sum > max) {
                max = sum;
                res = level;
            }

        }
        return res;
    }
}
