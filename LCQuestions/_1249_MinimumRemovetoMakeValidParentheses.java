package LCQuestions;

import java.util.Stack;

public class _1249_MinimumRemovetoMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        boolean[] invalidIndex = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                invalidIndex[i] = true;
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    invalidIndex[i] = false;
                } else {
                    invalidIndex[stack.pop()] = false;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!invalidIndex[i]) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}
