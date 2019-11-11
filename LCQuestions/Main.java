package LCQuestions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // recursion
    public int countVowelPermutation(int n) {
        int[] count = new int[5];
        Arrays.fill(count, 1);
        long number = (long)(Math.pow(10, 9) + 7);

        while (n > 1) {
            n--;
            int temp0 = count[1] + count[2] + count[4];
            int temp1 = count[0] + count[2];
            int temp2 = count[1] + count[3];
            int temp3 = count[2];
            int temp4 = count[2] + count[3];
            count[0] = temp0;
            count[1] = temp1;
            count[2] = temp2;
            count[3] = temp3;
            count[4] = temp4;
        }
        int sum = 0;
        for(int i : count) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Main obj = new Main();
        int[][] gold = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        List<List<Integer>> k = obj.reconstructMatrix(2, 1, new int[]{1, 1, 1});
    }


    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        return helper(0, upper, lower, colsum);
    }

    private List<List<Integer>> helper(int index, int upper, int lower, int [] colsum) {
        if (index == colsum.length && upper == 0 && lower == 0) {
            List k1 = new ArrayList(res.get(0));
            List k2 = new ArrayList(res.get(1));
            List<List<Integer>> k3 = new ArrayList<>();
            k3.add(k1);
            k3.add(k2);
            return k3;
        } else if (index == colsum.length) {
            return new ArrayList<>();
        }
        List<List<Integer>> r;
        if (colsum[index] == 2) {
            res.get(0).add(1);
            res.get(1).add(1);
            r = helper(index + 1, upper - 1, lower - 1, colsum);
            res.get(0).remove(res.get(0).size() - 1);
            res.get(1).remove(res.get(1).size() - 1);
            if (r.size() > 0) {
                return r;
            }
        } else if (colsum[index] == 0) {
            res.get(0).add(0);
            res.get(1).add(0);
            r = helper(index + 1, upper, lower, colsum);
            res.get(0).remove(res.get(0).size() - 1);
            res.get(1).remove(res.get(1).size() - 1);
            if (r.size() > 0) {
                return r;
            }
        } else {
            res.get(0).add(0);
            res.get(1).add(1);
            r = helper(index + 1, upper, lower - 1, colsum);
            res.get(0).remove(res.get(0).size() - 1);
            res.get(1).remove(res.get(1).size() - 1);
            if (r.size() > 0) {
                return r;
            }
            res.get(0).add(1);
            res.get(1).add(0);
            r = helper(index + 1, upper - 1, lower, colsum);
            res.get(0).remove(res.get(0).size() - 1);
            res.get(1).remove(res.get(1).size() - 1);
            if (r.size() > 0) {
                return r;
            }
        }
        return new ArrayList<>();
    }

}
