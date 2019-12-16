package LCQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class _1162_AsFarFromLandAsPossible {

    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int res = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 0) {
                        q.offer(new int[]{x, y});
                    }
                }
            }
            res++;
        }
        return res == 0 ? -1 : res;
    }
}
