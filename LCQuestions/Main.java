package LCQuestions;

import Utils.TreeNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
            word = "";
        }
    }

    TrieNode root;

    private void addtoTrie(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode next = curr.children[c - 'a'];
            if (next == null) {
                next = new TrieNode();
                curr.children[c - 'a'] = next;
            }
            curr = next;
        }
        curr.word = s;
    }

    private List<List<String>> input(String word) {
        TrieNode cur = root;
        List<List<String>> res = new ArrayList<>();
        boolean flag = true;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] != null && flag) {
                List<String> tmp = new ArrayList<>();
                dfsFind(cur.children[c - 'a'], tmp);
                res.add(tmp);
                cur = cur.children[c - 'a'];
            } else {
                res.add(new ArrayList<>());
                flag = false;
            }
        }
        return res;
    }

    private boolean dfsFind(TrieNode node, List<String> tmp) {
        TrieNode cur = node;
        if (cur.word != "") {
            tmp.add(cur.word);
            if (tmp.size() == 3) {
                return false;
            }
        }
        for (int i = 0; i < cur.children.length; i++) {
            if (cur.children[i] != null) {
                if (!dfsFind(cur.children[i], tmp)) {
                    return false;
                }
            }
        }
        return true;
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new TrieNode();
        for (int i = 0; i < products.length; i++) {
            addtoTrie(products[i]);
        }
        return input(searchWord);
    }

    public static void main(String[] args) {
        Main obj = new Main();
    }
}
