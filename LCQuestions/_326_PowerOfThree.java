package LCQuestions;

public class _326_PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }


    public boolean isPowerOfThree1(int n) {
        return (Math.log(n) / Math.log(3)) % 1 == 0;
    }
}
