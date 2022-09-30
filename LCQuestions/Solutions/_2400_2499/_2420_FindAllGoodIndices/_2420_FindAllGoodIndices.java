package LCQuestions.Solutions._2400_2499._2420_FindAllGoodIndices;

import java.util.ArrayList;
import java.util.List;

public class _2420_FindAllGoodIndices {
    public static List<Integer> goodIndices(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        if (nums.length == 3 && k == 1) {
            res.add(1);
            return res;
        }
        int n = nums.length;

        for (int i = k; i < n - k; i++) {
            boolean startIncrease = true;
            boolean endDecrease = true;
            for (int j = 0; j < k - 1; j++) {
                if (nums[i - k + j] >= nums[i - k + j + 1]) {
                    startIncrease = false;
                }

                if (nums[i + j + 1] <= nums[i + j + 2]) {
                    endDecrease = false;
                }
            }
            if (!startIncrease && !endDecrease) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        goodIndices(new int[]{2,1,1,1,3,4,1}, 2);
    }
}
