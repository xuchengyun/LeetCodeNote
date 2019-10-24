package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
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
        while (idx < intervals.length && intervals[idx][0] <= newInterval[1]) {
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

    // binary search
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        int start = Arrays.binarySearch(Arrays.stream(intervals).map(x -> x[1]).toArray(), newInterval[0]);
        int end = Arrays.binarySearch(Arrays.stream(intervals).map(x -> x[0]).toArray(), newInterval[1]);
        start = start < 0 ? -(start + 1) + 1: start;
        end = end < 0 ? -(end + 1) + 1: end;
        if (start <= end) {
            newInterval[0] = Math.min(newInterval[0], intervals[start][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[end][1]);
        }
        List<int[]> res = new ArrayList<>();
        int idx = 0;
        while (idx < start) {
            res.add(intervals[idx++]);
        }
        res.add(newInterval);
        idx = end + 1;
        while (idx < intervals.length) {
            res.add(intervals[idx++]);
        }
        return res.toArray(new int[res.size()][2]);
    }

    private int findStartPos(int[][] intervals, int value) {
        int l = 0, r = intervals.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int end = intervals[mid][1];
            if (end == value) {
                return mid;
            }
            if (end > value) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int findEndPos(int[][] intervals, int value) {
            int l = 0, r = intervals.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2 + 1;
                int start = intervals[mid][0];
                if (start == value) {
                    return mid;
                }
                if (start > value) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
        return l;
    }
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        int start = Arrays.binarySearch(Arrays.stream(intervals).map(x -> x[1]).toArray(), newInterval[0]);
        int end = Arrays.binarySearch(Arrays.stream(intervals).map(x -> x[0]).toArray(), newInterval[1]);
        System.out.println("start, " + start + " " + "end, " + end);
        start = start < 0 ? -(start + 1) : start;
        end = end < 0 ? -(end + 1) - 1 : end;
        if (start <= end) {
            newInterval[0] = Math.min(newInterval[0], intervals[start][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[end][1]);
        }
        List<int[]> res = new ArrayList<>();
        int idx = 0;
        while (idx < start) {
            res.add(intervals[idx++]);
        }
        res.add(newInterval);
        idx = end + 1;
        while (idx < intervals.length) {
            res.add(intervals[idx++]);
        }
        return res.toArray(new int[res.size()][2]);
    }
}
