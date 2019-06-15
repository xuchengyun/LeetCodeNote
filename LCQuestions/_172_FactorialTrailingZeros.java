package LCQuestions;

public class _172_FactorialTrailingZeros {

    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
