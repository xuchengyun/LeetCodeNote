package LCQuestions.Solutions._1700_1799._1770_MaximumScoreFromPerformingMultiplicationOperations;

import java.util.Arrays;

public class _1770_MaximumScoreFromPerformingMultiplicationOperations {

    // Brute force
    public int maximumScore(int[] nums, int[] multipliers) {
        return dfs(nums, multipliers, 0, nums.length - 1, 0);
    }

    private int dfs(int[] nums, int[] multipliers, int start, int end, int index) {
        if (index == multipliers.length) {
            return 0;
        }
        int left = nums[start] * multipliers[index] + dfs(nums, multipliers, start + 1, end, index + 1);
        int right = nums[end] * multipliers[index] + dfs(nums, multipliers, start, end - 1, index + 1);
        return Math.max(left, right);
    }

    // 3D DP recursion
    int[][][] dp;
    boolean[][][] b;
    public int maximumScore1(int[] nums, int[] multipliers) {
        dp = new int[nums.length][nums.length][multipliers.length];
        b = new boolean[nums.length][nums.length][multipliers.length];
        return dfs1(nums, multipliers, 0, nums.length - 1, 0);
    }

    private int dfs1(int[] nums, int[] multipliers, int start, int end, int index) {
        if (index == multipliers.length) {
            return 0;
        }
        if (b[start][end][index]) {
            return dp[start][end][index];
        }
        int left = nums[start] * multipliers[index] + dfs1(nums, multipliers, start + 1, end, index + 1);
        int right = nums[end] * multipliers[index] + dfs1(nums, multipliers, start, end - 1, index + 1);
        int res = Math.max(left, right);
        dp[start][end][index] = res;
        b[start][end][index] = true;
        return res;
    }


    // 2D DP recursion solution
    Integer[][] memo;
    public int maximumScore2(int[] nums, int[] multipliers) {
        memo = new Integer[nums.length][multipliers.length];
        return dfs2(nums, multipliers, 0, 0);
    }

    private int dfs2(int[] nums, int[] multipliers, int start,  int index) {
        if (index == multipliers.length) {
            return 0;
        }
        int end = nums.length - 1 - (index - start);
        if (memo[start][index] != null) {
            return memo[start][index];
        }
        int left = nums[start] * multipliers[index] + dfs2(nums, multipliers, start + 1, index + 1);
        int right = nums[end] * multipliers[index] + dfs2(nums, multipliers, start, index + 1);
        int res = Math.max(left, right);
        memo[start][index] = res;
        return res;
    }

}
