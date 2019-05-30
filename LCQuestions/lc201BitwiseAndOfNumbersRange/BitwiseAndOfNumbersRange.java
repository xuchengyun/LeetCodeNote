package LCQuestions.lc201BitwiseAndOfNumbersRange;

public class BitwiseAndOfNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            offset++;
        }
        return n <<= offset;
    }

    public int rangeBitwiseAnd1(int m, int n) {
        int mask = Integer.MAX_VALUE;
        while ((m & mask) != (n & mask)) {
            mask <<= 1;
        }
        return n & mask;
    }
}
