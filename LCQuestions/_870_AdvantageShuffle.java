package LCQuestions;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class _870_AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[0] - o1[0]));
        for (int i = 0; i < B.length; i++) {
            pq.add(new int[]{B[i], i});
        }
        int[] res = new int[A.length];
        int hi = res.length - 1;
        int lo = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] < A[hi]) {
                res[cur[1]] = A[hi--];
            } else {
                res[cur[1]] = A[lo++];
            }
        }
        return res;
    }

    public int[] advantageCount1(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : A) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            Integer x = map.higherKey(B[i]);
            if (x == null) {
                x = map.firstKey();
            }
            map.put(x, map.get(x) - 1);
            if (map.get(x) == 0) {
                map.remove(x);
            }
            res[i] = x;
        }
        return res;
    }
}
