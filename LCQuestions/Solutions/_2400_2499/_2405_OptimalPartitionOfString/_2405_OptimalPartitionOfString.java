package LCQuestions.Solutions._2400_2499._2405_OptimalPartitionOfString;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _2405_OptimalPartitionOfString {
    public int partitionString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int res = 1;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                res++;
                set.clear();
                set.add(c);
            } else {
                set.add(c);
            }
        }
        return res;
    }

    public int partitionString1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int res = 0, last = -1;

        for (int i = 0; i < s.length(); i++) {
            if (pos[s.charAt(i) - 'a'] >= last) {
                res++;
                last = i;
            }
            pos[s.charAt(i) - 'a'] = i;
        }
        return res;
    }

    // Bit manipulation
    public int partitionString2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int map = 0;
        int res = 1;
        for (char c : s.toCharArray()) {
            if ((map & (1 << (c - 'a'))) != 0) {
                res++;
                map = 0;
            }
            map ^= 1 << (c - 'a');
        }
        return res;
    }
}
