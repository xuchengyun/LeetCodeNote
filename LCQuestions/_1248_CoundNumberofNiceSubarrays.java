package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _1248_CoundNumberofNiceSubarrays {
    // prefix. convert even to 0 odd to 1
    public int numberOfSubarrays(int[] nums, int k) {
        int[] preSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                preSum[i] = nums[0] % 2 == 0 ? 0 : 1;
            }
            if (nums[i] % 2 == 0) {
                preSum[i] = preSum[i - 1];
            } else {
                preSum[i] = preSum[i - 1] + 1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < preSum.length; i++) {
            if (map.containsKey(preSum[i] - k)) {
                res += map.get(preSum[i] - k);
            } else {
                map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
            }
        }
        return res;
    }

    public int numberOfSubarrays1(int[] nums, int k) {
        int cur = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i] % 2 == 1 ? 1 : 0;
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            ans += map.getOrDefault(cur - k, 0);
        }
        return ans;
    }

    public int numberOfSubarrays2(int[] A, int k) {
        return atMost(A, k) - atMost(A, k - 1);
    }

    public int atMost(int[] A, int k) {
        int res = 0, i = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            k -= A[j] % 2;
            while (k < 0)
                k += A[i++] % 2;
            res += j - i + 1;
        }
        return res;
    }
}
