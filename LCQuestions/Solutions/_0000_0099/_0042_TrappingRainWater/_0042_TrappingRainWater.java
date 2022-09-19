package LCQuestions.Solutions._0000_0099._0042_TrappingRainWater;

import java.util.ArrayDeque;
import java.util.Deque;

public class _0042_TrappingRainWater {

    /**
    solution1: brute force
    1. traverse the array
    2. for every element in the array, find the maximum value from its left and right
    3. res += Math.min(leftMax, rightMax) - height[i]
    */
    public int trap(int[] height) {
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int MaxLeft = 0, MaxRight = 0;
            for (int l = i; l >= 0; l--) {
                MaxLeft = Math.max(MaxLeft, height[l]);
            }

            for (int r = i; r < height.length; r++) {
                MaxRight = Math.max(MaxRight, height[r]);
            }
            res += Math.min(MaxLeft, MaxRight) - height[i];
        }
        return res;
    }

    /**
    solution2: dynamic programming
    the idea is same as brute force, but only the array two times
     1. create an array called dp, traverse the array height and store the current max in the dp(find the left max)
    2. reverse traverse the dp array and find the left max
    3. find the minimum of left and right max and compare to height[i]
    */
    public int trap1(int[] height) {
        int res = 0, max = 0;
        int[] dp = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            max = Math.max(height[i], max);
            dp[i] = max;
        }
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(height[i], max);
            res += Math.min(dp[i], max) - height[i];
        }
        return res;
    }
    /**
     Solution 3: Using stack
     When is water trapped?
     Water is trapped when an increasing height appears.
     Stack store decreasing heights that can be possible leftUpper .
     Whenever we meet a rightUpper, we should accumulate water trapped.

     How much water is trapped because of the rightUpper?
     water trapped = width * height
     The width of water trapped depends on distance from leftUpper to rightUpper, so we save index rather than height
     The height of water trapped depends on min(leftUpper, rightUpper) - lowerHeight.
     */
    public int trap3(int[] height) {
        if (height == null || height.length < 2) return 0;
        Deque<Integer> dq = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!dq.isEmpty() && height[dq.peek()] < height[i]) {
                int topIndex = dq.pop();
                if (dq.isEmpty()) {
                    break;
                }
                int left = dq.peek();
                int dist = Math.min(height[left], height[i]) - height[topIndex];
                res += dist * (i - left - 1);
            }
            dq.push(i);
        }
        return res;
    }

    /**
    solution4: two pointers
     - Initialize left point to 0 and right pointer to size - 1
     - while (left < right), do:
        - If height[left] < height[right]
        - else add left_max - height[left] to ans
        - add 1 to left
     - else
         - If height[left] >= height[right]
         - else add right_max - height[left] to ans
         - subtract 1 from right
    */
    public int trap4(int[] height) {
        int left = 0, right = height.length - 1, res = 0;
        int leftMax = 0, rightMax = 0;
        // Water could, potentially, fill everything from left to right, if there is nothing in between.
        while (left < right) {
            // a taller bar exists on left pointer's right side(height[leftMax] must less than height[right])
            if (height[left] < height[right]) {
                if (height[left] < leftMax) {
                    res += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;
            // a taller bar exists on right pointer's left side
            } else {
                if (height[right] < rightMax) {
                    res += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return res;
    }
}
