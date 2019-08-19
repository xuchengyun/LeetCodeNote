package LCQuestions;

public class _053_MaxinumSubarray {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int curMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curMax > 0) {
                curMax += nums[i];
            } else {
                curMax = nums[i];
            }
            res = Math.max(res, curMax);
        }
        return res;
    }

    // divide conquer
    public int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);
        int rmax = helper(nums, mid + 1, right);
        int mmax = nums[mid], t = mmax;
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        return Math.max(mmax, Math.max(lmax, rmax));
    }
}
