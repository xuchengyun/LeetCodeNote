package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _118_PascalTriangle {
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

}
