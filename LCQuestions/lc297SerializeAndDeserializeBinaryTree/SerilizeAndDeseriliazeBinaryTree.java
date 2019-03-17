package LCQuestions.lc297SerializeAndDeserializeBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class SerilizeAndDeseriliazeBinaryTree {
    // bfs
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                sb.append("null");
                continue;
            }
            int val = cur.val;
            sb.append(val + ",");
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "" || data == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.offer(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode cur = q.poll();
            if (!values[i].equals("null")) {
                cur.left = new TreeNode(Integer.parseInt(values[i]));
                q.offer(cur.left);
            }

            if (!values[++i].equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(values[i]));
                q.offer(cur.right);
            }
        }
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
