package LCQuestions.lc1015NumbersWithRepeatedDigits;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumbersWithRepeatedDIgits {
    public int numDupDigitsAtMostN(int N) {
        List<Integer> digits = new ArrayList<>();
        for (int tmp = N + 1; tmp > 0; tmp /= 10) {
            digits.add(0, tmp % 10);
        }

        int len = digits.size();

        int noRepeatNum = 0;
        for (int i = 0; i < len - 1; i++) {
            noRepeatNum += calNoRepeatWithinDigitNum(i);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            int start = 1;
            if (i != 0) {
                start = 0;
            }
            for (int j = start; j < digits.get(i); j++) {
                if (!set.contains(j)) {
                    noRepeatNum += calNoRepeatWithinDigitNum(9 - i, len - i - 1);
                }
            }
            if (set.contains(digits.get(i))) break;

            set.add(digits.get(i));
        }
        return N - noRepeatNum;
    }

    private int calNoRepeatWithinDigitNum(int num) {
        int res = 9;
        for (int i = 0; i < num; i++) {
            res *= (9 - i);
        }
        return res;
    }

    private int calNoRepeatWithinDigitNum(int largestNum, int num) {
        if (num == 0) return 1;
        int res = largestNum;
        for (int i = 1; i < num; i++) {
            res *= (largestNum - i);
        }
        return res;
    }

    public static void main(String[] args) {
        NumbersWithRepeatedDIgits n = new NumbersWithRepeatedDIgits();
        System.out.println(n.numDupDigitsAtMostN(7654));
    }

}
