package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _119_PascalTriangleII {
    /**
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     * Note that the row index starts from 0.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     * Example:
     * Input: 3
     * Output: [1,3,3,1]
     * Follow up:
     * <p>
     * Could you optimize your algorithm to use only O(k) extra space?
     *
     * @param rowIndex
     * @return
     */
    //recursion
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        }
        if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        }
        List<Integer> pre = getRow(rowIndex - 1);

        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 0; i < pre.size() - 1; i++) {
            cur.add(pre.get(i) + pre.get(i + 1));
        }
        cur.add(1);
        return cur;
    }

    public List<Integer> getRow1(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) return res;
        for (int i = 0; i < rowIndex + 1; i++) {
            res.add(0, 1);
            for (int j = 1; j < res.size() - 1; j++) {
                res.set(j, res.get(j) + res.get(j + 1));
            }
        }
        return res;
    }

    public List<Integer> getRow2(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            result.add(1);
            for (int j = i - 1; j > 0; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }

        return result;
    }
}
