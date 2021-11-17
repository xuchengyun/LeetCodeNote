package LCQuestions;

public class _2076_ProcessRestrictedFriendRequests {
    // Union Find
    int[] parent;
    int[] rank;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        parent = new int[n];
        rank = new int[n];

        boolean[] res = new boolean[requests.length];
        // Initialize Parent table
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // Iterate thru restrictions
        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0];
            int v = requests[i][1];
            u = find(u);
            v = find(v);
            boolean flag = true;

            for (int j = 0; j < restrictions.length; j++) {
                int u_ = restrictions[j][0];
                int v_ = restrictions[j][1];
                u_ = find(u_);
                v_ = find(v_);
                if ((u == u_ && v == v_) || (u == v_ && v == u_)) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                union(u, v);
            }
            res[i] = flag;
        }
        return res;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }

    private void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (rank[u] > rank[v]) {
            parent[v] = u;
        } else if (rank[u] < rank[v]) {
            parent[u] = v;
        } else {
            parent[v] = u;
            rank[u]++;
        }
    }
}
