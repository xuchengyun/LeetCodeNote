package LCQuestions;

public class _211_AddAndSearchWord {
    class TrieNode {
        TrieNode[] children;
        boolean isLeaf;
        TrieNode() {
            this.children = new TrieNode[26];
            this.isLeaf = false;
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public _211_AddAndSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word.toCharArray(), root, 0);
    }

    public boolean search(char[] chs, TrieNode node, int index) {
        if (chs.length == index) return node.isLeaf;
        if (chs[index] != '.') {
            if (node.children[chs[index] - 'a'] != null) {
                return search(chs, node.children[chs[index] -'a'], index + 1);
            }
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    if (search(chs, node.children[i], index + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
