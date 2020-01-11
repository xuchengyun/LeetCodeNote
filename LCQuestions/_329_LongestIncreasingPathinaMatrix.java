package LCQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class _329_LongestIncreasingPathinaMatrix {

    private final static int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        int[][] data = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        _329_LongestIncreasingPathinaMatrix k = new _329_LongestIncreasingPathinaMatrix();
        k.longestIncreasingPath(data);
    }

    // DFS
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(memo, matrix, i, j));
            }
        }
        //followup
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] == res) {
                    print(matrix, i, j, memo);
                    System.out.println();
                }
            }
        }
        return res;
    }

    private void print(int[][] matrix, int i, int j, int[][] memo) {
        System.out.print(matrix[i][j]);
        for (int[] dir: dirs) {
            int nx = i + dir[0];
            int ny = j + dir[1];
            if (nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length) {
                continue;
            }
            if (memo[nx][ny] == memo[i][j] - 1) {
                print(matrix, nx, ny, memo);
            }
        }
    }

    private int dfs(int[][] memo, int[][] matrix, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];
        int m = matrix.length;
        int n = matrix[0].length;
        int curMax = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(memo, matrix, x, y);
            curMax = Math.max(curMax, len);
        }
        memo[i][j] = curMax;
        return curMax;
    }

    // BFS
    public int longestIncreasingPath1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] != 0) {
                    continue;
                }
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                memo[i][j] = 1;
                int len = 1;
                while (!q.isEmpty()) {
                    len++;
                    int size = q.size();
                    for (int k = 0; k < size; k++) {
                        int[] cur = q.poll();
                        for (int[] dir : dirs) {
                            int x = cur[0] + dir[0];
                            int y = cur[1] + dir[1];
                            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[cur[0]][cur[1]] || len <= memo[x][y]) {
                                continue;
                            }
                            memo[x][y] = len;
                            res = Math.max(res, len);
                            q.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return res;
    }

    // BFS Topological sort
    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] indegree = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                        continue;
                    }
                    if (matrix[nx][ny] < matrix[i][j]) {
                        indegree[i][j]++;
                    }
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (indegree[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        int res = 0;
        while (!q.isEmpty()) {
            res++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int nx = cur[0] + dir[0];
                    int ny = cur[1] + dir[1];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                        continue;
                    }
                    if (matrix[nx][ny] > matrix[cur[0]][cur[1]]) {
                        indegree[nx][ny]--;
                        // 一定要注意indegree == 0 这个条件要在matrix[nx][ny] > matrix[cur[0]][cur[1]]里面判断
                        if (indegree[nx][ny] == 0) {
                            q.offer(new int[]{nx, ny});
                        }
                    }

                }
            }
        }
        return res;
    }
}
