package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _1269_NumberofWaystoStayintheSamePlaceAfterSomeSteps {
    final static int MOD = (int) Math.pow(10, 9) + 7;

    public static void main(String[] args) {
        _1269_NumberofWaystoStayintheSamePlaceAfterSomeSteps obj = new _1269_NumberofWaystoStayintheSamePlaceAfterSomeSteps();
        obj.numWays(27, 7);
    }

    public int numWays(int steps, int arrLen) {
        Map<String, Integer> memo = new HashMap<>();
        return helper(steps, 0, memo, arrLen);
    }

    private int helper(int steps, int pos, Map<String, Integer> memo, int len) {
        if (steps == 0 && pos == 0) {
            return 1;
        }
        if (pos < 0 || pos >= len || steps == 0) {
            return 0;
        }
        String key = steps + "," + pos;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int sum = (helper(steps - 1, pos - 1, memo, len) % MOD + helper(steps - 1, pos, memo, len) % MOD) % MOD + helper(steps - 1, pos + 1, memo, len) % MOD;
        memo.put(key, sum % MOD);
        return sum % MOD;
    }
}
