package LCQuestions;

import java.util.*;

import Utils.ListNode;


public class Main {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            String reverseStr = new StringBuilder(cur).reverse().toString();
            // if (map.containsKey("")) {
            //     if (isPalindrome(words[i])) {
            //         int index = map.get("");
            //         if (i == index) continue;
            //         res.add(Arrays.asList(index, i));
            //         res.add(Arrays.asList(i, index));
            //     }
            // }
//            if (map.containsKey(reverseStr)) {
//                int index = map.get(reverseStr);
//                if (index != i) {
//                    res.add(Arrays.asList(i, index));
//                    continue;
//                }
//            }

            for (int cut = 0; cut <= cur.length(); cut++) {
                if (isPalindrome(cur.substring(0, cut))) {
                    String cut_r = reverseStr(cur.substring(cut));
                    if (map.containsKey(cut_r)) {
                        int found = map.get(cut_r);
                        if (found != i) {
                            res.add(Arrays.asList(found, i));
                        }
                    }
                }
                if (isPalindrome(cur.substring(cut))) {
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if (map.containsKey(cut_r)) {
                        int found = map.get(cut_r);
                        if (found != i) {
                            res.add(Arrays.asList(i, found));
                        };
                    }
                }
            }
        }
        return res;
    }

    public String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }


    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});

    }
}
