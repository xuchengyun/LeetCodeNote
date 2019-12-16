package LCQuestions;

import java.util.Stack;

public class _020_ValidParentheses {
    // stack
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
            if (c == '}') {
                if (stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            }
            if (c == ']') {
                if (stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            }
        }
        return true;
    }
}
