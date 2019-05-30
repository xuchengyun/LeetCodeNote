package LCQuestions.lc069Sqrtx;

public class Sqrtx {
    public int mySqrt(int x) {
        return (int)Math.sqrt((double) x);
    }

    // Binary Search
    public int mySqrt1(int x) {
        if (x <= 0) return 0;
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return mid;
            }
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right - 1;
    }
}
