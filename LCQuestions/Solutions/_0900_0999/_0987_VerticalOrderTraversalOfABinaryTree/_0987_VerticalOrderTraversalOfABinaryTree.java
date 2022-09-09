package LCQuestions.Solutions._0900_0999._0987_VerticalOrderTraversalOfABinaryTree;

import Utils.TreeNode;

import java.util.*;

public class _0987_VerticalOrderTraversalOfABinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, PriorityQueue<Point>> map = new HashMap<>();

        traverse(root, map, 0, 0);

        List<List<Integer>> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            min = Math.min(key, min);
            max = Math.max(key, max);
        }
        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)) {
                PriorityQueue<Point> pq = map.get(i);
                List<Integer> l = new ArrayList<>();
                while (!pq.isEmpty()) {
                    l.add(pq.poll().val);
                }
                res.add(l);
            }

        }
        return res;
    }

    private void traverse(TreeNode node, Map<Integer, PriorityQueue<Point>> map, int x, int y) {
        if (node == null) {
            return;
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        if (map.containsKey(x)) {
            pq = map.get(x);
        } else {
            map.put(x, pq);
        }
        pq.offer(new Point(y, node.val));
        traverse(node.left, map, x - 1, y - 1);
        traverse(node.right, map, x + 1, y - 1);

    }

    static class Point implements Comparable<Point> {
        int y, val;
        Point(int y, int val) {
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Point o) {
            if (y != o.y) {
                return o.y - y;
            } else {
                return val - o.val;
            }
        }
    }
}
