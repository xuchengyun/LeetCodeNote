package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _1276_NumberofBurgersWithNoWasteofIngredients {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList<>();
        int k = tomatoSlices - 2 * cheeseSlices;
        if (k % 2 != 0 || k < 0) {
            return res;
        }
        k /= 2;
        int m = 4 * cheeseSlices - tomatoSlices;
        if (m % 2 != 0 || m < 0) {
            return res;
        }
        m /= 2;
        res.add(k);
        res.add(m);
        return res;
    }
}
