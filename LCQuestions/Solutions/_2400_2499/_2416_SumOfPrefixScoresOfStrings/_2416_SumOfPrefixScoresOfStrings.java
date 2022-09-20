package LCQuestions.Solutions._2400_2499._2416_SumOfPrefixScoresOfStrings;

import Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _2416_SumOfPrefixScoresOfStrings {
    static class TrieNode {
        TrieNode root;
        char val;
        boolean isLeaf;
        TrieNode[] children = new TrieNode[26];
        int score;

    }

    public int[] sumPrefixScores(String[] words) {
        TrieNode root = ConstructTrie(words);
        return getScore(root, words);
    }

    private int[] getScore(TrieNode root, String[] words) {
        int[] res = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int score = 0;
            TrieNode node = root;
            for (char c: words[i].toCharArray()) {
                score += node.children[c - 'a'].score;
                node = node.children[c - 'a'];
            }
            res[i] = score;
        }
        return res;
    }

    private TrieNode ConstructTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    node.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                node =  node.children[word.charAt(i) - 'a'];
                node.score++;
            }
        }
        return root;
    }
}
