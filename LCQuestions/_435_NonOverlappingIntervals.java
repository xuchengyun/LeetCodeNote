package LCQuestions;

import java.util.Arrays;

public class _435_NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0;
        int last = 0;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[last][1]) {
                res++;
                if (intervals[i][1] > intervals[last][1]) {
                    last = i;
                }
            } else {
                last = i;
            }
        }
        return res;
    }
}
