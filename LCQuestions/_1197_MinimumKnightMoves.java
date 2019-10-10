package LCQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _1197_MinimumKnightMoves {

    private final static int[][] dirs =
            new int[][]{{1, 2}, {2, 1}, {-1, 2}, {-2, 1},
                    {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private final static int hashCode = 601;

    public int minKnightMoves(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0 && b == 0) return 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                int x = cur % hashCode;
                int y = cur / hashCode;
                for (int[] dir : dirs) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx + yy > 300) {
                        continue;
                    }
                    int hash = xx + yy * hashCode;
                    if (hash == a + b * hashCode) {
                        System.out.println("a :" + xx + " " + "b : " + yy);
                        return step + 1;
                    }
                    if (!visited.contains(hash)) {
                        q.offer(hash);
                        visited.add(hash);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
