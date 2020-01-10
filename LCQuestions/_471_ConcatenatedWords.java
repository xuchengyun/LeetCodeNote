package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _471_ConcatenatedWords {
    TrieNode root;

    public static void main(String[] args) {
        _471_ConcatenatedWords obj = new _471_ConcatenatedWords();
        obj.findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"});

    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        root = new TrieNode();
        for (String word : words) {
            addToTrie(word);
        }

        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (isConcatenate(word.toCharArray(), 0, 0)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isConcatenate(char[] ca, int index, int cnt) {
        if (index == ca.length) {
            return cnt >= 2;
        }
        TrieNode cur = root;
        for (int i = index; i < ca.length; i++) {
            char c = ca[i];
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
            if (cur.word != null) {
                if (isConcatenate(ca, i + 1, cnt + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addToTrie(String word) {
        TrieNode cur = root;
        char[] ca = word.toCharArray();
        for (char c : ca) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.word = word;
    }

    class TrieNode {
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
        }
    }
}
