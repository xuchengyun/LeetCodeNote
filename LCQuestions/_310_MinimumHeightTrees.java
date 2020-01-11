package LCQuestions;

import java.util.*;

/**
 * Created by xuchengyun on 12/31/19.
 */
public class _310_MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        int[] indegree = new int[n];
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        for (int[] edge : edges) {
            indegree[edge[0]]++;
            indegree[edge[1]]++;
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int size = q.size();
            res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                res.add(cur);
                for (int neighbor : adj.get(cur)) {
                    if (indegree[neighbor] == 0) {
                        continue;
                    }
                    if (indegree[neighbor] == 2) {
                        q.offer(neighbor);
                    }
                    indegree[neighbor]--;
                }
                indegree[cur]--;
            }
        }
        return res;
    }
}
