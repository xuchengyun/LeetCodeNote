package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.ListNode;


public class Main {
    public static int minGroups(int[][] intervals) {

        List<Integer> l = new ArrayList<>();
        int res = 0;

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            boolean included = false;
            for (int j =0; j < l.size(); j++) {
                if (interval[0] > l.get(j)) {
                    included = true;
                    l.set(j, interval[1]);
                }
            }
            if (!included) {
                res++;
                l.add(interval[1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<ListNode> l = new ArrayList<>();
        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        a.next.next = new ListNode(4);


        int[][] intervals = new int[][]{};

    }
}
