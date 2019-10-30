package LCQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class _362_DesignHitCounter {

    Queue<Integer> q;
    /** Initialize your data structure here. */
    public _362_DesignHitCounter() {
        q = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!q.isEmpty() && timestamp - q.peek() >= 300) {
            q.poll();
        }
        return q.size();
    }

    private int[] times;
    private int[] hits;

    public void DesignHitCounter2() {
        times = new int[300];
        hits = new int[300];
    }


    public void hit2(int timestamp) {
        int index = timestamp % 300;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }

    public int getHits2(int timestamp) {
        int res = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) {
                res += hits[i];
            }
        }
        return res;
    }

}
