package LCQuestions;

import java.util.*;

import Utils.ListNode;


public class Main {
    public int concatenatedBinary(int n) {
        int mod = 1000000007;
        int res = 0;
        int pos = 0;
        for (int i = n; i >= 1; i--) {
            String output = Integer.toBinaryString(i);
            long tmp = i;
            res += (tmp << pos) % mod;
            pos += output.length();
            
        }
        return res % mod;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.concatenatedBinary(12);

    }
}
