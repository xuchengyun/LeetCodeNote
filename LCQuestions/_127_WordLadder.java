package LCQuestions;

import java.util.*;

public class _127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int res = 0;
        while (!q.isEmpty()) {
            res++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                char[] cr = s.toCharArray();
                for (int j = 0; j < cr.length; j++) {
                    for (char k = 'a'; k < 'z'; k++) {
                        char tmp = cr[j];
                        cr[j] = k;
                        String ns = new String(cr);
                        if (ns.equals(endWord) && set.contains(endWord)) {
                            return res + 1;
                        }
                        cr[j] = tmp;
                        if (!set.contains(ns) || visited.contains(ns)) {
                            continue;
                        }
                        visited.add(ns);
                        q.offer(ns);
                    }
                }
            }
        }
        return 0;
    }
}
