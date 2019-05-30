package LCQuestions.lc191NumberOf1Bits;

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n & 1;
            n >>>= 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        NumberOf1Bits n = new NumberOf1Bits();
        n.hammingWeight(0b11111111111111111111111111111101);
        System.out.println();
    }
}
