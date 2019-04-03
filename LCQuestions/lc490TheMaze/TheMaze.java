package LCQuestions.lc490TheMaze;

import java.util.LinkedList;
import java.util.Queue;

public class TheMaze {
    //bfs
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // corner case check
        if (maze == null || maze.length == 0 || maze[0].length == 0) return true;
        int m = maze.length;
        int n = maze[0].length;
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[m][n];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1]) return true;
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dirs[i][0];
                int y = cur[1] + dirs[i][1];
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x += dirs[i][0];
                    y += dirs[i][1];
                }
                if (!visited[x - dirs[i][0]][y - dirs[i][1]]) {
                    visited[x - dirs[i][0]][y - dirs[i][1]] = true;
                    queue.offer(new int[] {x - dirs[i][0], y - dirs[i][1]});
                }
            }
        }
        return false;
    }

    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    //dfs
    public boolean hasPath1(int[][] maze, int[] start, int[] destination) {
        // corner case first
        if (maze == null || maze.length == 0 || maze[0].length == 0) return true;
        int m = maze.length;
        int n = maze[0].length;
        return helper(maze, start, destination);
    }

    private boolean helper(int[][] maze, int[] cur, int[] destination) {
        if (cur[0] == destination[0] && cur[1] == destination[1]) return true;

        int m = maze.length;
        int n = maze[0].length;

        maze[cur[0]][cur[1]] = -1;
        for (int[] dir : dirs) {
            int x = cur[0];
            int y = cur[1];
            while (x >= 0 && x < m && y >= 0 && y < n) {
                x += dir[0];
                y += dir[1];
            }

            x -= dir[0];
            y -= dir[1];
            if (maze[x][y] != -1) {
                if (helper(maze, new int[]{x, y}, destination)) return true; }
        }
        return false;
    }
}
