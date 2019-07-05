package LCQuestions;

public class _055_JumpGame {

    /**
     Given an array of non-negative integers, you are initially positioned at the first index of the array.
     Each element in the array represents your maximum jump length at that position.
     Determine if you are able to reach the last index.

     Example 1:
     Input: [2,3,1,1,4]
     Output: true
     Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

     * @param nums
     * @return
     */
//    dp, dp[i] = max(dp[i - 1], nums[i - 1]) - 1
//    dp 表示到达i时剩余的步数
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1;
            if (dp[i] < 0) return false; // 到达i没有剩余的步数
        }
        return true;
    }

    //greedy
    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int curMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > curMax) return false;
            curMax = Math.max(curMax, i + nums[i]);
        }
        return true;
    }
}
