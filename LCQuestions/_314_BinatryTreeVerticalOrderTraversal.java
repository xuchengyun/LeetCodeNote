package LCQuestions;

import Utils.TreeNode;

import java.util.*;

public class _314_BinatryTreeVerticalOrderTraversal {
    int min = 0;
    int max = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, 0);
        for (int i = min; i <= max; i++) {
            res.add(new ArrayList<>());
        }

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        q.offer(root);
        index.offer(-min);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int curIdx = index.poll();
            res.get(curIdx).add(cur.val);
            if (cur.left != null) {
                q.offer(cur.left);
                index.offer(curIdx - 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                index.offer(curIdx + 1);
            }
        }
        return res;
    }

    private void helper(TreeNode root, int index) {
        if (root == null) return;
        min = Math.min(min, index);
        max = Math.max(max, index);
        helper(root.left, index - 1);
        helper(root.right, index + 1);
    }

    public List<List<Integer>> verticalOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        q.offer(root);
        index.offer(0);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            Integer idx = index.poll();
            if (!map.containsKey(idx)) {
                map.put(idx, new ArrayList<>(Arrays.asList(cur.val)));
            } else {
                map.get(idx).add(cur.val);
            }
            if (cur.left != null) {
                q.offer(cur.left);
                index.offer(idx - 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                index.offer(idx + 1);
            }
        }
        for (int n : map.keySet()) {
            res.add(map.get(n));
        }
        return res;
    }

    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        queue.offer(root);
        index.offer(0);
        int min = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int curIdx = index.poll();
            if (!map.containsKey(curIdx)) {
                map.put(curIdx, new ArrayList<>(Arrays.asList(node.val)));
            } else {
                map.get(curIdx).add(node.val);
            }
            if (node.left != null) {
                queue.add(node.left);
                index.add(curIdx - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                index.add(curIdx + 1);
            }
            min = Math.min(min, curIdx);
        }
        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }
        return res;
    }
}
