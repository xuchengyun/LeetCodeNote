package LCQuestions;

import java.util.Arrays;

public class _016_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int cur = nums[i];
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int sum = cur + nums[p1] + nums[p2];
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                if (nums[p1] + nums[p2] + cur == target) {
                    return target;
                } else if (nums[p1] + nums[p2] + cur < target) {
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        return res;
    }
}
