package LCQuestions.lc119PascalTriangleII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        }
        if (rowIndex == 1) {
            return Arrays.asList(1,1);
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
}
