package LCQuestions.Solutions._2300_2399._2393_CountStrictlyIncreasingSubarrays;

public class _CountStrictlyIncreasingSubarrays {
    // sliding window
    public long countSubarrays(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        long start = 0;
        long res = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {

            } else {
                long len = i - start;
                start = i;
                res += (1 + len) * len / 2;
            }
        }
        res += (n - start) * (n - start + 1) / 2;
        return res;
    }
}
