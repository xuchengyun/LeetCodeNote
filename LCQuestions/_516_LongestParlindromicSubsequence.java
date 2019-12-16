package LCQuestions;

public class _516_LongestParlindromicSubsequence {
    public static void main(String[] args) {
        _516_LongestParlindromicSubsequence obj = new _516_LongestParlindromicSubsequence();
        obj.longestPalindromeSubsequence1("bbbab");
    }

    //https://leetcode.com/problems/longest-palindromic-subsequence/discuss/222605/DP-Problem-Classifications-Helpful-Notes
    /*
    if (s.charAt(i) == s.charAt(j))
    dp[i][j] = dp[i + 1][j - 1] + 2
    else
    dp[i][j]= Math.max(dp[i + 1][j], dp[i][j - 1])
    */
    public int longestPalindromeSubsequence(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j + 1 == i) {
                        dp[j][i] = 2;
                    } else {
                        dp[j][i] = dp[j + 1][i - 1] + 2;
                    }
                } else {
                    dp[j][i] = Math.max(dp[j + 1][i], dp[j][i - 1]);
                }
            }
        }
        return dp[len - 1][len - 1];
    }

    // compress to 1d array
    public int longestPalindromeSubsequence1(String s) {
        int len = s.length();
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            int pre = 0;
            for (int j = i - 1; j >= 0; j--) {
                int tmp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j + 1]);
                }
                pre = tmp;
            }
        }
        return dp[0];
    }

    // recursive
    public int longestPalindromeSubsequence2(String s) {
        return helper(s, 0, s.length() - 1, new int[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, int[][] memo) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}
