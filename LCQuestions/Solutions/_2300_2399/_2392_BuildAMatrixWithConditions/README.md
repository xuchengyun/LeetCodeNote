# [2392. Build a Matrix With Conditions](https://leetcode.com/problems/build-a-matrix-with-conditions)

## Description

<p>You are given a <strong>positive</strong> integer <code>k</code>. You are also given:</p>

<ul>
	<li>a 2D integer array <code>rowConditions</code> of size <code>n</code> where <code>rowConditions[i] = [above<sub>i</sub>, below<sub>i</sub>]</code>, and</li>
	<li>a 2D integer array <code>colConditions</code> of size <code>m</code> where <code>colConditions[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>.</li>
</ul>

<p>The two arrays contain integers from <code>1</code> to <code>k</code>.</p>

<p>You have to build a <code>k x k</code> matrix that contains each of the numbers from <code>1</code> to <code>k</code> <strong>exactly once</strong>. The remaining cells should have the value <code>0</code>.</p>

<p>The matrix should also satisfy the following conditions:</p>

<ul>
	<li>The number <code>above<sub>i</sub></code> should appear in a <strong>row</strong> that is strictly <strong>above</strong> the row at which the number <code>below<sub>i</sub></code> appears for all <code>i</code> from <code>0</code> to <code>n - 1</code>.</li>
	<li>The number <code>left<sub>i</sub></code> should appear in a <strong>column</strong> that is strictly <strong>left</strong> of the column at which the number <code>right<sub>i</sub></code> appears for all <code>i</code> from <code>0</code> to <code>m - 1</code>.</li>
</ul>

<p>Return <em><strong>any</strong> matrix that satisfies the conditions</em>. If no answer exists, return an empty matrix.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/07/06/gridosdrawio.png" style="width: 211px; height: 211px;" />
<pre>
<strong>Input:</strong> k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
<strong>Output:</strong> [[3,0,0],[0,0,1],[0,2,0]]
<strong>Explanation:</strong> The diagram above shows a valid example of a matrix that satisfies all the conditions.
The row conditions are the following:
- Number 1 is in row <u>1</u>, and number 2 is in row <u>2</u>, so 1 is above 2 in the matrix.
- Number 3 is in row <u>0</u>, and number 2 is in row <u>2</u>, so 3 is above 2 in the matrix.
The column conditions are the following:
- Number 2 is in column <u>1</u>, and number 1 is in column <u>2</u>, so 2 is left of 1 in the matrix.
- Number 3 is in column <u>0</u>, and number 2 is in column <u>1</u>, so 3 is left of 2 in the matrix.
Note that there may be multiple correct answers.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
<strong>Output:</strong> []
<strong>Explanation:</strong> From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be above 1 to be satisfied.
No matrix can satisfy all the conditions, so we return the empty matrix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 400</code></li>
	<li><code>1 &lt;= rowConditions.length, colConditions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>rowConditions[i].length == colConditions[i].length == 2</code></li>
	<li><code>1 &lt;= above<sub>i</sub>, below<sub>i</sub>, left<sub>i</sub>, right<sub>i</sub> &lt;= k</code></li>
	<li><code>above<sub>i</sub> != below<sub>i</sub></code></li>
	<li><code>left<sub>i</sub> != right<sub>i</sub></code></li>
</ul>


## Solutions
<!-- tabs:start -->
拓扑排序 + BFS

### **Java**

```java
class Solution {
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
}

```
DFS
```java
class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

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
```
### **...**

```

```

<!-- tabs:end -->