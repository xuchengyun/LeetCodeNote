package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = nums[i];
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                if (nums[p1] + nums[p2] == -target) {
                    res.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                    while (p1 < p2 && nums[p1] == nums[p1 + 1]) {
                        p1++;
                    }
                    while (p1 < p2 && nums[p2] == nums[p2 - 1]) {
                        p2--;
                    }
                    p1++;
                    p2--;
                } else if (nums[p1] + nums[p2] < -target) {
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test");
    }

}
