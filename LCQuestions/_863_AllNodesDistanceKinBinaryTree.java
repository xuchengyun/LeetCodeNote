package LCQuestions;

import Utils.TreeNode;

import java.util.*;

public class _863_AllNodesDistanceKinBinaryTree {

    List<Integer> res;
    TreeNode target;
    int K;
    //bfs
    Map<TreeNode, TreeNode> parent;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        res = new ArrayList<>();
        this.target = target;
        this.K = K;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return -1;
        }
        if (node.val == target.val) {
            findChildren(node, K);
            return 0;
        } else {
            int left = dfs(node.left);
            int right = dfs(node.right);
            if (left == -1 && right == -1) {
                return -1;
            }
            if (left != -1) {
                int l = left + 1;
                if (l == K) {
                    res.add(node.val);
                } else if (l < K) {
                    findChildren(node.right, K - l - 1);
                }
                return l;
            }
            if (right != -1) {
                int r = right + 1;
                if (r == K) {
                    res.add(node.val);
                } else if (r < K) {
                    findChildren(node.left, K - r - 1);
                }
                return r;
            }
        }
        return -1;
    }

    private void findChildren(TreeNode node, int dis) {
        if (node == null) {
            return;
        }
        if (dis == 0) {
            res.add(node.val);
        } else {
            findChildren(node.left, dis - 1);
            findChildren(node.right, dis - 1);
        }
    }

    public List<Integer> distanceK1(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        dfs(root, null);
        Queue<TreeNode> queue = new LinkedList();
        queue.add(target);
        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        List<Integer> res = new ArrayList<>();
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (dist == K) {
                for (int i = 0; i < size; i++) {
                    res.add(queue.poll().val);
                }
                return res;
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null && !seen.contains(node.left)) {
                        seen.add(node.left);
                        queue.offer(node.left);
                    }
                    if (node.right != null && !seen.contains(node.right)) {
                        seen.add(node.right);
                        queue.offer(node.right);
                    }
                    TreeNode p = parent.get(node);
                    if (p != null && !seen.contains(p)) {
                        seen.add(p);
                        queue.offer(p);
                    }
                }
            }
            dist++;
        }
        return res;
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }


}
