package LCQuestions.Solutions._1300_1399._1383_MaximumPerformanceOfATeam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class _1383_MaximumPerformanceOfATeam {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[]{speed[i], efficiency[i]};
        }
        // Sort by efficiency
        Arrays.sort(engineers, (a, b) -> (b[1] - a[1]));
        Queue<Integer> pq = new PriorityQueue<>(k + 1);

        long res = 0, sumSpeed = 0;
        for (int[] engineer: engineers) {
            pq.add(engineer[0]);
            sumSpeed += engineer[0];
            if (pq.size() > k) {
                sumSpeed -= pq.poll();
            }
            res = Math.max(res, sumSpeed * engineer[1]);
        }

        return (int)(res % (int)(1e9 + 7));
    }
}
