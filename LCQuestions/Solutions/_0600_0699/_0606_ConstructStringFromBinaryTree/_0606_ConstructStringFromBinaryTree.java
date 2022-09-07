package LCQuestions.Solutions._0600_0699._0606_ConstructStringFromBinaryTree;

import Utils.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class _0606_ConstructStringFromBinaryTree {
    // DFS Recursion
    public String tree2str(TreeNode root) {
        return traverse(root);
    }

    private String traverse(TreeNode node) {
        if (node == null) return "";
        String left = traverse(node.left);
        String right = traverse(node.right);
        if (left.equals("") && right.equals("")) {
            return "" + node.val;
        }
        if (left.equals("")) {
            return node.val + "()("+ right + ")";
        }
        if (right.equals("")) {
            return node.val + "("+ left + ")";
        }
        return node.val + "(" + left + ")("+ right + ")";
    }


    // DFS Stack
    public String tree2str1(TreeNode root) {
        if (root == null)
            return "";
        TreeNode dummy = new TreeNode(0);
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> s = new ArrayDeque<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            /*Every time you append a open parentheses,
            you push dummy node onto stack to remind that we also need a close parentheses when pop this dummy node.            *
             */
            if (cur == dummy) {
                sb.append(')');
                continue;
            }
            sb.append('(').append(cur.val);
            s.push(dummy);
            if (cur.left == null && cur.right != null) {
                sb.append("()");
            }
            if (cur.right != null) {
                s.push(cur.right);
            }
            if (cur.left != null) {
                s.push(cur.left);
            }
        }
        return sb.substring(1, sb.length() - 1);
    }

}
