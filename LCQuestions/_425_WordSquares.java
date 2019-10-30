package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _425_WordSquares {

    List<List<String>> res = new ArrayList<>();
    String[] words;
    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        for (String word : words) {
            List<String> list = new ArrayList<>();
            list.add(word);
            backTrack(word, 1, list);
        }
        return res;
    }

    private void backTrack(String word, int index, List<String> list) {
        if (index == word.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String l : list) {
            sb.append(l.charAt(index));
        }
        String prefix = sb.toString();
        for (String candidate : getWordsWithPrefix(prefix)) {
            list.add(candidate);
            backTrack(candidate, index + 1, list);
            list.remove(list.size() - 1);
        }
    }

    private List<String> getWordsWithPrefix(String prefix) {
        List<String> r = new ArrayList<>();
        for (String w : words) {
            if (w.startsWith(prefix)) {
                r.add(w);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        String[] input = new String[]{"area","lead","wall","lady","ball"};
        _425_WordSquares o = new _425_WordSquares();
        o.wordSquares(input);
    }
}
