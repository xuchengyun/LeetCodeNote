package LCQuestions;

public class _063_UniquePathsII {
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return helper(m, n, new int[m][n]);
    }

    private int helper(int m, int n, int[][] memo) {
        if (m < 0 || n < 0 || memo[m][n] == 1) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }
        if (memo[m][n] != 0) {
            return memo[m][n];
        }
        memo[m][n] = helper(m - 1, n, memo) + helper(m, n - 1, memo);
        return memo[m][n];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        if (dp[0][0] == 0 ) return 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0) {
                        if (j > 0) {
                            dp[i][j] = dp[i][j - 1];
                        }
                    } else if (j == 0) {
                        if (i > 0) {
                            dp[i][j] = dp[i - 1][j];
                        }
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }

            }
        }
        return dp[m - 1][n - 1];
    }
}
