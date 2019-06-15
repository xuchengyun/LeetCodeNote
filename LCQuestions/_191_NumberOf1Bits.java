package LCQuestions;

public class _191_NumberOf1Bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n & 1;
            n >>>= 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        _191_NumberOf1Bits n = new _191_NumberOf1Bits();
        n.hammingWeight(0b11111111111111111111111111111101);
        System.out.println();
    }
}
