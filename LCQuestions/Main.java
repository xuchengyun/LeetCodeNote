package LCQuestions;

import java.util.*;

import Utils.ListNode;


public class Main {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();


        for (String path: paths) {
            String[] files = path.split(" ");
            String dir = files[0];
            for (int i = 1; i < files.length; i++) {
                String[] fileArr = files[i].split("\\(");
                String name = fileArr[0];
                String hash = fileArr[1].substring(0, fileArr[1].indexOf(")"));
                List<String> l = map.getOrDefault(hash, new ArrayList<String>());
                l.add(dir + name);
            }
        }

        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }

    public String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }


    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"});

    }
}
