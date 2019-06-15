package LCQuestions.lc089GreyCode;

import java.util.ArrayList;
import java.util.List;

public class GreyCode {

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }
}
