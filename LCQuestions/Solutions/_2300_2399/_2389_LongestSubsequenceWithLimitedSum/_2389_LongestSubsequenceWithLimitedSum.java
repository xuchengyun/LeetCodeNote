package LCQuestions.Solutions._2300_2399._2389_LongestSubsequenceWithLimitedSum;

import java.util.ArrayDeque;
import java.util.Deque;

public class _2389_LongestSubsequenceWithLimitedSum {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        // 单调递减栈
        Deque<Integer> dq = new ArrayDeque<>();
        int n = chargeTimes.length;
        long sum = 0;
        int j = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int charge = chargeTimes[i];
            int runningCost = runningCosts[i];
            while (!dq.isEmpty() && chargeTimes[dq.getLast()] <= charge) {
                dq.pollLast();
            }
            dq.addLast(i);
            sum += runningCost;
            while (!dq.isEmpty() && chargeTimes[dq.getFirst()] + sum * (i - j + 1) > budget) {
                if (dq.getFirst() == j) {
                    dq.pollFirst();
                }
                sum -= runningCosts[j++];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
