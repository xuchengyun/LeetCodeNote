package LCQuestions;

import java.util.Arrays;

public class _1170_CompareStringsbyFrequencyoftheSmallestCharacter {

    public static void main(String[] args) {
        String[] q = new String[]{"aabbabbb", "abbbabaa", "aabbbabaa", "aabba", "abb", "a", "ba", "aa", "ba", "baabbbaaaa", "babaa", "bbbbabaa"};
        String[] w = new String[]{"b", "aaaba", "aaaabba", "aa", "aabaabab", "aabbaaabbb", "ababb", "bbb", "aabbbabb", "aab", "bbaaababba", "baaaaa"};
        _1170_CompareStringsbyFrequencyoftheSmallestCharacter a = new _1170_CompareStringsbyFrequencyoftheSmallestCharacter();
        a.numSmallerByFrequency(q, w);
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] q = new int[queries.length];
        int[] w = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            q[i] = getSmallestFreq(queries[i]);
        }

        for (int i = 0; i < words.length; i++) {
            w[i] = getSmallestFreq(words[i]);
        }
        Arrays.sort(w);
        int[] res = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            int left = 0, right = w.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (w[mid] == q[i]) {
                    left = mid + 1;
                } else if (w[mid] > q[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            res[i] = w.length - left;
        }
        return res;
    }

    private int getSmallestFreq(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        for (int count : cnt) {
            if (count != 0) {
                return count;
            }
        }
        return -1;
    }
}
