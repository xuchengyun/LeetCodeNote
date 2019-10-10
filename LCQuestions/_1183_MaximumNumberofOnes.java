package LCQuestions;

import java.util.PriorityQueue;
import java.util.Queue;

public class _1183_MaximumNumberofOnes {

    // 数学题
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int[][] frequency = new int[sideLength][sideLength];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                frequency[i % sideLength][j % sideLength]++;
            }
        }

        Queue<Integer> pq = new PriorityQueue<>((a1, a2) -> a2 - a1);
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                pq.offer(frequency[i][j]);
            }
        }

        int total = 0;
        for (int i = 0; i < maxOnes; i++) {
            total += pq.poll();
        }
        return total;
    }
}
