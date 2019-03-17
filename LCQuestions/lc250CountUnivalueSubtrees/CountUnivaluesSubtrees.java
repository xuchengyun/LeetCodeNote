package LCQuestions.lc250CountUnivalueSubtrees;

public class CountUnivaluesSubtrees {
    // this method is not efficient, post order traverse will be more efficient
    int res = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return res;
        if (isUnival(root, root.val)) res++;
        countUnivalSubtrees(root.left);
        countUnivalSubtrees(root.right);
        return res;
    }

    private boolean isUnival(TreeNode node, int val) {
        if (node == null) return true;
        return node.val == val && isUnival(node.left, val) && isUnival(node.right, val);
    }

    // post order traverse
    public int coundUnivalSubtrees1(TreeNode root) {
        if (root == null) return res;
        postorder(root);
        return res;
    }

    private boolean postorder(TreeNode node) {
        if (node == null) return true;
        boolean left = postorder(node.left);
        boolean right = postorder(node.right);
        if (left && right) {
            if (node.left != null && node.left.val != node.val) return false;
            if (node.right != null && node.right.val != node.val) return false;
            res++;
            return true;
        }
        return false;
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
