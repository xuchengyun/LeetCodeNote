package LCQuestions;

public class _1143_LongestCommonSubsequence {

    public static void main(String[] args) {
    }

    // dp space O(m * n)
    public int longestCommonSubsequence(String text1, String text2) {
        int dp[][] = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    // dp space O(min(m, n)
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1.length() > text2.length()) {
            return longestCommonSubsequence(text2, text1);
        }
        int n = text1.length();
        int dp[] = new int[n + 1];
        for (int i = 1; i <= text2.length(); i++) {
            int pre = 0;
            for (int j = 1; j <= text1.length(); j++) {
                int tmp = dp[j];
                if (text2.charAt(i - 1) == text1.charAt(j - 1)) {
                    dp[j] = pre + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = tmp;
            }
        }
        return dp[text1.length()];
    }

    // recursive
    public int longestCommonSubsequence2(String text1, String text2) {
        return helper(text1.length(), text2.length(), text1, text2, new int[text1.length() + 1][text2.length() + 1]);
    }

    private int helper(int i, int j, String text1, String text2, int[][] memo) {
        if (i <= 0 || j <= 0) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            memo[i][j] = helper(i - 1, j - 1, text1, text2, memo) + 1;
        } else {
            memo[i][j] = Math.max(helper(i - 1, j, text1, text2, memo), helper(i, j - 1, text1, text2, memo));
        }
        return memo[i][j];
    }
}
