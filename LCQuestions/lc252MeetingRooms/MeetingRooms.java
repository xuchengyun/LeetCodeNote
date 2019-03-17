package LCQuestions.lc252MeetingRooms;

import java.util.Arrays;

public class MeetingRooms {
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

    // Sorting
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) {
                return false;
            }
        }
        return true;
    }

    // Brute Force
    public boolean canAttendMeetings1(Interval[] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                // do not miss the "==" case
                if ((intervals[i].start <= intervals[j].start && intervals[i].end > intervals[j].start)
                        || (intervals[i].start >= intervals[j].start && intervals[j].end > intervals[i].start)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        MeetingRooms testBruteForce = new MeetingRooms();
        Interval[] intervals = new Interval[] {new Interval(5, 8), new Interval(6,8)};
        testBruteForce.canAttendMeetings1(intervals);
    }
}
