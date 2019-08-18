package LCQuestions;

import Utils.TreeNode;

public class _337_HouseRobberIII {
    /**
     *
     偷奇数层还是偶数层
     https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left);
            val += rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left);
            val += rob(root.right.right);
        }
        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }
}
