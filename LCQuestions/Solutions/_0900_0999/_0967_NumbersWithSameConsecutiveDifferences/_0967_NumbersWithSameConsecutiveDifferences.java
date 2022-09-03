package LCQuestions.Solutions._0900_0999._0967_NumbersWithSameConsecutiveDifferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0967_NumbersWithSameConsecutiveDifferences {
    // DFS
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(list, N - 1, K, i);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(List<Integer> list, int N, int K, int num) {
        if (N <= 0) {
            list.add(num);
            return;
        }
        int lastDigit = num % 10;
        if (lastDigit + K <= 9) {
            dfs(list, N - 1, K, num * 10 + lastDigit + K);
        }

        if (lastDigit - K >= 0 && K != 0) {
            dfs(list, N - 1, K, num * 10 + lastDigit - K);
        }
    }

    // BFS
    public int[] numsSameConsecDiff1(int N, int K) {
        if (N == 1) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (int i = 1; i < N; i++) {
            List<Integer> newArr = new ArrayList<>();
            for (int cur : queue) {
                int tail = cur % 10;
                if (tail + K < 10) {
                    newArr.add(cur * 10 + tail + K);
                }
                if (tail - K >= 0 && K != 0) {
                    newArr.add(cur * 10 + tail - K);
                }
            }
            queue = newArr;
        }
        return queue.stream().mapToInt(i -> i).toArray();
    }
}
