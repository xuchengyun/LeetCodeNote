package LCQuestions.lc008StringToInteger;

public class StringToInteger {
    /**
     *  corner case
     *  1. +
     *  2. -
     *  3 越界
     *  time : O (n)
     *  space : O (1);
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        int sign = 1;
        int index = 0;
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            index++;
            sign = -1;
        }
        long res = 0;
        while (index < str.length()) {
            if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                res = res * 10 + str.charAt(index) - '0';
                if (res > Integer.MAX_VALUE) {
                    break;
                }
            } else {
                break;
            }
        }
        if (res * sign >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res * sign <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)res * sign;
    }
}
