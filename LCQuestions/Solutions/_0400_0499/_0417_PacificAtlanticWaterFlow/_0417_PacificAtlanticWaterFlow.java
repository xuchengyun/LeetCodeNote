package LCQuestions.Solutions._0400_0499._0417_PacificAtlanticWaterFlow;

import java.util.*;

public class _0417_PacificAtlanticWaterFlow {

    private static final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // BFS
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pacificQueue.offer(new int[]{i, 0});
            atlanticQueue.offer(new int[]{i, n - 1});
        }

        for (int i = 0; i < n; i++) {
            pacificQueue.offer(new int[]{0, i});
            atlanticQueue.offer(new int[]{m - 1, i});
        }

        boolean[][] pacificCells = new boolean[m][n];
        boolean[][] atlanticCells = new boolean[m][n];

        bfs(pacificQueue, matrix, pacificCells);
        bfs(atlanticQueue, matrix, atlanticCells);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificCells[i][j] && atlanticCells[i][j]) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return res;
    }

    private void bfs(Queue<int[]> q, int[][] matrix, boolean[][] visited) {
        int m = matrix.length, n = matrix[0].length;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] || matrix[nx][ny] < matrix[cur[0]][cur[1]]) {
                    continue;
                }
                q.offer(new int[]{nx, ny});
            }
        }
    }

    // DFS
    public List<List<Integer>> pacificAtlantic1(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, i, 0, Integer.MIN_VALUE);
            dfs(matrix, atlantic, i, n - 1, Integer.MIN_VALUE);
        }

        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, 0, i, Integer.MIN_VALUE);
            dfs(matrix, atlantic, m - 1, i, Integer.MIN_VALUE);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (pacific[i][j] && atlantic[i][j])
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
        return res;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int i, int j, int pre) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length
                || visited[i][j] == true || pre > matrix[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(matrix, visited, i + 1, j, matrix[i][j]);
        dfs(matrix, visited, i, j + 1, matrix[i][j]);
        dfs(matrix, visited, i - 1, j, matrix[i][j]);
        dfs(matrix, visited, i, j - 1, matrix[i][j]);
    }

}
