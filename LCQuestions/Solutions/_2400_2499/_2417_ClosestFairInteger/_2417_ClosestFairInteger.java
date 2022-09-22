package LCQuestions.Solutions._2400_2499._2417_ClosestFairInteger;

import java.util.ArrayList;
import java.util.List;

public class _2417_ClosestFairInteger {
    public int closestFair(int n) {
        int digitNum = 0;
        int numOdd = 0;
        int numEven = 0;
        List<Integer> l = new ArrayList<>();
        int newN = n;
        while (newN > 0) {
            int digit = newN % 10;
            int t = (digit % 2) == 0 ? numEven++ : numOdd++;
            newN = newN / 10;
            digitNum++;
            l.add(digit);
        }
        String numStr = Integer.toString(n);

        int res = 0;
        if (digitNum == 1) {
            return 10;
        }
        if (digitNum % 2 == 1) {
            res += (int)(Math.pow(10.0, (double)digitNum));
            int base = 1;
            for (int i = 0; i < digitNum / 2; i++) {
                res += base;
                base *= 10;
            }
        } else {
            if (numEven == numOdd) {
                return n;
            } else {
                int first = Integer.parseInt(numStr.substring(0, digitNum / 2));
                int second = Integer.parseInt(numStr.substring(digitNum / 2));
                int base9 = 9;
                int base8 = 8;
                int total9 = 0;
                int total8 = 0;
                for (int i = 0; i < digitNum / 2; i++) {
                    total9 += base9;
                    base9 *= 10;
                    total8 += base8;
                    base8 *= 10;
                }
                if (first == total9 && second > total8) {
                    int base = 1;
                    res += (int)(Math.pow(10.0, (double)(digitNum + 1)));
                    for (int i = 0; i < digitNum / 2; i++) {
                        res += base;
                        base *= 10;
                    }
                } else {
                    while (true) {
                        newN=n;
                        int cntE = 0;
                        while(newN != 0){
                            int digit = newN%10;
                            newN = newN / 10;
                            if(digit % 2 == 0) cntE++;
                        }
                        if(cntE == digitNum - cntE) return n;
                        n++;
                    }
                }
            }

        }

        return res;
    }
}
