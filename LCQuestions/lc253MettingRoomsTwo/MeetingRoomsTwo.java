package LCQuestions.lc253MettingRoomsTwo;

import java.util.Arrays;

public class MeetingRoomsTwo {
    static class Interval {
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
}
