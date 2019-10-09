package LCQuestions;

public class _1216_ValidPalindromeIII {


    /**
     Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
     A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
     Example 1:
     Input: s = "abcdeca", k = 2
     Output: true
     Explanation: Remove 'b' and 'e' characters.
     * @param s
     * @param k
     * @return
     */
    // convert to find the least character needed
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j =  i - 1; j > 0; j--) {
                if (i == 0) {
                    dp[j][i] = 0;
                }
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[j][i] = dp[j + 1][i - 1];
                } else {
                    dp[j][i] = Math.min(dp[j + 1][i], dp[j][i - 1]) + 1;
                }
            }
        }
        return k >= dp[1][n];
    }

    // 1d array
    public boolean isValidPalindrome1(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int pre = 0;
            for (int j =  i - 1; j > 0; j--) {
                int tmp = dp[j];
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(dp[j + 1], dp[j]) + 1;
                }
                pre = tmp;
            }
        }
        return k >= dp[1];
    }

    // recursion
    public boolean isValidPalindrome2(String s, int k) {
        int n = s.length();
        int l = helper(0, n - 1, new int[n][n], s);
        return l <= k;
    }

    private int helper(int i, int j, int[][] memo, String s) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] > 0) {
            return memo[i][j];
        }

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(i + 1, j - 1, memo, s);
        } else {
            memo[i][j] = Math.min(helper(i + 1, j, memo, s), helper(i, j - 1, memo, s));
        }
        return memo[i][j];
    }


    public static void main(String[] args) {
        _1216_ValidPalindromeIII obj = new _1216_ValidPalindromeIII();
        obj.isValidPalindrome1("abcdeca", 2);
    }
}
