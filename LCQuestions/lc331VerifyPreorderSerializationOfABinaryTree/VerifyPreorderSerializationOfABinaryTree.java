package LCQuestions.lc331VerifyPreorderSerializationOfABinaryTree;

import java.util.Stack;

public class VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) return false;

        Stack<String> stack = new Stack<>();

        String[] strs = preorder.split(",");

        for (int i = 0; i < strs.length; i++) {
            String curr = strs[i];
            if (!stack.isEmpty() && stack.peek().equals("#")) {
                if (curr.equals("#")) {
                    stack.pop();
                }
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }

        return (stack.size() == 1 && stack.peek().equals("#"));
    }

    // method 2. recursion
    public boolean isValidSerialization1(String preorder) {
        if (preorder == null) return false;
        String[] tree = preorder.split(",");
        int index = isValid(tree, 0);
        return tree.length - 1 == index;
    }

    private int isValid(String[] s, int index) {
        if (s.length == index) return -1;
        if (s[index].equals("#")) {
            return index;
        }

        int left = isValid(s, index + 1);
        if (left == -1) return -1;
        int right = isValid(s, left + 1);
        if (right == -1) return -1;
        return right;
    }
}
