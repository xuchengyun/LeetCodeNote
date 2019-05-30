package LCQuestions.lc007ReverseInteger;

public class ReverseInteger {
    /**
     *  need to care about the case for int overflow
     *  int range:
     *  -2147483648 ~ 2147483647
     *  time: O(n);
     *  space: O(1);
     */
    // convert res to long to solve this issue
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int tail = x % 10;
            x = x / 10;
            res = res * 10 + tail;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int)res;
    }

    // only store int value
    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            if (Math.abs(res) > Integer.MAX_VALUE / 10) return 0;
            int tail = x % 10;
            x = x / 10;
            res = res * 10 + tail;
        }
        return res;
    }
}
