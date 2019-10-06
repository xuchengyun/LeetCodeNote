package LCQuestions;

import java.util.HashMap;
import java.util.HashSet;

public class _291_WordPatternII {


    /**
     Given a pattern and a string str, find if str follows the same pattern.
     Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
     Example 1:
     Input: pattern = "abab", str = "redblueredblue"
     Output: true
     Example 2:
     Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
     Output: true
     Example 3:
     Input: pattern = "aabb", str = "xyzabcxzyabc"
     Output: false
     Notes:
     You may assume both pattern and str contains only lowercase letters.
     * @param pattern
     * @param str
     * @return
     */
    // backTracking
    public boolean wordPatternMatch(String pattern, String str) {
        return isMatch(str, 0, pattern, 0, new HashMap<>(), new HashSet<>());
    }

    private boolean isMatch(String str, int i, String pattern, int j, HashMap<Character, String> map, HashSet<String> set) {
        if (str.length() == i && pattern.length() == j) {
            return true;
        }
        if (str.length() == i || pattern.length() == j) {
            return false;
        }
        char curP = pattern.charAt(j);
        if (map.containsKey(curP)) {
            String s = map.get(curP);
            if (str.startsWith(s, i)) {
                return isMatch(str, i + s.length(), pattern, j + 1, map, set);
            }
            return false;
        }

        for (int k = i; k < str.length(); k++) {
            String cur = str.substring(i, k + 1);
            if (set.contains(cur)) {
                continue;
            }
            map.put(curP, cur);
            set.add(cur);
            if (isMatch(str, k + 1, pattern, j + 1, map, set)) {
                return true;
            }
            map.remove(curP);
            set.remove(cur);
        }
        return false;
    }
}
