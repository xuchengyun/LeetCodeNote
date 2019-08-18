package LCQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class _329_LongestIncreasingPathinaMatrix {

    int[][] dirs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
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
        return res;
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

    public static void main(String[] args) {
        int[][] data = new int[][] {{3,4,5}, {3,2,6}, {2,2,1}};
        _329_LongestIncreasingPathinaMatrix k = new _329_LongestIncreasingPathinaMatrix();
        k.longestIncreasingPath1(data);
    }
}
