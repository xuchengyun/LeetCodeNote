package LCQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1218_LongestArithmeticSubsequenceofGivenDifferencc {
    // dp

    /**
     time complexity is high for this method, O(n ^ 2), space is O(n)
     */
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] - arr[j] == difference) {
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }

            }
        }
        int res = 0;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }


    // use hashmap to speed it up
    public int longestSubsequence1(int[] arr, int difference) {
        // map to store the current in array and the maximum subsequence ending in that element
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],  map.getOrDefault(arr[i] - difference, 0) + 1);
            res = Math.max(map.get(arr[i]), res);
        }
        return res;
    }
}
