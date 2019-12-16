package LCQuestions;

public class _062_UniquePaths {

    //2D
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        if (m == 1 || n == 1) {
            return 1;
        }

        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    //recursion + memo
    public int uniquePaths1(int m, int n) {
        int[] dp = new int[n];
        if (m == 1 || n == 1) {
            return 1;
        }

        return helper(m - 1, n - 1, new int[m][n]);
    }

    private int helper(int m, int n, int[][] memo) {
        if (m < 0 || n < 0) {
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
}
