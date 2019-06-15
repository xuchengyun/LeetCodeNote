package LCQuestions;

public class _400_NthDigit {

    /**
     * 1 - 9 : 9
     * 10 -99 : 90
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;
        while (n > len * count) {
            n -= len * count;
            len ++;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len;
        String s = Integer.toString(start);
        return s.charAt((n - 1) % len) - '0';
    }
}
