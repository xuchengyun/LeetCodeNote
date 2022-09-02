package LCQuestions.Solutions._0600_0699._0637_AverageOfLevelsInBinaryTree;

import Utils.TreeNode;

import java.util.*;

public class _0637_AverageOfLevelsInBinaryTree {
    // BFS
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Double> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int n = q.size();
            double cur = 0.0;
            for (int i = 0; i < n; i++) {
                TreeNode curNode = q.poll();
                cur += Objects.requireNonNull(curNode).val;
                if (curNode.left != null) {
                    q.offer(curNode.left);
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                }
            }
            res.add(cur / n);
        }
        return res;
    }

    // DFS
    public List<Double> averageOfLevels1(TreeNode root) {
        // User 2 list to store sum and num of nodes for each layer
        List<Double> sum = new ArrayList<>();
        List<Integer> cnt = new ArrayList<>();
        dfs(sum, cnt, root, 0);

        List<Double> res = new ArrayList<>();
        for (int i = 0; i < sum.size(); i++) {
            res.add(sum.get(i)/cnt.get(i));
        }
        return res;
    }

    public void dfs(List<Double> sum, List<Integer> cnt, TreeNode node, int i) {
        if (node == null) return;
        if (sum.size() <= i) {
            sum.add((double)node.val);
            cnt.add(1);
        } else {
            sum.set(i, sum.get(i) + node.val);
            cnt.set(i, cnt.get(i) + 1);
        }
        dfs(sum, cnt, node.left, i + 1);
        dfs(sum, cnt, node.right, i + 1);
    }
}
