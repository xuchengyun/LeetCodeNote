package LCQuestions.lc416PartitionEqualSubsetSum;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    /*
     * step1. get sum of nums first, divide it by 2
     * step2. to find if we have subset which sum is equal to sum of nums divided by 2
     * step3. use dp to solve this problem, dp[i][j] represent whether the specific sum j can be gotten from
     * the first i element
     * base case: dp[0][0] = true
     *
     * Transition function. For each number, if we don't pick, dp[i][j] = dp[i - 1][j]
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) return false;
        int target = sum / 2;
        int len = nums.length;
        // should understand why we have this "+1"
        boolean[][] dp = new boolean[len + 1][target + 1];

        dp[0][0] = true;

        for (int i = 0; i < len + 1; i++) {
            dp[i][0] = true;
        }

        for (int j = 0; j < target + 1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                // need to check whether j is greater than nums[i]
                // otherwise we cannot select nums[i]

                // need to understand the ">="
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][target];
    }

    public boolean canPartition1(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : nums) {
            /*
            还有一点需要注意的是，dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i-1]];
            即dp[i][j]的计算是依赖于外层上一次即i-1中的比j小的index的值，
            所以内层循环不能从小到大，如果从小到大会复写后来j需要用到的值，这样滚动数组就失效了，所以只能从大到小。*/
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[sum];
    }
}
