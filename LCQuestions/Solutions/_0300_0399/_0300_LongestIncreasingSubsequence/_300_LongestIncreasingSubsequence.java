package LCQuestions.Solutions._0300_0399._0300_LongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _300_LongestIncreasingSubsequence {

    /**
     * method1
     * brute force recursion
     */
    public int lengthOfLIS1(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }

    /**
     * method3
     * patient sort + binary search(插入排序 二分法)
     */
    public static int lengthOfLIS4(int[] nums) {
        // use a list to store the tail for each pile
        List<Integer> pileTail = new ArrayList<>();
        for (int num : nums) {
            // use binary search to find the smallest next tail which is larger that the number
            int left = 0, right = pileTail.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (pileTail.get(mid) >= num) {
                    right = mid;
                    if (left == right) {
                        break;
                    }
                } else {
                    left = mid + 1;
                }
            }
            // the left is not contained in current pile
            // we create a new pile
            if (left == pileTail.size()) {
                pileTail.add(num);
            } else {
                // we update the tail of current pile
                pileTail.set(left, num);
            }
        }
        // the size of current pile is the answer
        return pileTail.size();
    }

    /**
     * method2
     * recursion with memorization
     */
    public int lengthOfLIS2(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthOfLIS(nums, -1, 0, memo);
    }

    private int lengthOfLIS(int[] nums, int preIndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[preIndex + 1][curpos] >= 0) {
            return memo[preIndex + 1][curpos];
        }
        int taken = 0;
        int notaken = 0;
        if (preIndex < 0 || nums[curpos] > nums[preIndex]) {
            taken = 1 + lengthOfLIS(nums, curpos, curpos + 1, memo);
            notaken = lengthOfLIS(nums, preIndex, curpos + 1, memo);
        } else {
            notaken = lengthOfLIS(nums, preIndex, curpos + 1, memo);
        }
        memo[preIndex + 1][curpos] = Math.max(taken, notaken);
        return memo[preIndex + 1][curpos];
    }

    /**
     * method3
     * dp
     */
    public int lengthOfLIS3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 0;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        lengthOfLIS4(nums);
    }

    private int lengthOfLIS(int[] nums, int pre, int curpos) {
        if (nums.length == curpos) return 0;
        int taken = 0;
        int notTaken = 0;
        if (nums[curpos] > pre) {
            taken = 1 + lengthOfLIS(nums, nums[curpos], curpos + 1);
            notTaken = lengthOfLIS(nums, pre, curpos + 1);
        } else {
            // because the value of preMax is already put into answer, So we cannot take current element
            notTaken = lengthOfLIS(nums, pre, curpos + 1);
        }
        return Math.max(taken, notTaken);
    }
}
