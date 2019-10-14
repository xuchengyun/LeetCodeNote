package LCQuestions;

public class _1223_DiceRollSimulation {
    int[][][] dp;
    final int M = 1000000007;
    //https://www.cnblogs.com/lz87/p/11669196.html
    public int dieSimulator(int n, int[] rollMax) {
        dp = new int[n][6][16];
        return dfs(n, rollMax, -1, 0);
    }

    private int dfs(int n, int[] rollMax, int pre, int len) {
        if (n == 0) {
            return 1;
        }
        if (pre > 0 && dp[n][pre][len] > 0) {
            return dp[n][pre][len];
        }
        int res = 0;
        for (int i = 0; i < 6; i++) {
            if (i == pre && len == rollMax[i]) {
                continue;
            }
            res = (res + dfs(n - 1, rollMax, i, i == pre ? len + 1 : 1)) % M;
        }
        if (pre > 0) {
            dp[n][pre][len] = res;
        }
        return res;
    }

    public int dieSimulator1(int n, int[] rollMax) {
        long divisor = (long) Math.pow(10, 9) + 7;
        long[][] dp = new long[n][7];
        for (int i = 0; i < 6; i++) {
            dp[0][i] = 1;
        }
        dp[0][6] = 6;
        for (int i = 1; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < 6; j++) {
                dp[i][j] = dp[i - 1][6];
                if (i - rollMax[j] < 0) {
                    sum = (sum + dp[i][j]) % divisor;
                } else {
                    if (i - rollMax[j] - 1 >= 0) {
                        dp[i][j] = (dp[i][j] - (dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j])) % divisor + divisor;
                    } else {
                        dp[i][j] = (dp[i][j] - 1) % divisor;
                    }
                    sum = (sum + dp[i][j]) % divisor;
                }

            }
            dp[i][6] = sum;
        }
        return (int) (dp[n - 1][6]);
    }

}
