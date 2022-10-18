package LCQuestions.Solutions._2400_2499._2444_CountSubarraysWithFixedBounds;

public class _2444_CountSubarraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        /**
        Three pointer sliding window:
         Intuition: Loop through the entire array one time.
         At each index ith, find all fixed-bound subarrays with ith index as the right edge of subarray.

         How to check fixed-bound arrays
         There are two types of subarrays that end at ith index.

         1.Subarrays where all values are within bound. e.g. minK <= value<= maxK
         2.Subarrays where there exists a value equal to minK, and a value equal to maxK.
         If both 1 and 2 are satisfied, it will be a fixed bound subarray.

         Implementation
         1.Use start variable to exclude values outside bound, where start is exclusive. All subarrays in (start, end] are within bound.

         2.Use hasMax to store latest index where nums[hasMax] == maxK
           Use hasMin to store latest index where nums[hasMin] == minK

         3.Use Math.min(hasMax, hasMin) to find largest left edge where subarray [left, right] contains both minK and maxK
         */
        long res = 0, jbad = -1, jmin = -1, jmax = -1, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < minK || nums[i] > maxK) jbad = i;
            if (nums[i] == minK) jmin = i;
            if (nums[i] == maxK) jmax = i;
            res += Math.max(0L, Math.min(jmin, jmax) - jbad);
        }
        return res;
    }
}
