package LCQuestions.Solutions._2300_2399._2390_RemovingStarsFromAString;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class _2390_RemovingStarsFromAString {
    public String removeStars(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for(char ch:s.toCharArray()){
            if(ch!='*') {
                dq.addLast(ch);
            } else {
                dq.removeLast();
            }
        }
        StringBuilder sb = new StringBuilder();

        while(!dq.isEmpty()) {
            sb.append(dq.removeFirst());
        }

        return sb.toString();
    }
}
