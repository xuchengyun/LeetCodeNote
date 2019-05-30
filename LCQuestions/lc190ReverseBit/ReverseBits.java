package LCQuestions.lc190ReverseBit;

public class ReverseBits {
    // bit 运算记得要加括号
    public int reverseBits(int n) {
        // bit manipulation
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                res = res << 1 + 1;
            } else {
                res <<= 1;
            }
            n <<= 1;
        }
        return res;
    }
}
