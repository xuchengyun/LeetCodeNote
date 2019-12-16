package LCQuestions;

import Utils.TreeNode;

public class _106_ConstructBinaryTreeFromInorderandPostorderTraversal {

    int pIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        if (inorder.length == 0) {
            return null;
        }
        pIdx = postorder.length - 1;
        return build(inorder, postorder, 0, pIdx);
    }

    private TreeNode build(int[] inorder, int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[pIdx--]);
        int index = findIdx(inorder, node.val, end, start);
        node.right = build(inorder, postorder, index + 1, end);
        node.left = build(inorder, postorder, start, index - 1);
        return node;
    }

    private int findIdx(int[] inorder, int val, int end, int start) {
        for (int i = end; i >= start; i--) {
            if (inorder[i] == val) return i;
        }
        return 0;
    }
}
