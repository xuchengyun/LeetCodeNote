package LCQuestions.Solutions._2300_2399._2392_BuildAMatrixWithConditions;

import java.util.*;

public class _2392_BuildAMatricWithConditions {
    //BFS
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer> order1 = GenerateTopologicalSort(rowConditions, k);
        List<Integer> order2 = GenerateTopologicalSort(colConditions, k);
        if (order1.size() < k || order2.size() < k) {
            return new int[0][0];
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < k; i++) {
            m.put(order2.get(i), i);
        }
        int[][] ans = new int[k][k];
        for (int i = 0; i < k; i++) {
            ans[i][m.get(order1.get(i))] = order1.get(i);
        }
        return ans;
    }

    private List<Integer> GenerateTopologicalSort(int[][] A, int k) {
        int[] deg = new int[k];
        List<Integer> order = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            graph.add(new ArrayList<>());
        }
        Queue<Integer> q = new LinkedList<>();
        for (int[] c : A) {
            graph.get(c[0] - 1).add(c[1] - 1);
            deg[c[1] - 1]++;
        }
        for (int i = 0; i < k; i++) {
            if (deg[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            order.add(x + 1);
            for (int y : graph.get(x)) {
                if (--deg[y] == 0) q.add(y);
            }
        }
        return order;
    }


    //DFS
    public int[][] buildMatrix1(int k, int[][] rowConditions, int[][] colConditions) {

        int[][] ans = new int[k][k];

        // create adjacency List for the rows and columns
        Map<Integer, Set<Integer>> adjListRow = new HashMap<>();
        Map<Integer, Set<Integer>> adjListCol = new HashMap<>();
        for(int[] row : rowConditions){
            adjListRow.putIfAbsent(row[0], new HashSet<>());
            adjListRow.get(row[0]).add(row[1]);
        }
        for(int[] col : colConditions){
            adjListCol.putIfAbsent(col[0], new HashSet<>());
            adjListCol.get(col[0]).add(col[1]);
        }

        // run DFS with cycle detection while storing the finish order of the nodes
        Map<Integer, Integer> rowIndex = new HashMap<>();
        Map<Integer, Integer> colIndex = new HashMap<>();
        int[] visitedRow = new int[k+1];
        int[] visitedCol = new int[k+1];
        for(int i = 1; i <= k; i++){
            if(!(dfs(i, adjListRow, visitedRow, rowIndex) && dfs(i, adjListCol, visitedCol, colIndex)))
                return new int[0][0];
        }

        // assign the correct position to the nodes according to the finish order
        for( int i = 1; i <= k; i++){
            int r = rowIndex.size() - rowIndex.get(i) - 1;
            int c = colIndex.size() - colIndex.get(i) - 1;
            ans[r][c] = i;
        }

        return ans;
    }

    /** DFS with cycle detection (returns false in case of a cycle)
     State of a node can be one of the following
     0 -> unvisited
     1 -> visiting
     2 -> visited
     On the DFS path visiting a node with value 1 means we are visiting a node on the path we came from -> hence it is a cycle(DFS returns false). When a visited node is encountered, we can return back to the caller node.
     */
    private boolean dfs(int node, Map<Integer, Set<Integer>> adjList, int[] visited, Map<Integer, Integer>  map){

        if(visited[node] == 2) return true;
        if(visited[node] == 1) return false;
        visited[node] = 1;

        Set<Integer> list = adjList.get(node);
        if(list != null)
            for(int ele : list)
                if(visited[ele] != 2)
                    if(!dfs(ele, adjList, visited, map)) return false;

        // mark the node visited and add to the map of finished nodes with the finish number
        visited[node] = 2;
        map.put(node, map.size());
        return true;
    }
}
