package LCQuestions.Solutions._0000_0099._0001_TwoSum;

import java.util.HashMap;

public class _0001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("argument is not valid");
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new RuntimeException("index not found");
    }

    public int[] twoSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("argument is not valid");
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("index not found");
    }
}
