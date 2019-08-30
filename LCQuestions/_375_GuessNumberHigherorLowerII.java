package LCQuestions;

public class _375_GuessNumberHigherorLowerII {

    int[][] dp;
    public int getMoneyAmount(int n) {
        dp = new int[n + 1][n + 1];
        return helper(1, n);
    }

    private int helper(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            res = Math.min(res, Math.max(helper(i, x - 1), helper(x + 1, j)) + x);
        }
        dp[i][j] = res;
        return res;
    }
}
