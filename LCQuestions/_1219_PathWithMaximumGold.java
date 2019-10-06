package LCQuestions;

public class _1219_PathWithMaximumGold {

    // typical dfs problem
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;
        for (int r = 0; r < m; r++) // O((m*n)^2) -> This is complexity of this solution
            for (int c = 0; c < n; c++)
                maxGold = Math.max(maxGold, findMaxGold(grid, r, c));

        return maxGold;
    }

    private static final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    // O(m*n), because from starting (r,c) they can go up to m*n unique paths
    private int findMaxGold(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return 0;

        int origin = grid[r][c];
        grid[r][c] = 0; // mark as visited

        int maxGold = 0;
        for (int i = 0; i < 4; i++)
            maxGold = Math.max(maxGold, findMaxGold(grid, dirs[i][0] + r, dirs[i][1] + c));

        grid[r][c] = origin; // backtrack
        return maxGold + origin;
    }
}
