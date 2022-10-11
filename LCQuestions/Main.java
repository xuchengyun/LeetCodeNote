package LCQuestions;

import java.util.*;

import Utils.ListNode;


public class Main {
    public String robotWithString(String s) {
        return robotWithString(0, s.length() - 1, s, new String[s.length()][s.length()]);
    }

    private String robotWithString(int i, int j, String s, String[][] memo) {
        if (i > j) {
            return "";
        }
        if (i == j) {
            return String.valueOf(s.charAt(i));
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        String res = s.substring(i, j + 1);
        int index = i;
        while (index <= j) {
            String newStr = robotWithString(i, index, s, memo) + robotWithString(index + 1, j, s, memo);
            System.out.println(index + " " + i + " " + j);
            if (newStr.compareTo(res) < 0) {
                res = newStr;
            }
            index++;
        }
        memo[i][j] = res;
        return res;
    }


    public static void main(String[] args) {
        Main m = new Main();
        String s = m.robotWithString("bdaa");
        System.out.println(s);
    }
}
