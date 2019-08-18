package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _212_WordSearchII {
    /**
     *
      Trie + DFS

     * @param boards
     * @param words
     * @return
     */
    public List<String> findWords(char[][] boards, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        int m = boards.length;
        int n = boards[0].length;
        // dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(boards, root, res, i, j);
            }
        }
        return res;
    }

    public void dfs(char[][] boards, TrieNode node, List<String> res, int i, int j) {
        if (i < 0 || i >= boards.length || j < 0 || j >= boards[0].length) return;
        char c = boards[i][j];
        int k = c - 'a';
        // '#' to check if it is visited
        if (c == '#' || node.children[k] == null) return;
        node = node.children[k];
        boards[i][j] = '#'; //visited
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }

        dfs(boards, node, res, i + 1, j);
        dfs(boards, node, res, i - 1, j);
        dfs(boards, node, res, i, j - 1);
        dfs(boards, node, res, i, j + 1);
        boards[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curNode = root;
            char[] cArray = word.toCharArray();
            for (char c : cArray) {
                int k = c - 'a';
                if (curNode.children[k] == null) {
                    curNode.children[k] = new TrieNode();
                }
                curNode  = curNode.children[k];
            }
            curNode.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children;
        String word;
        TrieNode() {
            this.children = new TrieNode[26];
        }
    }
}
