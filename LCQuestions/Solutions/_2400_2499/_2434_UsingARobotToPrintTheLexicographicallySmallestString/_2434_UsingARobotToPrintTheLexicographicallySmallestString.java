package LCQuestions.Solutions._2400_2499._2434_UsingARobotToPrintTheLexicographicallySmallestString;

import java.util.ArrayDeque;
import java.util.Deque;

public class _2434_UsingARobotToPrintTheLexicographicallySmallestString {
    public String robotWithString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        int[] freq = new int[26];
        for (char c: s.toCharArray()){
            freq[c -'a']++;
        }

        for (char c: s.toCharArray()) {
            stack.push(c);
            freq[c - 'a']--;
            while (!stack.isEmpty() && !hasSmaller(stack.peek(), freq)) { // check if there is a smaller character in the rest of the string compared to top character of the stack
                res.append(stack.pop());
            }
        }
        return res.toString();
    }

    private boolean hasSmaller(char c, int[] freq) {
        int ind = c - 'a';
        for (int i = 0; i < ind; i++) {
            if (freq[i] > 0) return true;
        }
        return false;
    }
}
