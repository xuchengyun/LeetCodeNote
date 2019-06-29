package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _377_CombinationSumIV {
    /**
     nums = [1, 2, 3]
     target = 4

     The possible combination ways are:
     (1, 1, 1, 1)
     (1, 1, 2)
     (1, 2, 1)
     (1, 3)
     (2, 1, 1)
     (2, 2)
     (3, 1)

     Note that different sequences are counted as different combinations.

     Therefore the output is 7.
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        /**
         omb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
         */
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    if (i - num >= 0) {
                        res[i] += res[i - num];
                    }
                }
            }
        }
        return res[target];
    }

    //backTracking
    public int combinationSum42(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(nums, target, map);
    }

    private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res);
        return res;
    }
}
