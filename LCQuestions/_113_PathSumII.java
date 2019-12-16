package LCQuestions;

import Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _113_PathSumII {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, 0, sum, new ArrayList<>());
        return res;
    }

    private void helper(TreeNode root, int i, int sum, List<Integer> temp) {
        if (root.left == null && root.right == null) {
            if (i + root.val == sum) {
                temp.add(root.val);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
                return;
            }
        }
        temp.add(root.val);
        if (root.left != null) {
            helper(root.left, i + root.val, sum, temp);
        }
        if (root.right != null) {
            helper(root.right, i + root.val, sum, temp);
        }
        temp.remove(temp.size() - 1);
    }

    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        // sum along the current path
        int pathSum = 0;
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null || !s.isEmpty()) {
            // go down all the way to the left leaf node
            // add all the left nodes to the stack
            while (curr != null) {
                s.push(curr);
                path.add(curr.val);
                pathSum += curr.val;
                curr = curr.left;
            }
            curr = s.peek();
            // check leaf
            if (curr.left == null && curr.right == null && pathSum == sum) {
                list.add(new ArrayList<>(path));
            }
            if (curr.right != null && curr.right != prev) {
                curr = curr.right;
            } else {
                s.pop();
                prev = curr;
                pathSum -= curr.val;
                path.remove(path.size() - 1);
                curr = null;
            }
        }
        return list;
    }
}
