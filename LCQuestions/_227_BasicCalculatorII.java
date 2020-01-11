package LCQuestions;

import java.util.Stack;

/**
 * Created by xuchengyun on 12/31/19.
 */
public class _227_BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int tmp = 0;
        char preOp = '+';
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                tmp = tmp * 10 + cur - '0';
                if (i != s.length() - 1) {
                    continue;
                }
            }
            if (cur == ' ') {
                continue;
            }
            if (preOp == '+') {
                stack.push(tmp);
            } else if (preOp == '-') {
                stack.push(-tmp);
            } else if (preOp == '*') {
                stack.push(stack.pop() * tmp);
            } else if (preOp == '/') {
                stack.push(stack.pop() / tmp);
            }
            tmp = 0;
            preOp = cur;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
    // no stack
    //3 + 5 * 2
    public int calculate1(String s) {
        int curRes = 0;
        int total = 0;
        char op = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c <= '9' && c >= '0') {
                num = num * 10 + c - '0';
                if (s.length() != i - 1) {
                    continue;
                }
            }
            if (op == '+') {
                curRes += num;
            } else if (op == '-') {
                curRes -= num;
            } else if (op == '*') {
                curRes *= num;
            } else if (op == '/') {
                curRes /= num;
            }
            if (c == '+' || c == '-' || i == s.length() - 1) {
                total += curRes;
                curRes = 0;
            }
            op = c;
            num = 0;
        }
        return total;
    }

    public static void main(String[] args) {
        _227_BasicCalculatorII obj = new _227_BasicCalculatorII();
        obj.calculate1("3+2*2");
    }
}
