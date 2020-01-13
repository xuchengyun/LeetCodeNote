package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _431_EncodeNaryTreetoBinaryTree {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        List<Node> children = root.children;
        TreeNode tmp = node;
        if (!children.isEmpty()) {
            for (int i = 0; i < children.size(); i++) {
                if (i == 0) {
                    node.left = encode(children.get(0));
                    tmp = node.left;
                } else {
                    tmp.right = encode(children.get(i));
                    tmp = tmp.right;
                }
            }
        }
        return node;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node result = new Node(root.val, new ArrayList<>());
        TreeNode cur = root.left;
        while (cur != null) {
            result.children.add(decode(cur));
            cur = cur.right;
        }
        return result;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
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
