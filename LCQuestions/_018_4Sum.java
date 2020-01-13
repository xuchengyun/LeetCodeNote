package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _018_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int p1 = j + 1;
                int p2 = nums.length - 1;
                while (p1 < p2) {
                    int sum = nums[i] + nums[j] + nums[p1] + nums[p2];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[p1], nums[p2]));
                        while (p1 < p2 && nums[p1] == nums[p1 + 1]) {
                            p1++;
                        }
                        while (p1 < p2 && nums[p2] == nums[p2 - 1]) {
                            p2--;
                        }
                        p1++;
                        p2--;
                    } else if (sum < target) {
                        p1++;
                    } else {
                        p2--;
                    }
                }
            }
        }
        return res;
    }
}
