package LCQuestions.Solutions._0400_0499._0429_N_aryTreeLevelOrderTraversal;

import Utils.ListNode;

import java.util.*;

public class _0429_N_aryTreeLevelOrderTraversal {
    // BFS
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node cur = q.poll();
                if (cur != null) {
                    l.add(cur.val);
                }
                for (Node child: cur.children) {
                    q.offer(child);
                }
            }
            res.add(l);
        }
        return res;
    }

    // BFS, no queue
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<Node> preLayer = Arrays.asList(root);
        while (!preLayer.isEmpty()) {
            List<Integer> preValues = new ArrayList<>();
            List<Node> curLayer = new ArrayList<>();
            for (Node node : preLayer) {
                preValues.add(node.val);
                curLayer.addAll(node.children);
            }
            res.add(preValues);
            preLayer = curLayer;
        }
        return res;
    }

    // DFS
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, Node node, int level) {
        if(node == null) return;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        for (Node n: node.children) {
            dfs(res, n, level + 1);
        }
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
