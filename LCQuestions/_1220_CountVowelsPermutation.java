package LCQuestions;

public class _1220_CountVowelsPermutation {
    static int MOD = (int) (Math.pow(10, 9) + 7);

    // dp
    public int countVowelPermutation(int n) {
        long[][] dp = new long[5][n];
        for (int i = 0; i < 5; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = (dp[1][i - 1] + dp[2][i - 1] + dp[4][i - 1]) % MOD; // 'a' can follow {'e', 'i', 'u'}
            dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD; // 'e' can follow {'a', 'i'}
            dp[2][i] = (dp[1][i - 1] + dp[3][i - 1]) % MOD; // 'i' can follow {'e', 'o'}
            dp[3][i] = (dp[2][i - 1]) % MOD; // 'o' can follow {'i'}
            dp[4][i] = (dp[2][i - 1] + dp[3][i - 1]) % MOD; // 'u' can follow {'i', 'o'}
        }
        long sum = 0;
        for (long i : dp[n - 1]) {
            sum = (sum + i) % MOD;
        }
        return (int) sum;
    }
}
