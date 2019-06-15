package LCQuestions;

public class _042_TrappingRainWater {

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
    1. create an array called dp, traver the array height and store the current max in the dp(find the left max)
    2. reverse traverse the dp array and find the left max
    3. find the minumim of left and right max and compare to height[i]
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
    solution3: two pointers
    */
    public int trap3(int[] height) {
        int left = 0, right = height.length - 1, res = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < leftMax) {
                    res += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;
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
