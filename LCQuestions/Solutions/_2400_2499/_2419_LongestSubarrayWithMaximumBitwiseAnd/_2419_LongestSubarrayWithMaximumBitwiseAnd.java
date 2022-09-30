package LCQuestions.Solutions._2400_2499._2419_LongestSubarrayWithMaximumBitwiseAnd;

public class _2419_LongestSubarrayWithMaximumBitwiseAnd {
    public static int longestSubarray(int[] nums) {
        int max = 0;
        for (int j : nums) {
            if (j > max) {
                max = j;
            }
        }

        int res = 0, len = 0;
        for (int num : nums) {
            if (num == max) {
                res++;
                len = Math.max(res, len);
            } else {
                res = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        longestSubarray(new int[]{1,2,3,3,2,2});
    }
}
