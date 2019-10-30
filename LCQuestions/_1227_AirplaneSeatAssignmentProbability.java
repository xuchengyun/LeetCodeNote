package LCQuestions;

public class _1227_AirplaneSeatAssignmentProbability {
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1;
        }
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n  - 1);
    }

    //dp
    public double nthPersonGetsNthSeat1(int n) {
        double[] dp = new double[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1d / (i + 1) + (i - 1d) / (i + 1) * dp[i - 1];
        }
        return dp[n - 1];
    }
}
