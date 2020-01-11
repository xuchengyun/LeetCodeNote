package LCQuestions;

import java.util.Arrays;

public class _259_3SumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int cur = nums[i];
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int sum = cur + nums[p1] + nums[p2];
                if (sum < target) {
                    cnt += p2 - p1;
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        return cnt;
    }
}
