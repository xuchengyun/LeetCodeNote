package LCQuestions;

/**
 * Created by xuchengyun on 12/23/19.
 */
public class _087_ScrambleString {
    // brute force
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            if ((isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) ||
                (isScramble(s1.substring(0, i), s2.substring(s1.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s1.length() - i)))) {
                return true;
            }
        }
        return false;
    }

    // recursion + memo
    public boolean isScramble1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int n = s1.length();
        int[][][] memo = new int[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n + 1; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return helper(s1, s2, 0, 0, n, memo);
    }

    private boolean helper(String s1, String s2, int i, int j, int len, int[][][] memo) {
        if (len == 0) {
            return true;
        }
        if (len == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }
        if (memo[i][j][len] != -1) {
            return memo[i][j][len] == 1;
        }
        for (int k = 1; k < len; k++) {
            if ((helper(s1, s2, i, j, k, memo)
                    && helper(s1, s2, i + k, j + k, len - k, memo)) ||
                (helper(s1, s2, i, j + len - k, k, memo)
                    && helper(s1, s2, i + k, j, len - k, memo))) {
                memo[i][j][len] = 1;
                return true;
            }
        }
        memo[i][j][len] = 0;
        return false;
    }

    /*
    dp[i][j][len] = (dp[i][j][k] && dp[i + k][j + k][len - k]) || (dp[i][j + len - k][k] && dp[i + k][j][len - k])
    其中k为1到len - 1
     */
    // 3 dimension dp
    public boolean isScramble2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int len = 1; len < n + 1; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                for (int j = 0; j < n - len + 1; j++) {
                    if (len == 1) {
                        dp[i][j][len] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        for (int k = 1; k < len; k++) {
                            if ((dp[i][j][k] && dp[i + k][j + k][len - k]) || (dp[i][j + len - k][k] && dp[i + k][j][len - k])){
                                dp[i][j][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
