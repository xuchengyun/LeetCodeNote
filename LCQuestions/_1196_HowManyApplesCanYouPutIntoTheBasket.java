package LCQuestions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _1196_HowManyApplesCanYouPutIntoTheBasket {


    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        int i;
        for (i = 0; i < arr.length; i++) {
            count += arr[i];
            if (count > 5000) {
                return i;
            }
        }
        return i;
    }

    // pq greedy
    public int maxNumberOfApples2(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int count = 0;
        for (int n : arr) {
            count += n;
            pq.offer(n);
            while (count > 5000) {
                count -= pq.poll();
            }
        }
        return pq.size();
    }
}
