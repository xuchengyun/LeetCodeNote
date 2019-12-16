package LCQuestions;

public class _115_DistinctSubsequence {

    /**
     * Given a string S and a string T,
     * count the number of distinct subsequences of S which equals T.
     * <p>
     * A subsequence of a string is a new string which
     * is formed from the original string by deleting some
     * (can be none) of the characters without disturbing
     * the relative positions of the remaining characters.
     * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        for (int j = 1; j < m + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
