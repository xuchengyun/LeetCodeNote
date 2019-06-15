package LCQuestions;

import java.util.Stack;

/**
 *  (1+(4+5+2)-3)+(6+8)
 *
 */
public class _224_BasicCalculator {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int cur = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    cur = 10 * cur + s.charAt(++i) - '0';
                }
                res += sign * cur;
            } else if(c == '+') {
                sign = 1;
            } else if(c == '-') {
                sign = -1;
            } else if(c == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if(c == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }

    public int calculate1(String s) {
        int res = 0, sign = 1, len = s.length();
        for (int i = 0; i < len; i++) {
            int num = 0;
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int cur = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    cur = 10 * cur + s.charAt(++i) - '0';
                }
                num += sign * cur;
            } else if (c == '(') {
                int j = i, cnt = 0;
                while (i < len) {
                    if (s.charAt(i) == '(') {
                        cnt++;
                    } else if (s.charAt(i) == ')') {
                        cnt--;
                    }
                    if (cnt == 0) break;
                    i++;
                }
                int cur = calculate(s.substring(j + 1, i - j - 1));
                num += sign * cur;
            }
            res += num;
            if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            }
        }
        return res;
    }
}
