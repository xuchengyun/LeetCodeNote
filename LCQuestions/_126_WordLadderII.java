package LCQuestions;

import java.util.*;

public class _126_WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> level = new HashMap<>();
        for (String s : dict) {
            level.put(s, Integer.MAX_VALUE);
        }
        level.put(beginWord, 0);

        Map<String, List<String>> neighbors = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int minLevel = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int nextStep = level.get(cur) + 1;
            if (nextStep > minLevel) break;
            for (int i = 0; i < cur.length(); i++) {
                char[] chars = cur.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String newWord = String.valueOf(chars);
                    if (dict.contains(newWord)) {
                        if (nextStep > level.get(newWord)) {
                            continue;
                        } else if (nextStep < level.get(newWord)){
                            level.put(newWord, nextStep);
                            queue.offer(newWord);
                        }
                        if (neighbors.containsKey(cur)) {
                            neighbors.get(cur).add(newWord);
                        } else {
                            List<String> l = new ArrayList<>();
                            l.add(newWord);
                            neighbors.put(cur,  l);
                        }
                    }
                    if (newWord.equals(endWord)) {
                        minLevel = nextStep;
                    }
                }
            }
        }
        List<String> solution = new ArrayList<>();
        dfs(beginWord, endWord, res, solution, neighbors);
        return res;
    }

    private void dfs(String cur, String end, List<List<String>> res, List<String> solution, Map<String, List<String>> neighbors ) {
        solution.add(cur);
        if (cur.equals(end)) {
            res.add(new ArrayList<>(solution));
        } else {
            List<String> myNeighbors = neighbors.get(cur);
            if (myNeighbors != null) {
                for (String n : myNeighbors) {
                    dfs(n, end, res,solution, neighbors);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }

    public static void main(String[] args) {
        _126_WordLadderII w = new _126_WordLadderII();
        String start = "a";
        String end = "c";
        String[] sArray = new String[]{"a", "b", "c"};
        List<String> la = Arrays.asList(sArray);
        w.findLadders(start, end, la);
    }
}
