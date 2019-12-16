package LCQuestions;

public class _152_MaxinumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("Input is not valid");
        }
        int res = nums[0];
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]), nums[i]);
            dpMin[i] = Math.min(Math.min(dpMin[i - 1] * nums[i], dpMax[i - 1] * nums[i]), nums[i]);
            res = Math.max(res, dpMax[i]);
        }
        return res;
    }

    // optimized
    public int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("Input is not valid");
        }
        int res = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(min * nums[i], temp * nums[i]), nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
