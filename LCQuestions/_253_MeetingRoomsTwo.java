package LCQuestions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _253_MeetingRoomsTwo {
    /**
     Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
     Example 1:

     Input: [[0, 30],[5, 10],[15, 20]]
     Output: 2
     Example 2:

     Input: [[7,10],[2,4]]
     Output: 1
     NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

     \__\ \____\
       \____\ \___\

     * @param intervals
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {
        // start array
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);
        // compare for each interval with its start value
        // chronological order, time line
        /*
        1. Get start and end array and then sort them.
        2. Traverse through start Array.
        3. Keep in mind to search by timeline
        4. if starts[i] < ends[endIndex] means we need additional room for it, so cnt++
        5. else means one of meeting room get released. so we don't need additional room for end time, just endIndex++;
         */
        int endIndex = 0;
        int cnt = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (starts[i] < ends[endIndex]) {
                cnt++;
            } else {
                endIndex++;
            }
        }
        return cnt;
    }

    public int meetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int res = 0;
        int endIndex = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (start[i] < end[endIndex]) {
                res++;
            } else {
                endIndex++;
            }
        }
        return res;
    }

    // Priority Queue

    /**
     *
     \__\   \____\
     \____\     \___\
     * @param intervals
     * @return
     */
    public int meetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<Interval> q = new PriorityQueue<>(intervals.length, (a, b) -> (a.end - b.end));
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        q.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval cur = q.poll();
            if (intervals[i].start >= cur.end) {
                cur.end = intervals[i].end;
            } else {
                q.offer(intervals[i]);
            }
            q.offer(cur);
        }
        return q.size();
    }

    public int meetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(intervals.length, (a, b) -> (a[1] - b[1]));
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        q.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = q.poll();
            if (intervals[i][0] >= cur[1]) {
                cur[1] = intervals[i][1];
            } else {
                q.offer(intervals[i]);
            }
            q.offer(cur);
        }
        return q.size();
    }


    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
