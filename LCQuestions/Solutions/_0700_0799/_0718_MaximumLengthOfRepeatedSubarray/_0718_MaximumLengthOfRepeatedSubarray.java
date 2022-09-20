package LCQuestions.Solutions._0700_0799._0718_MaximumLengthOfRepeatedSubarray;

public class _0718_MaximumLengthOfRepeatedSubarray {
    // dp
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    res = Math.max(res, dp[i + 1][j + 1]);
                }
            }
        }

        return res;
    }

    // dp - compress space to 1d
    public int findLength1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m < n) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = n; j >= 1; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    res = Math.max(dp[j], res);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return res;
    }

    // Sliding window space to O(1)
    /**
     * The initial position and the directions in which we slide. One step means shifting the top array by one position (index) to the right, or the the bottom array by one position (index) to the left:
              [1,2,3,2,1]   -->
               <--    [3,2,1,4,7]

              [1,2,3,2,1]
                    [3,2,1,4,7]

              [1,2,3,2,1]      -->
         <--      [3,2,1,4,7]
      and so on*/
    public int findLength2(int[] nums1, int[] nums2) {
        int result = 0;
        for (int i = 0; i < nums1.length + nums2.length - 1; ++i) {
            // The current overlapping window is A[aStart, Math.min(A.length, B.length)] and B[bStart, Math.min(A.length, B.length)].
            int aStart = Math.max(0, nums1.length - 1 - i);
            int bStart = Math.max(0, i - (nums1.length - 1));
            int numConsecutiveMatches = 0;
            for (int aIdx = aStart, bIdx = bStart; aIdx < nums1.length && bIdx < nums2.length; ++aIdx, ++bIdx) {
                // Maintain number of equal consecutive elements in the current window (overlap) and the max number ever computed.
                numConsecutiveMatches = nums1[aIdx] == nums2[bIdx] ? numConsecutiveMatches + 1 : 0;
                result = Math.max(result, numConsecutiveMatches);
            }
        }
        return result;
    }
}
