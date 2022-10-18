package LCQuestions.Solutions._2400_2499._2441_LargestPositiveIntegerThatExistsWithItsNegative;

import java.util.HashSet;
import java.util.Set;

public class _2441_LargestPositiveIntegerThatExistsWithItsNegative {
    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = -1;
        for (int num: nums) {
            if (set.contains(-num)) {
                res = Math.abs(num) > res? Math.abs(num) : res;
            } else {
                set.add(num);
            }
        }
        return res;
    }
}
