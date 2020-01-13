package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _679_24Game {
    final double eps = 0.001;

    public static void main(String[] args) {
        _679_24Game obj = new _679_24Game();
        System.out.println(obj.judgePoint24(new int[]{1, 3, 4, 6}));
    }

    public boolean judgePoint24(int[] nums) {

        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return helper(list);
    }

    private boolean helper(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24.0) < eps;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                double num1 = list.get(i);
                double num2 = list.get(j);
                List<Double> tmp = generatePossibleResults(num1, num2);
                for (double n : tmp) {
                    List<Double> next = new ArrayList<>();
                    next.add(n);
                    for (int k = 0; k < size; k++) {
                        if (k == j || k == i) {
                            continue;
                        }
                    }
                    if (helper(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Double> generatePossibleResults(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        res.add(a / b);
        res.add(b / a);
        return res;
    }
}
