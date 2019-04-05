package LCQuestions.lc449SerializeAndDeserializeBST;

import Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBST {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return "null";
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode node, StringBuilder sb) {
        sb.append(node.val).append(',');
        if (node.left != null) {
            preOrder(node.left, sb);
        }
        if (node.right != null) {
            preOrder(node.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        if (data.equals("null")) {
            return null;
        }
        Queue<String> q = new LinkedList<>();
        String[] sArr = data.split(",");
        for (String s : sArr) {
            q.offer(s);
        }
        return deserialize(q);
    }
    private TreeNode deserialize(Queue<String> q) {
        if (q.isEmpty()) return null;
        String s = q.poll();
        int val = Integer.parseInt(s);
        Queue<String> smaller = new LinkedList<>();
        while (!q.isEmpty() && Integer.parseInt(q.peek()) < val) {
            smaller.offer(q.poll());
        }
        TreeNode root = new TreeNode(val);
        root.left = deserialize(smaller);
        root.right = deserialize(q);
        return root;
    }

}
