package LCQuestions.Solutions._2400_2499._2415_ReverseOddLevelsOfBinaryTree;

import Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _2415_ReverseOddLevelsOfBinaryTree {
    // BFS
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        int level = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<TreeNode> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = dq.poll();
                if (level % 2 == 1) {
                    l.add(cur);
                }
                if (cur.left != null) {
                    dq.offer(cur.left);
                }
                if (cur.right != null) {
                    dq.offer(cur.right);
                }
            }
            level++;
            if (l.size() > 1) {
                reverse(l);
            }
        }
        return root;
    }

    private void reverse(List<TreeNode> l) {
        if (l.size() <= 1) {
            return;
        }

        int left = 0, right = l.size() - 1;
        while (left < right) {
            TreeNode leftNode = l.get(left), rightNode = l.get(right);
            int tmp = rightNode.val;
            rightNode.val = leftNode.val;
            leftNode.val = tmp;
            left++;
            right--;
        }
    }

    // DFS
    public TreeNode reverseOddLevels2(TreeNode root) {
        if (root == null) return null;
        traverse(root.left, root.right, 1);
        return root;
    }

    private void traverse(TreeNode l, TreeNode r, int level) {
        if (l == null || r == null) return;
        if (level % 2 == 1) {
            int tmp = l.val;
            l.val = r.val;
            r.val = tmp;
        }

        traverse(l.left, r.right, level + 1);
        traverse(l.right, r.left, level + 1);
    }

}
