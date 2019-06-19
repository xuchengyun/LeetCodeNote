package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _340_LongestSubstringWithAtMostKDistincCharacters {
    /**
     * for example s = "eceba" and k = 2;
     * sliding window
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
         Map<Character, Integer> count = new HashMap<>();
         int res = 0;
         int j = 0; //left pointer
        for (int i = 0; i < s.length(); i++) {
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
            while (count.size() > k) {
                if (count.containsKey(s.charAt(j))) {
                    if (count.get(s.charAt(j)) == 1) {
                        count.remove(s.charAt(j));
                    } else {
                        count.put(s.charAt(j), count.get(s.charAt(j)) - 1);
                    }
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        lengthOfLongestSubstringKDistinct("ccaabbb", 2);
    }
}
