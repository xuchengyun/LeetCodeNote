package LCQuestions.lc155MinStack;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;

    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            int min = minStack.peek();
            if (x <= min) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop(int x) {
        int min = stack.pop();
        if (min == getMin()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
