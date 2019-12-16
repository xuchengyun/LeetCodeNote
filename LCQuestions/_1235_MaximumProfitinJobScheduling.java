package LCQuestions;

import java.util.Arrays;

public class _1235_MaximumProfitinJobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] tmp = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tmp[i][0] = startTime[i];
            tmp[i][1] = endTime[i];
            tmp[i][2] = profit[i];
        }
        Arrays.sort(tmp, (o1, o2) -> (o1[0] - o2[0]));
        for (int i = 0; i < startTime.length; i++) {
            startTime[i] = tmp[i][0];
            endTime[i] = tmp[i][1];
            profit[i] = tmp[i][2];
        }
        return helper(0, profit, startTime, endTime, new Integer[startTime.length]);
    }

    private int helper(int index, int[] profit, int[] startTime, int[] endTime, Integer[] memo) {
        if (index >= profit.length) {
            return 0;
        }
        int taken = 0;
        int noTaken;
        if (memo[index] != null) {
            return memo[index];
        }
        noTaken = helper(index + 1, profit, startTime, endTime, memo);
        int cur = 0;
        for (int i = index + 1; i < startTime.length; i++) {
            if (endTime[index] <= startTime[i]) {
                cur = profit[i];
            }
        }
        taken += cur + profit[index];
        memo[index] = Math.max(taken, noTaken);
        return memo[index];
    }
}
