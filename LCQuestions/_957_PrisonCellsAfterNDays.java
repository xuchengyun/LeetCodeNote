package LCQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _957_PrisonCellsAfterNDays {

    // brute force
    public int[] prisonAfterNDays(int[] cells, int N) {
        while (N > 0) {
            N--;
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
        }
        return cells;
    }

    // 2 ^ 6 = 64 states, we have a circle of 64, to reduce time complexity
    // For each state, we only meet once, so the time complexity is O(2 ^ N), where N is the number of cells
    public int[] prisonAfterDay1(int[] cells, int N) {
        if (N == 0) return cells;
        Map<String, Integer> visited = new HashMap<>();
        while (N > 0) {
            visited.put(Arrays.toString(cells), N);
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; i++) {
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }
            cells = cells2;
            N--;
            if (visited.containsKey(Arrays.toString(cells))) {
                int thatN = visited.get(Arrays.toString(cells));
                int diff = N - thatN;
                N %= diff;
            }
        }
        return cells;
    }


    // find the pattern of it, the length of loop can be 1, 7, or 14.
    public int[] prisonAfterNDays2(int[] cells, int N) {
        for (N = (N - 1) % 14 + 1; N > 0; --N) {
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
        }
        return cells;
    }

    public static void main(String[] args) {
        _957_PrisonCellsAfterNDays p = new _957_PrisonCellsAfterNDays();
        int[] cells = new int[]{1,1,0,1,1,0,1,1};
        int N = 6;
    }
}
