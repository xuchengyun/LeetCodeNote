package LCQuestions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _297_SerilizeAndDeseriliazeBinaryTree {
    // bfs
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                sb.append("null,");
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

    public String serialize1(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
        } else {
            sb.append(root.val + ",");
            dfs(root.left, sb);
            dfs(root.right, sb);
        }
    }

    public TreeNode deserialize1(String data) {
        Queue<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(",")));
        return dfs1(q);
    }

    private TreeNode dfs1(Queue<String> q) {
        String cur = q.poll();
        if (cur.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(cur));
        node.left = dfs1(q);
        node.right = dfs1(q);
        return node;
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
