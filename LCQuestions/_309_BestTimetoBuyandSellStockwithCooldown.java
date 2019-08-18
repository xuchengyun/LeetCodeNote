package LCQuestions;

public class _309_BestTimetoBuyandSellStockwithCooldown {

    /**
     * buy[i]表示在第i天之前最后一个操作是买，此时的最大收益。
     sell[i]表示在第i天之前最后一个操作是卖，此时的最大收益。
     rest[i]表示在第i天之前最后一个操作是冷冻期，此时的最大收益。
     我们写出递推式为：
     buy[i]  = max(rest[i-1] - price, buy[i-1])
     sell[i] = max(buy[i-1] + price, sell[i-1])
     rest[i] = max(sell[i-1], buy[i-1], rest[i-1])

     上述递推式很好的表示了在买之前有冷冻期，买之前要卖掉之前的股票。一个小技巧是如何保证[buy, rest, buy]的情况不会出现，这是由于buy[i] <= rest[i]， 即rest[i] = max(sell[i-1], rest[i-1])，这保证了[buy, rest, buy]不会出现。
     另外，由于冷冻期的存在，我们可以得出rest[i] = sell[i-1]，这样，我们可以将上面三个递推式精简到两个：

     buy[i]  = max(sell[i-2] - price, buy[i-1])
     sell[i] = max(buy[i-1] + price, sell[i-1])
     * @param prices
     * @return
     */
    // cool down
    public int maxProfit(int[] prices) {
        int sell = 0, pre_sell = 0, buy = Integer.MIN_VALUE, prev_buy;

        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(pre_sell - price, prev_buy);
            pre_sell = sell;
            sell = Math.max(prev_buy + price, pre_sell);
        }
        return sell;
    }
}
