package LCQuestions.Solutions._2400_2499._2435_PathsInMatrixWhoseSumIsDivisibleByK;

import java.util.Arrays;

public class _2435_PathsInMatrixWhoseSumIsDivisibleByK {
    int[][][] dp;
    int mod = 1_000_000_007;

    public int numberOfPaths(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        dp = new int[rows][cols][k];
        for(int[][] a : dp) {
            for(int[] b : a) {
                Arrays.fill(b, -1);
            }
        }

        return helper(grid, 0, 0, 0, k);
    }

    int helper(int[][] grid, int r, int c, int sum, int k) {
        if(r < 0 || r == grid.length || c < 0 || c == grid[0].length) {
            return 0;
        }

        sum += grid[r][c];
        if(r == grid.length-1 && c == grid[0].length-1) {
            return sum%k==0 ? 1 : 0;
        }

        if(dp[r][c][sum%k] != -1) {
            return dp[r][c][sum%k];
        }

        dp[r][c][sum%k] = (helper(grid, r+1, c, sum, k) + helper(grid, r, c+1, sum, k)) % mod;
        return dp[r][c][sum%k];
    }

    public int numberOfPaths1(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, mod = 1000_000_007;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int s = 0; s < k; s++) {
                    int moddedSum = (s + grid[i][j]) % k;
                    if (j > 0) dp[i][j][moddedSum] += dp[i][j - 1][s];
                    if (i > 0) dp[i][j][moddedSum] += dp[i - 1][j][s];
                    dp[i][j][moddedSum] %= mod;
                }
        return dp[m - 1][n - 1][0];
    }
}
