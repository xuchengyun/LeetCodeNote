package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _1278_PalindromePartitioningIII {
    Map<String, Integer> map;
    int[][] cache;

    public static void main(String[] args) {
        _1278_PalindromePartitioningIII obj = new _1278_PalindromePartitioningIII();
        obj.palindromePartition("aabbc", 3);
    }

    // interesting dp
    public int palindromePartition(String s, int k) {
        map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                calculate(s.substring(i, j + 1));
            }
        }
        // dp[pos][cnt] 表示在前pos的位置 切cnt份的最小的变化数量
        int[][] dp = new int[n][k + 1];
        for (int j = 1; j <= k; j++) {
            for (int i = j - 1; i < n; i++) {
                if (j == 1) {
                    dp[i][j] = map.get(s.substring(0, i + 1));
                    continue;
                }
                int cur = Integer.MAX_VALUE;
                for (int q = j - 2; q < i; q++) {
                    cur = Math.min(cur, dp[q][j - 1] + map.get(s.substring(q + 1, i + 1)));
                }
                dp[i][j] = cur;
            }
        }
        return dp[n - 1][k];
    }

    private void calculate(String s) {
        int i = 0, j = s.length() - 1;
        int res = 0;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                res++;
            }
        }
        map.put(s, res);
    }

    public int palindromePartition1(String s, int k) {
        cache = new int[s.length()][k];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < k; j++) {
                cache[i][j] = -1;
            }
        }
        return getChangeCount(s, 0, k);
    }

    int getChangeCount(String s, int start, int k) {
        if (cache[start][k - 1] != -1) {
            return cache[start][k - 1];
        }
        if (k == 1) {
            return cache[start][0] = countSubStringChange(s, start, s.length() - 1);
        }
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= s.length() - k; i++) {
            min = Math.min(min, countSubStringChange(s, start, i) + getChangeCount(s, i + 1, k - 1));
        }
        return cache[start][k - 1] = min;
    }

    private int countSubStringChange(String s, int start, int end) {
        int count = 0;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--))
                count++;
        }
        return count;
    }
}
