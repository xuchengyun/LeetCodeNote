package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _056_MergeIntervals {

    /**
     扫面线算法 swipe line
     \__\    \__\
       \__\    \___\


     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] <= end) {
                end = Math.max(end, interval[1]);
            } else {
                res.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[res.size()][2]);
    }

    public int[][] merge1(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        List<int[]> res = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (i == intervals.length - 1 || start[i + 1] > end[i]) {
                res.add(new int[]{start[index], end[i]});
                index = i + 1;
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
