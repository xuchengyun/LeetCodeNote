package LCQuestions;

public class _188_BestTimetoBuyandSellStockIV {

    /**
     Say you have an array for which the i-th element is the price of a given stock on day i.
     Design an algorithm to find the maximum profit. You may complete at most k transactions.
     Note:
     You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * @param k
     * @param prices
     * @return
     */
    // This is slower solution
    // T[i][j] 当前达到第j天最多可以进行i次交易，最大利润是多少
    // T[i][j] = Max(T[i][j - 1], prices[j] - price[m] + T[i - 1][m - 1])   m = 0...j - 1
    // Convert to faster solution
    // T[i][j] = max: T[i][j - 1]
    //                price[j] + MaxDiff
    //                MaxDiff = max(maxdiff, T[i - 1][j - 1] - price[j])
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], tmpMax + prices[j]);
                tmpMax = Math.max(tmpMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        return profit;
    }


//    hold[i][j]代表可以交易i次 在第j天時 持有股票的最大獲利
//    unhold[i][j]代表可以交易i次 在第j天時 不持有股票的最大獲利
//    若把買入股票當作交易（賣出股票就不算交易次數）
//    遞推式是
//    hold[i][j] = max(hold[i][j-1], unhold[i-1][j-1] - prices[j]);
//    unhold[i][j] = max(unhold[i][j-1], hold[i][j-1] + prices[j]);
//    不過要另外考量2個邊界狀況
//    1. 第0天 也就是j=0
//    2. 可交易1次 也就是i=0
//    可以擴展dp邊界處理 但直接if判斷也滿簡單的
    public int maxProfit1(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);
        int[][] hold = new int[k + 1][len];
        int[][] unhold = new int[k + 1][len];

        for (int i = 1; i < k + 1; i++) {
            for (int j = 0; j < len; j++) {
                if (j == 0) {
                    hold[i][j] = -prices[j];
                    unhold[i][j] = 0;
                } else {
                    hold[i][j] = Math.max(hold[i][j - 1], unhold[i - 1][j - 1] - prices[j]);
                    unhold[i][j] = Math.max(unhold[i][j - 1], hold[i][j - 1] + prices[j]);
                }
            }
        }
        return Math.max(hold[k][len - 1], unhold[k][len - 1]);
    }

    /**
     * This is slow method but easier to understand.
     * Time complexity is O(k * number of days ^ 2)
     * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     */
    public int maxProfitSlowSolution(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices.length];

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, prices[j] - prices[m] + T[i-1][m]);
                }
                T[i][j] = Math.max(T[i][j-1], maxVal);
            }
        }
        return T[K][prices.length - 1];
    }
}
