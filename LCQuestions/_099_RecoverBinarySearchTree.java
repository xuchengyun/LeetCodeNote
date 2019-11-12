package LCQuestions;

import Utils.TreeNode;

import java.util.Stack;

public class _099_RecoverBinarySearchTree {
    // iteration
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode x = null, y = null, prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null) {
                if (prev.val > root.val) {
                    if (x == null) {
                        x = prev;
                    }
                    y = root;
                }
            }
            prev = root;
            root = root.right;
        }

        swap(x, y);
    }

    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }


    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;


    // recursion
    public void recoverTree1(TreeNode root) {
        // In order traversal to find the two elements
        traverse(root);
        swap(first, second);
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        // Start of "do some business",
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        traverse(root.right);
    }
}
