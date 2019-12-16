package LCQuestions;

import java.util.Arrays;

public class _685_RedundantConnectionII {

    /**
     * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
     * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
     * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.
     * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
     * Example 1:
     * Input: [[1,2], [1,3], [2,3]]
     * Output: [2,3]
     * Explanation: The given directed graph will be like this:
     * 1
     * / \
     * v   v
     * 2-->3
     * Example 2:
     * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
     * Output: [4,1]
     * Explanation: The given directed graph will be like this:
     * 5 <- 1 -> 2
     * ^    |
     * |    v
     * 4 <- 3
     *
     * @param edges
     * @return
     */
    /*
    three conditions
    1. we don't have node with indegree >= 2, which means we have cycle, we need to return the last edge in cycle
    2. we have node with indegree >= 2 and we don't have cycle, we need to return the second edge
    3. we have node with indegree >= 2 and we have cycle, we need to return the edge in cycle
    https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        int[] parent = new int[len + 1];
        int[] size = new int[len + 1];
        Arrays.fill(parent, -1);
        Arrays.fill(size, 1);
        int[] UF = new int[len + 1];
        int first = -1, second = -1, last = -1;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parent[v] != -1) {
                first = parent[v];
                second = i;
                continue;
            }
            parent[v] = i;
            int vv = find(v, UF);
            int uu = find(u, UF);
            if (vv == uu) {
                last = i;
            } else {
                if (size[uu] >= size[vv]) {
                    size[uu] += size[vv];
                    UF[vv] = uu;
                } else {
                    size[vv] += size[uu];
                    UF[uu] = vv;
                }
            }
        }
        if (last == -1) return edges[second];
        if (first == -1) return edges[last];
        return edges[first];
    }

    private int find(int x, int[] UF) {
        if (UF[x] == 0) {
            return x;
        } else {
            return UF[x] = find(UF[x], UF);
        }
    }
}
