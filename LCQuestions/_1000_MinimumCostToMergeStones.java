package LCQuestions;

public class _1000_MinimumCostToMergeStones {
    public static void main(String[] args) {
        _1000_MinimumCostToMergeStones c = new _1000_MinimumCostToMergeStones();
    }
    /*
    So we need to know the minimum cost of merging left part to 1 pile, and minimum cost of merging right part to 1 pile, this is a typical sub problem.
State: Minimum cost merging piles from i to j to 1 pile.

Function: dp[i][j] = min(sum[i][j] + dp[i][k] + dp[k + 1][j]) (i <= k < j)

Init: dp[i][i] = 0 (Already a pile)

Answer: dp[1][len] (len is the stones number)
     */
    public int mergeStonesTwo(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int len = stones.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[len + 1][len + 1];
        int[] prefixSum = new int[len + 1];
        int i, j, k, l;
        for (i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }
        
        for (i = 1; i <= len; i++) {
            dp[i][i] = 0;
        }
        
        for (l = 2; l <= len; l++) {
            for (i = 1; i <= len - l + 1; i++) {
                j = i + l - 1;
                dp[i][j] = max;
                int sum = prefixSum[j] - prefixSum[i - 1];
                for (k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum);
                }
            }
        }
        
        return dp[1][len];
    }
}
