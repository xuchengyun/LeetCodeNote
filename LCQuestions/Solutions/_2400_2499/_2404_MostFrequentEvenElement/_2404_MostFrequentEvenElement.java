package LCQuestions.Solutions._2400_2499._2404_MostFrequentEvenElement;

import java.util.HashMap;
import java.util.Map;

public class _2404_MostFrequentEvenElement {
    public int mostFrequentEven(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int max = -1;
        int res = -1;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                int curCnt = map.get(num);
                if (curCnt > max) {
                    res = num;
                    max = curCnt;
                } else if (curCnt == max) {
                    if (res > num) {
                        res = num;
                    }
                }
            }
        }
        return res;
    }
}
