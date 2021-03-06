package LCQuestions;

public class _070_ClimbingStairs {
    //dp
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // dp save space
    public int climbStairs3(int n) {
        int[] dp = new int[n + 1];
        int first = 1;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    // recursion
    public int climbStairs1(int n) {
        if (n < 2) return 1;
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // recursion with memo
    public int climbStairs2(int n) {
        int[] memo = new int[n + 1];
        return climbStairs2(n, memo);
    }

    private int climbStairs2(int n, int[] memo) {
        if (n < 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = climbStairs2(n - 1, memo) + climbStairs2(n - 2, memo);
        return memo[n];
    }
}
