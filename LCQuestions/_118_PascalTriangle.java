package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _118_PascalTriangle {
    /**
     Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
     In Pascal's triangle, each number is the sum of the two numbers directly above it.
     Example:
     Input: 5
     Output:
     [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
     ]
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        if (numRows == 1) {
            res.add(Arrays.asList(1));
            return res;
        }
        if (numRows == 2) {
            res.add(Arrays.asList(1));
            res.add(Arrays.asList(1,1));
            return res;
        }

        List<List<Integer>> pre =  generate(numRows - 1);
        List<Integer> preList = pre.get(pre.size() - 1);
        List<Integer> curList = new ArrayList<>();
        curList.add(1);
        for (int i = 0; i < preList.size() - 1; i++) {
            curList.add(preList.get(i) + preList.get(i + 1));
        }
        curList.add(1);
        pre.add(curList);
        return pre;
    }

    // dp
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        List<Integer> pre = cur;
        for (int i = 0; i < numRows; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
            res.add(cur);
        }
        return res;
    }

}
