package LCQuestions;

import java.util.*;

public class _1222_QueensThatCanAttackTheKing {

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 1}, {1, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) {
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1, 2});
        System.out.println(set.contains(new int[]{1, 2}));
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int[] q : queens) {
            s.add(q[0] * 8 + q[1]);
        }
        for (int[] dir : dirs) {
            int dx = dir[0];
            int dy = dir[1];
            int x = king[0] + dx;
            int y = king[1] + dy;
            while (x >= 0 && y >= 0 && x < 8 && y < 8) {
                if (s.contains(x * 8 + y)) {
                    result.add(Arrays.asList(x, y));
                    break;
                }
                x += dx;
                y += dy;
            }
        }
        return result;
    }
}
