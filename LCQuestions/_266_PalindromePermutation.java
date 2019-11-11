package LCQuestions;

import java.util.HashSet;
import java.util.Set;

public class _266_PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        return true;
    }

    public boolean canPermutePalindrome1(String s) {
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i< s.length(); i++) {
            if(set.contains(s.charAt(i))){
                set.add(s.charAt(i));
            }else {
                set.remove(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }
}
