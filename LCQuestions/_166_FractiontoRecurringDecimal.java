package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _166_FractiontoRecurringDecimal {

    /**
     Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     If the fractional part is repeating, enclose the repeating part in parentheses.

     Example 1:
     Input: numerator = 1, denominator = 2
     Output: "0.5"
     Example 2:

     Input: numerator = 2, denominator = 1
     Output: "2"
     Example 3:
     Input: numerator = 2, denominator = 3
     Output: "0.(6)"
     * @param numerator
     * @param denominator
     * @return
     */
    //1, 0 + -
    //2, 整数
    //3, 小数
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        res.append(((numerator > 0) ^ (denominator > 0) ? "-" : ""));
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        res.append('.');
        //用hashmap 记录位置
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());

        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, '(');
                res.append(')');
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}
