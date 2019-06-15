package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _057_InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null ||intervals.length == 0) {
            res.add(newInterval);
            return res.toArray(new int[res.size()][2]);
        }

        int idx = 0;
        while (idx < intervals.length && intervals[idx][1] < newInterval[0]) {
            res.add(intervals[idx++]);
        }
        while ((idx < intervals.length && intervals[idx][0] <= newInterval[1])) {
            newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
            idx++;
        }
        res.add(newInterval);
        while (idx < intervals.length) {
            res.add(intervals[idx++]);
        }
        return res.toArray(new int[res.size()][2]);
    }
}
