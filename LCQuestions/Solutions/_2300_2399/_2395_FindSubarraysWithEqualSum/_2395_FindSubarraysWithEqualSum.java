package LCQuestions.Solutions._2300_2399._2395_FindSubarraysWithEqualSum;

import java.util.HashSet;
import java.util.Set;

public class _2395_FindSubarraysWithEqualSum {
    // Hashset
    public boolean findSubarrays(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        int n = nums.length;
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int cur = nums[i] + nums[i + 1];
            if (s.contains(cur)) {
                return true;
            }
            s.add(cur);
        }
        return false;
    }
}
