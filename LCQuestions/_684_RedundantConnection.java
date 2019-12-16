package LCQuestions;

import java.util.*;

public class _684_RedundantConnection {

    /**
     * In this problem, a tree is an undirected graph that is connected and has no cycles.
     * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
     * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
     * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
     *
     * @param edges
     * @return
     */
    // union find
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1], parent)) {
                return new int[]{edge[0], edge[1]};
            }
        }
        throw new RuntimeException("not found");
    }

    private boolean union(int x, int y, int[] parent) {
        int xx = find(x, parent);
        int yy = find(y, parent);
        if (xx == yy) {
            return false;
        }
        parent[xx] = yy;
        return true;
    }

    // path compression
    private int find(int x, int[] parent) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }


    // dfs
    // we can add edge one by one, for one edge (u, v), if u can reach v, which means that if we connect u and v will form a cycle
    // dfs we have a strategy to avoid duplicate traverse, so we don't need visited set.
    // use a pointer prev to denote previous node, if neibor equals prev, we just skip it
    // we can add edge one by one, for one edge (u, v), if u can reach v, which means that if we connect u and v will form a cycle
    public int[] findRedundantConnection1(int[][] edges) {
        List<Set<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < edges.length + 1; i++) {
            adj.add(new HashSet());
        }
        for (int[] edge : edges) {
            if (hasCycleDFS(-1, edge[0], edge[1], adj)) {
                return edge;
            }
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return new int[]{};
    }

    private boolean hasCycleDFS(int pre, int u, int v, List<Set<Integer>> adj) {
        if (u == v) {
            return true;
        }
        for (int neighbor : adj.get(u)) {
            if (neighbor == pre) {
                continue;
            }
            if (hasCycleDFS(u, neighbor, v, adj)) {
                return true;
            }
        }
        return false;
    }

    public int[] findRedundantConnection2(int[][] edges) {
        List<Set<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < edges.length + 1; i++) {
            adj.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            Set<Integer> visited = new HashSet<>();
            if (hasCycleBFS(edge[0], edge[1], visited, adj)) {
                return edge;
            }
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return new int[]{};
    }

    private boolean hasCycleBFS(int u, int v, Set<Integer> visited, List<Set<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(u);
        visited.add(u);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (adj.get(cur).contains(v)) {
                return true;
            }
            for (int neighbor : adj.get(cur)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                q.offer(neighbor);
                visited.add(neighbor);
            }
        }
        return false;
    }
}
