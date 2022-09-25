package LCQuestions.Solutions._1600_1699._1680_ConcatenationOfConsecutiveBinaryNumbers;

public class _1680_ConcatenationOfConsecutiveBinaryNumbers {
    public int concatenatedBinary(int n) {
        int MOD = 1000000007;
        long res = 0;
        int len = 0;
        for (int i = 1; i <= n; i++) {
            // when meets power of 2, increase the bit length
            if ((i & (i - 1)) == 0) {
                len++;
            }
            res = ((res << len) + i) % MOD;
        }
        return (int) res;
    }
}
