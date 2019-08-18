package LCQuestions;

public class _045_JumpGameII {

    /**
     Given an array of non-negative integers, you are initially positioned at the first index of the array.
     Each element in the array represents your maximum jump length at that position.
     Your goal is to reach the last index in the minimum number of jumps.

     Example:

     Input: [2,3,1,1,4]
     Output: 2
     Explanation: The minimum number of jumps to reach the last index is 2.
     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * @param nums
     * @return
     */
    //greedy
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int curMax = 0;
        int res = 0;
        int maxNext = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxNext = Math.max(maxNext, i + nums[i]);
            if (i == curMax) {
                curMax = maxNext;
                res++;
            }
        }
        return res;
    }

    //bfs
    public int jump1(int[] nums) {
        if (nums.length <= 1) return 0;
        int curMax = 0; // to mark the last element in a level
        int level = 0, i = 0;
        while (i <= curMax) {
            int furthest = curMax; // to mark the last element in the next level
            for (; i <= curMax; i++) {
                furthest = Math.max(furthest, nums[i] + i);
                if (furthest >= nums.length - 1) return level + 1;
            }
            level++;
            curMax = furthest;
        }
        return -1; // if i < curMax, i can't move forward anymore (the last element in the array can't be reached)
    }
}
