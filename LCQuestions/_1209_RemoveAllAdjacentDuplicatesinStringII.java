package LCQuestions;

import java.util.Deque;
import java.util.LinkedList;

public class _1209_RemoveAllAdjacentDuplicatesinStringII {

    /**
     Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
     We repeatedly make k duplicate removals on s until we no longer can.
     Return the final string after all such duplicate removals have been made.
     It is guaranteed that the answer is unique.
     * @param s
     * @param k
     * @return
     */
    // use two pointer to simulate stack
    public static String removeDuplicates(String s, int k) {
        // two pointer to simulate stack
        char[] stack = new char[s.length()];
        int[] count = new int[s.length()];
        int stackTail = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack[stackTail] = c;
            count[stackTail] = stackTail > 0 && stack[stackTail] == stack[stackTail - 1] ?
                    count[stackTail - 1] + 1 : 1;
            if (count[stackTail] == k) {
                stackTail -= k;
            }
            stackTail++;
        }
        return new String(stack, 0, stackTail);
    }

    // use stack deque
    public String removeDuplicates1(String s, int k) {
        Deque<Node> dq = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dq.isEmpty()) {
                dq.push(new Node(c, 1));
            } else {
                if (dq.peek().val == c) {
                    dq.peek().count++;
                } else {
                    dq.push(new Node(c, 1));
                }
            }
            if (dq.peek().count == k) {
                dq.pop();
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!dq.isEmpty()) {
            for (int i = 0; i < dq.peekLast().count; i++) {
                builder.append(dq.peekLast().val);
            }
            dq.pollLast();
        }
        return builder.toString();
    }

    // recursion
//    public String removeDuplicates2(String s, int k) {
//        int cnt = 0;
//        for (int i = 0; i <  )
//
//        Deque<Node> dq = new LinkedList<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (dq.isEmpty()) {
//                dq.push(new Node(c, 1));
//            } else {
//                if (dq.peek().val == c) {
//                    dq.peek().count++;
//                } else {
//                    dq.push(new Node(c, 1));
//                }
//            }
//            if (dq.peek().count == k) {
//                dq.pop();
//            }
//        }
//        StringBuilder builder = new StringBuilder();
//        while (!dq.isEmpty()) {
//            for (int i = 0; i < dq.peekLast().count; i++) {
//                builder.append(dq.peekLast().val);
//            }
//            dq.pollLast();
//        }
//        return builder.toString();
//    }

    class Node {
        char val;
        int count;
        public Node (char val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        _1209_RemoveAllAdjacentDuplicatesinStringII obj = new _1209_RemoveAllAdjacentDuplicatesinStringII();
        obj.removeDuplicates1("abcd", 2);
    }
}
