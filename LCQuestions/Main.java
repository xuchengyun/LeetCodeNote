package LCQuestions;

import java.util.*;

import Utils.ListNode;


public class Main {
    public int[] sumPrefixScores(String[] words) {
        int n = words.length, p = 31, m = (int)1e9 + 9;
        int[] ans = new int[n];
        long[] hash = new long[n], p_pow = new long[n];
        Arrays.fill(p_pow,1);
        for(int i = 0; i < 1000; i++){
            Map<Long,Integer> map = new HashMap<>();
            for(int j = 0; j < n; j++){
                if(words[j].length() <= i) continue;
                hash[j] = (hash[j] + (words[j].charAt(i) - 'a' + 1) * p_pow[j]) % m;
                p_pow[j] = (p_pow[j] * p) % m;
                map.put(hash[j], map.getOrDefault(hash[j],0) + 1);
            }
            for(int j = 0; j < n; j++){
                if(words[j].length() <= i) continue;
                ans[j] += map.get(hash[j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.sumPrefixScores(new String[]{"abc","ab","bc","b"});

    }
}
